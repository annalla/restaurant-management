package models;

public class SoftDrinkMenu extends DrinkMenu{
    public SoftDrinkMenu(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public String toString() {
        return "SoftDrinkMenu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
