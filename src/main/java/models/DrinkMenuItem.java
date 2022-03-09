package models;

public abstract class DrinkMenuItem extends MenuItem {
    protected final MenuType menuType=MenuType.DrinkMenu;

    public DrinkMenuItem(String name, String description, String image, double price) {
        super(name, description, image, price);
    }

    @Override
    public MenuType getMenuType() {
        return menuType;
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
