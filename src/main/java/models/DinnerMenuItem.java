package models;

public class DinnerMenuItem extends FoodMenuItem {
    protected final MenuType menuType=MenuType.DrinkMenu;

    public DinnerMenuItem(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public MenuType getMenuType() {
        return menuType;
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
