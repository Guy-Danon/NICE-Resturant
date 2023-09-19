import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Kitchen {
    private Thread[] chefs;
    private boolean terminated;
    public Kitchen(int numberOfChefs){
        terminated = false;
        chefs = new Thread[numberOfChefs];

        for(int i =0;i<numberOfChefs;i++){
            Thread t = new Thread(()->{
                while (!terminated) {
                    Order o = null;
                    try {
                        o = Main.ordersToMake.take();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " is terminated");
                    }
                    if (o != null) {
                        int orderTime = o.getTotalPrepTime();
                        System.out.println(Thread.currentThread().getName() + "makes order for table " + o.getTableNumber());
                        System.out.println("This will take " + orderTime + " minutes...");
                        try {
                            sleep(orderTime * 1000); //instead of minutes
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + "is terminated");
                        }
                        System.out.println("Order for table " + o.getTableNumber() + " is ready");
                        Main.ordersToDeliver.add(o);
                    }
                }
            });
            t.setName("chef-" + (i+1));
            chefs[i] = t;
        }
    }

    public void start(){
        for(Thread t : chefs){
            System.out.println(t.getName() + " started working!");
            t.start();
        }
    }
    public void end(){
        terminated = true;
        for(Thread t : chefs){
            t.interrupt();
        }
    }
}
