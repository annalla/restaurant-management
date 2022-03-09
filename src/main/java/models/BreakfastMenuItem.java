package models;

public class BreakfastMenuItem extends FoodMenuItem {
    protected final MenuType menuType=MenuType.BreakfastMenu;

    public BreakfastMenuItem(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public MenuType getMenuType() {
        return menuType;
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
