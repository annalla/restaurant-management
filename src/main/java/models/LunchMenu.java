package models;

public class LunchMenu extends FoodMenu{
    public LunchMenu(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public String toString() {
        return "LunchMenu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
