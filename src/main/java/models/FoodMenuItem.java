package models;

public abstract class FoodMenuItem extends MenuItem {
    protected final MenuType menuType=MenuType.FoodMenu;

    public FoodMenuItem(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public MenuType getMenuType() {
        return menuType;
    }

    @Override
    public String toString() {
        return "FoodMenu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
