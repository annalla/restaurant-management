package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class of MenuManagement: manage(add, delete, update, display) menu items in program
 *
 */
public class MenuManagement {

    private static final Logger logger = LogManager.getLogger(MenuManagement.class);

    public static List<MenuItem> menuList;
    public static HashMap<MenuType, List<MenuType>> menu;

    public MenuManagement() {
        menuList = new ArrayList<>();
    }

    public static void setMenuList(List<MenuItem> menuList) {
        MenuManagement.menuList = new ArrayList<>(menuList);
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
            if (menuList.get(index).update(properties)){
                return 1;
            }
            return -1;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("updateMenuWithMoreProperties() - " + e);
            return 0;
        }
    }

    /**
     * update menu name
     *
     * @param name name need to updated
     * @param index index of menu in list
     * @return true if update successfully. false if index not existed
     */
    public boolean updateMenuName(String name, int index) {
        try {
            menuList.get(index).setName(name);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Update menu failed due to " + e);
            return false;
        }
    }

    /**
     * update menu description
     *
     * @param description description need to updated
     * @param index index of menu in list
     * @return true if update successfully. false if index not existed
     */
    public boolean updateMenuDescription(String description, int index) {
        try {
            menuList.get(index).setDescription(description);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Update menu failed due to " + e);
            return false;
        }
    }

    /**
     * update menu image
     *
     * @param image image need to updated
     * @param index index of menu in list
     * @return true if update successfully. false if index not existed
     */
    public boolean updateMenuImage(String image, int index) {
        try {
            menuList.get(index).setImage(image);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Update menu failed due to " + e);
            return false;
        }
    }

    /**
     * update menu price
     *
     * @param price price need to updated
     * @param index index of menu in list
     * @return true if update successfully. false if index not existed
     */
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

    public static String getBasicMenuInfo(int index) {
        try {
            MenuItem menuItem = menuList.get(index);
            return menuItem.getBasicInfor();
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Get detailed Menu failed due to " + e);
            return null;
        }

    }

    public static boolean checkMenuData() {
        try {
            return menuList.size() != 0;
        }
        catch (RuntimeException e){
            return false;
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
