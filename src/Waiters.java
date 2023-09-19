import static java.lang.Thread.sleep;

public class Waiters {
    private Thread[] waiters;
    private boolean terminated;
    public Waiters(int numberOfWaiters){
        terminated = false;
        waiters = new Thread[numberOfWaiters];

        for(int i =0;i<numberOfWaiters;i++){
            Thread t = new Thread(()-> {
                while (!terminated) {
                    Order o = null;
                    try {
                        o = Main.ordersToDeliver.take();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " is terminated");
                    }
                    if (o != null) {
                        System.out.println(Thread.currentThread().getName() + " delivers order to table " + o.getTableNumber());
                        System.out.println("Order for table " + o.getTableNumber() + " was delivered");
                        int totalCash = o.getTotalCash();
                        //Updating total cash flow
                        synchronized (Main.cashFlow) {
                            Main.cashFlow += totalCash;
                            System.out.println("current cash flow is: " + Main.cashFlow);
                        }
                    }
                }
            });
            t.setName("waiter-" + (i+1));
            waiters[i] = t;
        }
    }

    public void start(){
        for(Thread t : waiters){
            System.out.println(t.getName() + " started working!");
            t.start();
        }
    }
    public void end(){
        terminated = true;
        for(Thread t : waiters){
            t.interrupt();
        }
    }
}
