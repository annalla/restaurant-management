package models;

public class LunchMenuItem extends FoodMenuItem {

    protected final MenuType menuType=MenuType.LunchMenu;

    public LunchMenuItem(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public MenuType getMenuType() {
        return menuType;
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
