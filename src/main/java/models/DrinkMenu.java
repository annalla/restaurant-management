package models;

public abstract class DrinkMenu extends MenuItem {
    public DrinkMenu(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public String toString() {
        return "DrinkMenu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
