public class Dish {
    private String name;
    private int price;
    int prepTimeInMinutes;

    public Dish(String name, int price, int prepTimeInMinutes) {
        this.name = name;
        this.price = price;
        this.prepTimeInMinutes = prepTimeInMinutes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPrepTimeInMinutes(int prepTimeInMinutes) {
        this.prepTimeInMinutes = prepTimeInMinutes;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPrepTimeInMinutes() {
        return prepTimeInMinutes;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
