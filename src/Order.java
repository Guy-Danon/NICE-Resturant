import java.util.List;

public class Order {
    private List<Dish> orderDishes;
    int tableNumber;
    String specialComments;

    public Order(List<Dish> orderDishes, int tableNumber, String specialComments) {
        this.orderDishes = orderDishes;
        this.tableNumber = tableNumber;
        this.specialComments = specialComments;
    }

    public List<Dish> getOrderDishes() {
        return orderDishes;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getSpecialComments() {
        return specialComments;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDishes=" + orderDishes +
                ", tableNumber=" + tableNumber +
                ", specialComments='" + specialComments + '\'' +
                '}';
    }

    public int getTotalPrepTime(){
        int total = 0;
        for(Dish d : orderDishes){
            total += d.getPrepTimeInMinutes();
        }
        return total;
    }

    public int getTotalCash(){
        int total = 0;
        for(Dish d : orderDishes){
            total += d.getPrice();
        }
        return total;
    }
}
