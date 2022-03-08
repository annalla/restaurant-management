package models;

public class AlcoholMenu extends DrinkMenu{

    public AlcoholMenu(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public String toString() {
        return "AlcoholMenu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
