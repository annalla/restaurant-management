package models;

public class MenuItemFactory {
    public static MenuItem createMenuItem(MenuType menuType,String name,String description,String image,double price){
        MenuItem menuItem;
        switch (menuType) {
            case AlcoholMenu:
                menuItem = new AlcoholMenuItem(name, description, image, price);
                break;
            case SoftDrinkMenu:
                menuItem = new SoftDrinkMenuItem(name, description, image, price);
                break;
            case LunchMenu:
                menuItem = new LunchMenuItem(name, description,image, price);
                break;
            case BreakfastMenu:
                menuItem = new BreakfastMenuItem(name, description, image, price);
                break;
            case DinnerMenu:
                menuItem = new DinnerMenuItem(name, description, image, price);
                break;
            default:
                return null;
        }
        return menuItem;
    }
}
