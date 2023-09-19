
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static final int NUM_OF_CHEFS = 5;
    public static final int NUM_OF_WAITERS = 5;
    public static Map<String, Dish> menu;
    private static Scanner s;
    public static BlockingDeque<Order> ordersToMake = new LinkedBlockingDeque<>();
    public static BlockingDeque<Order> ordersToDeliver = new LinkedBlockingDeque<>();
    public static Integer cashFlow = 0;

    public static void main(String[] args) {

        s = new Scanner(System.in);
        menu = new HashMap<>();
        menu.put("Omelette", new Dish("Omelette", 5, 5));
        menu.put("Cake", new Dish("Cake", 10, 7));
        menu.put("Noodles", new Dish("Noodles", 15, 10));
        Kitchen kitchen = new Kitchen(NUM_OF_CHEFS);
        Waiters waiters = new Waiters(NUM_OF_WAITERS);
        kitchen.start();
        waiters.start();

        boolean active = true;
        while(active){
            printUserMenu();
            int choice = s.nextInt();
            switch (choice){
                case 1:
                    System.out.println("The current menu is:");
                    printMenu();
                    break;
                case 2:
                    addDish();
                    System.out.println("Dish added successfully");
                    break;
                case 3:
                    System.out.print("Please insert table number: ");
                    int table = s.nextInt();
                    Order o = takeOrder(table);
                    ordersToMake.add(o);
                    break;
                case 4:
                    active =false;
                    kitchen.end();
                    waiters.end();
                    s.close();
                    break;
                default:
                    System.out.println("please try again");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printUserMenu(){
        System.out.println("Hello and welcome to NICE Restaurant");
        System.out.println("1. Present Menu");
        System.out.println("2. Add Dish to menu");
        System.out.println("3. Take order");
        System.out.println("4. Exit");
        System.out.print("Please enter your choice number: ");
    }

    private static void printMenu(){
        System.out.printf("Dish name:\tPrice:\tPrep Time\n");
        for(Dish d : menu.values()){
            System.out.printf("%s\t\t%d\t\t%d\n", d.getName(), d.getPrice(), d.getPrepTimeInMinutes());
        }
        System.out.println("Bon Appetit");
    }

    private static void addDish(){

        System.out.print("Please insert dish name: ");
        String name = s.next();
        System.out.print("Please insert dish price: ");
        int price = s.nextInt();
        System.out.print("Please insert dish prep time: ");
        int time = s.nextInt();
        menu.put(name ,new Dish(name, price, time));
    }

    private static Order takeOrder(int tableNumber){
        List<Dish> dishes = new ArrayList<>();
        System.out.println("Please enter items when you are done write 'done'");
        String curr = "";
        curr = s.next();
        while (!curr.equals("done")){
            if(menu.containsKey(curr))
                dishes.add(menu.get(curr));
            else
                System.out.println("There is no dish by that name");
            curr = s.next();
        }
        System.out.println("Any special comments?");
        curr = s.next();
        return new Order(dishes,tableNumber, curr);
    }
}