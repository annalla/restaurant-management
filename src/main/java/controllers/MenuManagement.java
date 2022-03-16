package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import models.*;
import utils.MenuViewConstant;
import views.MainView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static utils.ScreenUtility.stopScreen;

/**
 * Class of MenuManagement: manage(add, delete, update, display) menu items in program
 */
public class MenuManagement {

    private static final Logger logger = LogManager.getLogger(MenuManagement.class);

    public static List<MenuItem> menuList = new ArrayList<>();
    public static HashMap<MenuType, List<MenuType>> menu;

    // Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    // CSV file header
    private static final String FILE_HEADER = "menuType,name,description,image,price";

    public MenuManagement() {
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
    public boolean addMenuItem(MenuType type, String name, String description, String img, double price) {
        if (!checkExistedMenuItemName(name)) {
            MenuItem menuItem = MenuItemFactory.createMenuItem(type, name, description, img, price);
            if (menuItem != null) {
                menuList.add(menuItem);
                return true;
            }
        }
        return false;
    }

    /**
     * check existed name in menu list
     *
     * @param name name need to check
     * @return true if existed name, false if not
     */
    private boolean checkExistedMenuItemName(String name) {
        for (MenuItem menuItem : menuList) {
            if (name.equals(menuItem.getName())) {
                return true;
            }
        }
        return false;
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
            if (menuList.get(index).update(properties)) {
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
     * @param name  name need to updated
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
     * @param index       index of menu in list
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
            return MenuManagement.menuList.size() != 0;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Get menu list in data/menu_item_list.csv
     *
     * @return 1 if get bill list successfully, -1 if failed, 0 if file not existed
     * @throws IOException
     */
    public static int getMenuData() {
        ArrayList<MenuItem> newMenu = new ArrayList<>();
        try {
            File f = new File(MainView.dataDirectory + "//" + MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            if (!f.exists()) {
                return 0;
            }
            BufferedReader br = null;
            try {
                String line;
                br = new BufferedReader(new FileReader(MainView.dataDirectory + "//" + MenuViewConstant.FILE_NAME_MENU_ITEM_DATA));
                br.readLine();
                while ((line = br.readLine()) != null) {
                    MenuItem menuItem = convertCsvLine(line);
                    if (menuItem != null) {
                        newMenu.add(menuItem);
                    }
                }
                menuList = newMenu;
                return 1;

            } catch (IOException e) {
                return -1;
            } finally {
                try {
                    if (br != null)
                        br.close();
                } catch (IOException e) {
                    return -1;
                }
            }

        } catch (Exception e) {
            logger.fatal("getMenuData() - " + e);
            return -1;
        }
    }

    /**
     * Convert csv line (MenuType,name,description,image,price) into a menu item
     *
     * @param line
     * @return
     */
    private static MenuItem convertCsvLine(String line) {
        String[] properties = line.split(COMMA_DELIMITER);
        if (properties.length != 5) {
            return null;
        }
        try {
            MenuType menuType = MenuType.valueOf(properties[0]);
            if (properties[1].length() == 0) {
                return null;
            }
            double price = Double.valueOf(properties[4]);
            return MenuItemFactory.createMenuItem(menuType, properties[1], properties[2], properties[3], price);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Save menu list in data/menu_item_list.csv
     *
     * @return true if save bill list successfully, false if failed
     * @throws IOException
     */
    public static boolean saveMenuData() throws IOException {
        FileWriter fileWriter = null;
        try {
            File theDir = new File(MainView.dataDirectory);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            File myObj = new File(MainView.dataDirectory + "//" + MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            myObj.createNewFile();

            fileWriter = new FileWriter(MainView.dataDirectory + "//" + MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);

            // Write the CSV file header
            fileWriter.append(FILE_HEADER);

            // Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            // Write a new Country object list to the CSV file
            for (MenuItem menuItem : menuList) {
                fileWriter.append(String.valueOf(menuItem.getMenuType()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(menuItem.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(menuItem.getDescription());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(menuItem.getImage());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(menuItem.getPrice()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            return true;
        } catch (IOException e) {
            logger.fatal("saveMenuData()- " + e);
            return false;
        } finally {
            fileWriter.close();
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
