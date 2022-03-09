package views;

import controllers.MenuManagement;
import models.MenuItem;
import models.MenuType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.MenuViewConstant;
import utils.Message;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuView {

    private static final Scanner scanner = new Scanner(System.in);

    public static MenuManagement menuManagement = new MenuManagement();

    private static final Logger logger = LogManager.getLogger(MenuView.class);

    private static HashMap<Integer, MenuType> menuSelectMenuType = new HashMap<>();

    private static void setUpMenuSelectMenuType() {
        int index = 1;
        for (Map.Entry<MenuType, List<MenuType>> entry : MenuManagement.menu.entrySet()) {
            for (MenuType type : entry.getValue()) {
                menuSelectMenuType.put(index, type);
                index++;
            }
        }
    }

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
                    System.out.println(Message.INPUT_DESCRIPTION);
                    description = scanner.nextLine();
                    System.out.println(Message.INPUT_IMAGE_URL);
                    image = scanner.nextLine();
                    System.out.println(Message.INPUT_PRICE);
                    boolean flag1 = true;
                    while (flag1) {
                        try {
                            price = scanner.nextDouble();
                            scanner.nextLine();
                            flag1 = false;
                        } catch (RuntimeException e) {
                            logger.fatal("addAMenuItem() - " + e);
                            scanner.nextLine();
                            System.out.println(Message.INPUT_AGAIN);
                        }
                    }
                    menuManagement.addMenuItem(menuSelectMenuType.get(select), name, description, image, price);
                    flag = false;
                } else {
                    System.out.println(Message.CHOICE_NOT_EXISTED);
                }
            } catch (RuntimeException e) {
                logger.fatal("addAMenuItem() - " + e);
                System.out.println(Message.SELECT_AGAIN);
                scanner.nextLine();
            }
        }

    }

    public void deleteAMenuItem() {
        System.out.println(Message.DELETE_A_MENUITEM);
        int indexMenu;
        printMenuItemList();
        System.out.println(Message.INPUT_INDEX_MENU);
        boolean flag = true;
        while (flag) {
            try {
                indexMenu = scanner.nextInt();
                MenuItem deletedMenu = menuManagement.deleteMenu(indexMenu);
                if (deletedMenu == null) {
                    System.out.println(Message.FAILED_WRONG_INDEX);
                    if (isContinue()) {
                        System.out.println(Message.SELECT_AGAIN);
                    } else {
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
                System.out.println(Message.INPUT_AGAIN);
            }
        }
    }

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

    private void updatePriceMenuItem(int selectedMenu) {
        double price = -1;
        while (price <= -1) {
            try {
                System.out.println(Message.INPUT_PRICE);
                price = scanner.nextDouble();
            } catch (RuntimeException e) {
                logger.fatal("updatePriceMenuItem() - " + e);
                System.out.println(Message.WRONG_INPUT);
            }
        }
        if (menuManagement.updateMenuPrice(price, selectedMenu)) {
            System.out.println(Message.SUCCESS);

        } else {
            System.out.println(Message.FAILED_WRONG_INDEX);
        }
    }

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

    public void updateAMenuItem() {
        System.out.println(Message.UPDATE_A_MENUITEM);

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
                    default:
                        System.out.println(Message.CHOICE_NOT_EXISTED);
                }
            } catch (RuntimeException e) {
                logger.fatal("updateAMenuItem() - " + e);
                scanner.nextLine();
                if (isContinue()) {
                    System.out.println(Message.SELECT_AGAIN);
                } else {
                    flag = false;
                }
            }
            if (!isContinue()) {
                flag = false;
            }
        }
    }

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

    public void displayAMenuItem() {
        System.out.println(Message.DISPLAY_A_MENUITEM);
        printAMenuItem();
        stopScreen();
    }

    private void printMenuItemList() {
        for (int i = 0; i < menuManagement.getMenuList().size(); i++) {
            System.out.println(i + ". " + menuManagement.getMenuName(i));
        }
    }

    public void displayMenuItemList() {
        System.out.println(Message.DISPLAY_MENUITEM_LIST);
        printMenuItemList();
        stopScreen();
    }

    private boolean isContinue() {
        int select;
        System.out.println(Message.PRESS_1_TO_CONTINUE);
        try {
            select = scanner.nextInt();
            return select == 1;
        } catch (RuntimeException e) {
            logger.fatal("isContinue() - " + e);
            return false;
        }
    }

    private void stopScreen() {
        System.out.println(Message.PRESS_TO_CONTINUE);
        scanner.nextLine();
    }

    public void exportAMenuItemCSV() {
        System.out.println(Message.EXPORT_MENUITEM_CSV);

    }

    public void importAMenuItemCSV() {
        System.out.println(Message.IMPORT_MENUITEM_CSV);
    }

    public void getMenuItemData() {
        System.out.println(Message.GET_MENU_ITEMS);
        try {
            File f = new File(MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            if (!f.exists()) {
                System.out.println(Message.NO_DATA);
            }
            FileInputStream is = new FileInputStream(MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            ObjectInputStream ois = new ObjectInputStream(is);
            List<MenuItem> items = (List<MenuItem>) ois.readObject();
            menuManagement.setMenuList(items);
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

    public void saveMenuItemData() {
        System.out.println(Message.SAVE_MENU_ITEMS);

        try {
            File myObj = new File(MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            myObj.createNewFile();
            FileOutputStream fos = new FileOutputStream(MenuViewConstant.FILE_NAME_MENU_ITEM_DATA);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(menuManagement.getMenuList());
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
                scanner.nextLine();
                stopScreen();
            }
        }
    }
}
