package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MenuManagement {

    private static final Logger logger = LogManager.getLogger(MenuManagement.class);

    private List<MenuItem> menuList;
    public static HashMap<MenuType, List<MenuType>> menu;

    public MenuManagement() {
        menuList = new ArrayList<>();
    }

    public void setMenuList(List<MenuItem> menuList) {
        this.menuList = menuList;
    }

    public List<MenuItem> getMenuList() {
        return menuList;
    }

    public static void setUpMenu() {

        menu = new HashMap<>();
        List<MenuType> drinkMenu = new ArrayList<>();
        drinkMenu.add(MenuType.SoftDrinkMenu);
        drinkMenu.add(MenuType.AlcoholMenu);

        List<MenuType> foodMenu = new ArrayList<>();
        foodMenu.add(MenuType.LunchMenu);
        foodMenu.add(MenuType.DinnerMenu);
        foodMenu.add(MenuType.BreakfastMenu);
        menu.put(MenuType.FoodMenu, foodMenu);
        menu.put(MenuType.DrinkMenu, drinkMenu);
    }

    private MenuItem createMenuItem(MenuType type, String name, String des, String img, double price) {
        MenuItem menuItem;
        switch (type) {
            case AlcoholMenu:
                menuItem = new AlcoholMenuItem(name, des, img, price);
                break;
            case SoftDrinkMenu:
                menuItem = new SoftDrinkMenuItem(name, des, img, price);
                break;
            case LunchMenu:
                menuItem = new LunchMenuItem(name, des, img, price);
                break;
            case BreakfastMenu:
                menuItem = new BreakfastMenuItem(name, des, img, price);
                break;
            case DinnerMenu:
                menuItem = new DinnerMenuItem(name, des, img, price);
                break;
            default:
                return null;
        }
        return menuItem;
    }

    /**
     * create a menu in menuList
     *
     * @param name        name of the menu
     * @param description description of the menu
     * @param img         image's url of the menu
     * @param price       price of the menu
     * @param type        of menu
     * @return index of menu in menuList or a negative if not succeeded
     */
    public int addMenuItem(MenuType type, String name, String description, String img, double price) {
        menuList.add(createMenuItem(type, name, description, img, price));
        return menuList.size() - 1;
    }

    /**
     * Delete menu from index in menuList
     *
     * @param index index of the menu
     * @return deleted MenuItem or null if index out of bound
     */
    public MenuItem deleteMenu(int index) {
        try {
            return menuList.remove(index);
        } catch (RuntimeException e) {
            logger.fatal("deleteMenu() - " + e);
            return null;
        }
    }

    /**
     * Update properties of a menu in menuList
     *
     * @param menu  instance of new menu
     * @param index index of menu need to update
     * @return true if succeeded false if failed
     */
    public boolean updateMenu(MenuItem menu, int index) {
        try {
            menuList.get(index).updateMenu(menu);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("updateMenu() - " + e);
            return false;
        }
    }

    /**
     * Update menu with more properties (name,description,image url,price)
     *
     * @param properties string of properties
     * @param index      index of menu in list
     * @return 1 if update succeeded, 0 if update failed, -1 if wrong format properties string
     */
    public int updateMenuWithMoreProperties(String properties, int index) {
        try {
            MenuItem updatedItem = formatPropertiesStringToMenuItem(properties, index);
            if (updatedItem == null) {
                return -1;
            }
            menuList.get(index).updateMenu(updatedItem);
            return 1;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("updateMenu() - " + e);
            return 0;
        }
    }

    private MenuItem formatPropertiesStringToMenuItem(String properties, int index) {
        String[] items = properties.split("-");
        double price;
        if (items.length != 3) {
            return null;
        }
        for (int i = 0; i < items.length; i++) {
            items[i] = items[i].trim();
        }
        try {
            price = Double.parseDouble(items[items.length - 1]);
            if (price < 0) {
                return null;
            }
        } catch (RuntimeException e) {
            logger.fatal("formatPropertiesStringToMenuItem() - " + e);
            return null;
        }
        menuList.get(index).update(items[0], items[1], items[2], price);
        return menuList.get(index);
    }

    public boolean updateMenuName(String name, int index) {
        try {
            menuList.get(index).setName(name);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Update menu failed due to " + e);
            return false;
        }
    }

    public boolean updateMenuDescription(String description, int index) {
        try {
            menuList.get(index).setDescription(description);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Update menu failed due to " + e);
            return false;
        }
    }

    public boolean updateMenuImage(String image, int index) {
        try {
            menuList.get(index).setImage(image);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Update menu failed due to " + e);
            return false;
        }
    }

    public boolean updateMenuPrice(double price, int index) {
        try {
            menuList.get(index).setPrice(price);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Update menu failed due to " + e);
            return false;
        }
    }

    /**
     * Display information of the menu from index
     *
     * @param index index of menu in menuList
     * @return a string information of the menu
     */
    public String displayMenu(int index) {
        try {
            return menuList.get(index).toString();
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Get string display menu failed failed due to index out of bound");
            return null;
        }

    }

    /**
     * Get menuItem from menuList
     *
     * @param index of menu in menuList
     * @return Menu from index in menuList or null if index out of bound
     */
    public MenuItem getMenu(int index) {
        try {
            return menuList.get(index);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Get detailed Menu failed due to " + e);
            return null;
        }

    }

    /**
     * Get name of menu from index
     *
     * @param index index of menu in list
     * @return string name
     */
    public String getMenuName(int index) {
        try {
            return menuList.get(index).getName();
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Get detailed Menu failed due to " + e);
            return null;
        }

    }

    /**
     * Get price of menu from index
     *
     * @param index index of menu in list
     * @return string name
     */
    public double getMenuPrice(int index) {
        try {
            return menuList.get(index).getPrice();
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Get detailed Menu failed due to " + e);
            return -1;
        }

    }
}
