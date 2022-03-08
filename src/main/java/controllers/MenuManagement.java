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

    /**
     * create a menu in menuList
     *
     * @param name  name of the menu
     * @param des   description of the menu
     * @param img   image's url of the menu
     * @param price price of the menu
     * @param type  of menu
     * @return index of menu in menuList or a negative if not succeeded
     */
    public int createMenu(MenuType type, String name, String des, String img, double price) {

        switch (type) {
            case AlcoholMenu:
                menuList.add(new AlcoholMenu(name, des, img, price));
                break;
            case SoftDrinkMenu:
                menuList.add(new SoftDrinkMenu(name, des, img, price));
                break;
            case LunchMenu:
                menuList.add(new LunchMenu(name, des, img, price));
                break;
            case BreakfastMenu:
                menuList.add(new BreakfastMenu(name, des, img, price));
            case DinnerMenu:
                menuList.add(new DinnerMenu(name, des, img, price));
                break;
        }
        return menuList.size() - 1;
    }

    /**
     * Delete menu from index in menuList
     *
     * @param index index of the menu
     * @return deleted Menu or null if index out of bound
     */
    public MenuItem deleteMenu(int index) {
        try {
            return menuList.remove(index);
        } catch (RuntimeException e) {
            logger.fatal("Delete menu failed due to " + e);
            return null;
        }
    }

    /**
     * Update properties of a menu if index in of bound
     *
     * @param menu  instance of new menu
     * @param index index of menu need to update
     */
    public boolean updateMenu(MenuItem menu, int index) {
        try {
            menuList.get(index).updateMenu(menu);
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
}
