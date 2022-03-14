package views;

import controllers.MenuManagement;
import models.MenuItem;
import models.MenuType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.MainViewConstant;
import utils.MenuViewConstant;
import utils.Message;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static utils.ScreenUtility.isContinue;
import static utils.ScreenUtility.stopScreen;
import static utils.ScreenUtility.scanner;


/**
 * The class of MenuView: view of menu management
 *
 * @see MenuManagement
 */
public class MenuView {

    private MenuManagement menuManagement = new MenuManagement();

    private static final Logger logger = LogManager.getLogger(MenuView.class);

    private static HashMap<Integer, MenuType> menuSelectMenuType = new HashMap<>();

    /**
     * set up all kind of menu items
     */
    private static void setUpMenuSelectMenuType() {
        int index = 1;
        for (Map.Entry<MenuType, List<MenuType>> entry : MenuManagement.menu.entrySet()) {
            for (MenuType type : entry.getValue()) {
                menuSelectMenuType.put(index, type);
                index++;
            }
        }
    }

    /**
     * print all kind of menu items
     */
    private void displayMenuType() {
        int index = 1;
        for (Map.Entry<MenuType, List<MenuType>> entry : MenuManagement.menu.entrySet()) {
            System.out.println(entry.getKey());
            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.println("\t" + index + ". " + entry.getValue().get(i));
                index++;
            }
        }
    }

    /**
     * add a menu item view
     */
    public void addAMenuItem() {
        String name, description, image;
        double price = 0;
        System.out.println(Message.ADD_A_MENUITEM);
        boolean flag = true;
        int select;

        displayMenuType();

        while (flag) {
            try {
                System.out.println(Message.CHOOSE_TYPE_MENU);
                select = scanner.nextInt();
                scanner.nextLine();
                if (menuSelectMenuType.containsKey(select)) {
                    System.out.println(Message.INPUT_NAME);
                    name = scanner.nextLine();
                    while (name.length() == 0) {
                        System.out.println(Message.EMPTY_STRING + "." + Message.INPUT_AGAIN);
                        name = scanner.nextLine();
                    }
                    System.out.println(Message.INPUT_DESCRIPTION);
                    description = scanner.nextLine();
                    System.out.println(Message.INPUT_IMAGE_URL);
                    image = scanner.nextLine();
                    boolean flag1 = true;
                    while (flag1) {
                        System.out.println(Message.INPUT_PRICE);
                        try {
                            price = scanner.nextDouble();
                            scanner.nextLine();
                            if (price >= 0) {
                                flag1 = false;
                            } else {
                                System.out.println(Message.INVALID_VALUE);
                            }
                        } catch (RuntimeException e) {
                            logger.fatal("addAMenuItem() - " + e);
                            scanner.nextLine();
                            System.out.println(Message.WRONG_INPUT);
                        }
                    }
                    menuManagement.addMenuItem(menuSelectMenuType.get(select), name, description, image, price);
                    flag = false;
                } else {
                    System.out.println(Message.CHOICE_NOT_EXISTED);
                }
            } catch (RuntimeException e) {
                logger.fatal("addAMenuItem() - " + e);
                System.out.println(Message.WRONG_INPUT + "." + Message.SELECT_AGAIN);
                scanner.nextLine();
            }
        }

    }

    /**
     * delete a menu item view
     */
    public void deleteAMenuItem() {
        System.out.println(Message.DELETE_A_MENUITEM);
        if (MenuManagement.checkMenuData()) {
            int indexMenu;
            printMenuItemList();
            boolean flag = true;
            while (flag) {
                System.out.println(Message.INPUT_INDEX_MENU);
                try {
                    indexMenu = scanner.nextInt();
                    MenuItem deletedMenu = menuManagement.deleteMenu(indexMenu);
                    if (deletedMenu == null) {
                        System.out.println(Message.FAILED_WRONG_INDEX);
                        if (!isContinue()) {
                            flag = false;
                        }
                    } else {
                        System.out.println(Message.SUCCESS);
                        stopScreen();
                        scanner.nextLine();
                        flag = false;
                    }
                } catch (RuntimeException e) {
                    logger.fatal("deleteAMenuItem() - " + e);
                    scanner.nextLine();
                    System.out.println(Message.WRONG_INPUT);
                }
            }
        } else {
            System.out.println(Message.NO_DATA);
            stopScreen();
        }

    }

    /**
     * update name of menu item view
     *
     * @param selectedMenu index of selected menu to update
     */
    private void updateNameMenuItem(int selectedMenu) {
        scanner.nextLine();
        System.out.println(Message.INPUT_NAME);
        String name = scanner.nextLine();
        while (name.length() == 0) {
            System.out.println(Message.EMPTY_STRING + "." + Message.INPUT_AGAIN);
            name = scanner.nextLine();
        }
        if (menuManagement.updateMenuName(name, selectedMenu)) {
            System.out.println(Message.SUCCESS);
        } else {
            System.out.println(Message.FAILED_WRONG_INDEX);
        }
    }

    /**
     * update price of menu item view
     *
     * @param selectedMenu index of selected menu to update
     */
    private void updatePriceMenuItem(int selectedMenu) {
        double price = -1;
        while (price <= -1) {
            try {
                System.out.println(Message.INPUT_PRICE);
                price = scanner.nextDouble();
            } catch (RuntimeException e) {
                logger.fatal("updatePriceMenuItem() - " + e);
                System.out.println(Message.WRONG_INPUT);
                scanner.nextLine();
            }
        }
        if (menuManagement.updateMenuPrice(price, selectedMenu)) {
            System.out.println(Message.SUCCESS);

        } else {
            System.out.println(Message.FAILED_WRONG_INDEX);
        }
    }

    /**
     * update description of menu item view
     *
     * @param selectedMenu index of selected menu to update
     */
    private void updateDescriptionMenuItem(int selectedMenu) {
        scanner.nextLine();
        System.out.println(Message.INPUT_DESCRIPTION);
        String description = scanner.nextLine();
        while (description.length() == 0) {
            System.out.println(Message.EMPTY_STRING + "." + Message.INPUT_AGAIN);
            description = scanner.nextLine();
        }
        if (menuManagement.updateMenuDescription(description, selectedMenu)) {
            System.out.println(Message.SUCCESS);
        } else {
            System.out.println(Message.FAILED_WRONG_INDEX);
        }
    }

    /**
     * update image url of menu item view
     *
     * @param selectedMenu index of selected menu to update
     */
    private void updateImageMenuItem(int selectedMenu) {
        scanner.nextLine();
        System.out.println(Message.INPUT_IMAGE_URL);
        String image = scanner.nextLine();
        while (image.length() == 0) {
            System.out.println(Message.EMPTY_STRING + "." + Message.INPUT_AGAIN);
            image = scanner.nextLine();
        }
        if (menuManagement.updateMenuImage(image, selectedMenu)) {
            System.out.println(Message.SUCCESS);
        } else {
            System.out.println(Message.FAILED_WRONG_INDEX);
        }
    }

    /**
     * update more than one property of menu item (name,description,image url, price)
     *
     * @param selectedMenu index of selected menu to update
     */
    private void updateAMenuItemWithProperties(int selectedMenu) {
        scanner.nextLine();
        boolean flag = true;
        String properties;
        while (flag) {
            System.out.println(Message.INPUT_PROPERTIES);
            properties = scanner.nextLine();
            int updatedStatus = menuManagement.updateMenuWithMoreProperties(properties, selectedMenu);
            if (updatedStatus == 1) {
                System.out.println(Message.SUCCESS);
                return;
            } else if (updatedStatus == 0) {
                System.out.println(Message.FAILED_WRONG_INDEX);
            } else {
                System.out.println(Message.WRONG_FORMAT);
            }
            if (!isContinue()) {
                flag = false;
            }
        }
    }

    /**
     * update a menu item view
     *
     * @see MenuView#updateNameMenuItem(int)
     * @see MenuView#updateDescriptionMenuItem(int)
     * @see MenuView#updateImageMenuItem(int)
     * @see MenuView#updatePriceMenuItem(int)
     * @see MenuView#updateAMenuItemWithProperties(int)
     */
    public void updateAMenuItem() {
        System.out.println(Message.UPDATE_A_MENUITEM);
        if (MenuManagement.checkMenuData()) {
            int selectedMenu = printAMenuItem();
            int choice;
            boolean flag = true;

            while (flag) {
                System.out.println(Message.UPDATE_MENU_ITEM_MENU);
                System.out.println(Message.INPUT_CHOICE);
                try {
                    choice = scanner.nextInt();
                    switch (choice) {
                        case MenuViewConstant.UPDATE_NAME_INDEX:
                            updateNameMenuItem(selectedMenu);
                            break;
                        case MenuViewConstant.UPDATE_DESCRIPTION_INDEX:
                            updateDescriptionMenuItem(selectedMenu);
                            break;
                        case MenuViewConstant.UPDATE_IMAGE_INDEX:
                            updateImageMenuItem(selectedMenu);
                            break;
                        case MenuViewConstant.UPDATE_PRICE_INDEX:
                            updatePriceMenuItem(selectedMenu);
                            break;
                        case MenuViewConstant.UPDATE_MORE_INDEX:
                            updateAMenuItemWithProperties(selectedMenu);
                            break;
                        case MenuViewConstant.UPDATE_BACK:
                            flag = false;
                            break;
                        default:
                            System.out.println(Message.CHOICE_NOT_EXISTED);
                    }
                } catch (RuntimeException e) {
                    logger.fatal("updateAMenuItem() - " + e);
                    scanner.nextLine();
                    System.out.println(Message.WRONG_INPUT);
                }
            }
        } else {
            System.out.println(Message.NO_DATA);
            stopScreen();
        }
    }

    /**
     * select an index of a menu in list and print selected menu item
     *
     * @return selected index of menu in list
     */
    private int printAMenuItem() {
        int indexMenu = -1;
        printMenuItemList();
        boolean flag = true;
        while (flag) {
            try {
                System.out.println(Message.INPUT_INDEX_MENU);
                indexMenu = scanner.nextInt();
                scanner.nextLine();
                String selectedMenu = menuManagement.displayMenu(indexMenu);
                if (selectedMenu == null) {
                    System.out.println(Message.INDEX_NOT_EXISTED);
                } else {
                    System.out.println(selectedMenu);
                    flag = false;
                }
            } catch (RuntimeException e) {
                logger.fatal("printAMenuItem() - " + e);
                scanner.nextLine();
                System.out.println(Message.WRONG_INPUT);
            }
        }
        return indexMenu;
    }

    /**
     * display view a menu item
     *
     * @see MenuView#printAMenuItem()
     */
    public void displayAMenuItem() {
        System.out.println(Message.DISPLAY_A_MENUITEM);
        if (MenuManagement.checkMenuData()) {
            printAMenuItem();
        } else {
            System.out.println(Message.NO_DATA);
        }
        stopScreen();
    }

    /**
     * print all menu items in list
     */
    private void printMenuItemList() {
        if (!MenuManagement.checkMenuData()) {
            System.out.println(Message.NO_DATA);
            return;
        }
        System.out.println(Message.HEADER_MENU_LIST);
        for (int i = 0; i < MenuManagement.menuList.size(); i++) {
            System.out.println(String.format("%-10d%s",i,MenuManagement.getBasicMenuInfo(i)));
        }
    }

    /**
     * display view of all menu items in list
     *
     * @see MenuView#printMenuItemList()
     */
    public void displayMenuItemList() {
        System.out.println(Message.DISPLAY_MENUITEM_LIST);
        printMenuItemList();
        stopScreen();
    }

    public void exportAMenuItemCSV() {
        System.out.println(Message.EXPORT_MENUITEM_CSV);

    }

    public void importAMenuItemCSV() {
        System.out.println(Message.IMPORT_MENUITEM_CSV);
    }

    /**
     * Get data of all menu items in menu List in file
     */
    public void getMenuItemData() {
        System.out.println(Message.GET_MENU_ITEMS);
        try {
            File f = new File(MainView.dataDirectory + "//" + MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            if (!f.exists()) {
                System.out.println(Message.NO_DATA);
            }
            FileInputStream is = new FileInputStream(MainView.dataDirectory + "//" + MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            ObjectInputStream ois = new ObjectInputStream(is);
            List<MenuItem> items = (List<MenuItem>) ois.readObject();
            MenuManagement.setMenuList(items);
            ois.close();
            is.close();
            System.out.println(Message.SUCCESS);
            stopScreen();
        } catch (Exception e) {
            logger.fatal("getMenuItemData() - " + e);
            System.out.println(Message.GET_DATA_FAILED);
            stopScreen();
        }

    }

    /**
     * Save data of all menu items in menu List in file
     */
    public void saveMenuItemData() {
        System.out.println(Message.SAVE_MENU_ITEMS);

        try {
            File theDir = new File(MainView.dataDirectory);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            File myObj = new File(MainView.dataDirectory + "//" + MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            myObj.createNewFile();
            FileOutputStream fos = new FileOutputStream(MainView.dataDirectory + "//" + MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(MenuManagement.menuList);
            // closing resources
            oos.close();
            fos.close();
            System.out.println(Message.SUCCESS);

            stopScreen();
        } catch (IOException e) {
            logger.fatal("saveMenuItemData() - " + e);
            System.out.println(Message.SAVE_DATA_FAILED);
            stopScreen();
        }
    }

    /**
     * display main view of menu management
     */
    public void displayMain() {

        MenuManagement.setUpMenu();
        setUpMenuSelectMenuType();
        int choice;
        boolean flag = true;

        while (flag) {

            System.out.println(Message.MENU_VIEW_MENU);
            System.out.println(Message.INPUT_CHOICE);

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case MenuViewConstant.ADD_MENUITEM_INDEX:
                        addAMenuItem();
                        break;
                    case MenuViewConstant.DELETE_MENUITEM_INDEX:
                        deleteAMenuItem();
                        break;
                    case MenuViewConstant.UPDATE_MENUITEM_INDEX:
                        updateAMenuItem();
                        break;
                    case MenuViewConstant.DISPLAY_A_MENUITEM_INDEX:
                        displayAMenuItem();
                        break;
                    case MenuViewConstant.DISPLAY_MENUITEM_LIST_INDEX:
                        displayMenuItemList();
                        break;
                    case MenuViewConstant.EXPORT_A_MENUITEM_INDEX:
                        exportAMenuItemCSV();
                        break;
                    case MenuViewConstant.IMPORT_A_MENUITEM_INDEX:
                        importAMenuItemCSV();
                        break;
                    case MenuViewConstant.SAVE_MENUITEM_DATA_INDEX:
                        saveMenuItemData();
                        break;
                    case MenuViewConstant.GET_MENUITEM_DATA_INDEX:
                        getMenuItemData();
                        break;
                    case MenuViewConstant.BACK_MAIN_VIEW_INDEX:
                        flag = false;
                        break;
                    case MenuViewConstant.EXIT_INDEX:
                        System.exit(0);
                        break;
                    default:
                        System.out.println(Message.CHOICE_NOT_EXISTED);
                        stopScreen();
                }

            } catch (RuntimeException e) {
                logger.fatal("displayMain() - " + e);
                System.out.println(Message.WRONG_INPUT);
                scanner.nextLine();
                stopScreen();
            }
        }
    }
}
