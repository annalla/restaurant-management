package models;

public class DinnerMenu extends FoodMenu {
    public DinnerMenu(String name, String description, String image, double price) {
        super(name, description, image, price);
    }
    @Override
    public String toString() {
        return "DinnerMenu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
