package models;

public class BreakfastMenu extends FoodMenu {
    public BreakfastMenu(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public String toString() {
        return "BreakfastMenu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
