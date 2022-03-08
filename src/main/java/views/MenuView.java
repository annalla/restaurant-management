package views;

import controllers.MenuManagement;
import models.MenuItem;
import models.MenuType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.MenuViewConstant;


import java.awt.*;
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

    public void addAMenu() {
        String name, description, image;
        double price = 0;

        System.out.println("1. Add a menu----------------");
        System.out.println("Input name:");
        name = scanner.nextLine();
        System.out.println("Input description:");
        description = scanner.nextLine();
        System.out.println("Input image url:");
        image = scanner.nextLine();
        System.out.println("Input price:");
        boolean flag1 = true;
        while (flag1) {
            try {
                price = scanner.nextDouble();
                flag1 = false;
            } catch (RuntimeException e) {
                logger.fatal(e);
                System.out.println("Input price again:");
            }
        }

        boolean flag = true;
        int index = 1;

        for (Map.Entry<MenuType, List<MenuType>> entry : MenuManagement.menu.entrySet()) {
            System.out.println(entry.getKey());
            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.println("\t" + index + ". " + entry.getValue().get(i));
                index++;
            }
        }

        int select;

        System.out.println("Choose type of menu:");
        while (flag) {
            try {
                select = scanner.nextInt();
                if (menuSelectMenuType.containsKey(select)) {
                    menuManagement.createMenu(menuSelectMenuType.get(select), name, description, image, price);
                    flag = false;
                } else {
                    System.out.println("Wrong choice.Select again:");
                }
            } catch (RuntimeException e) {
                logger.fatal("Wrong input choice - " + e);
                scanner.nextLine();
                System.out.println("Select again:");
            }
        }

    }

    public void deleteAMenu() {
        int indexMenu;
        displayMenuList();
        System.out.println("Input index a menu to delete:");
        boolean flag = true;
        while (flag) {
            try {
                indexMenu = scanner.nextInt();
                MenuItem deletedMenu= menuManagement.deleteMenu(indexMenu);
                if(deletedMenu==null){
                    System.out.println("Delete menu failed due to wrong index");
                }
                else{
                    System.out.println("Delete menu s");
                    flag = false;
                }
            } catch (RuntimeException e) {
                logger.fatal(e);
                System.out.println("Input again:");
            }
        }
    }

    public void updateAMenu() {

    }

    public void displayAMenu() {

    }

    public void displayMenuList() {
        System.out.println("5. Display a menu list------------");
        for (int i = 0; i < menuManagement.getMenuList().size(); i++) {
            System.out.println(i+menuManagement.displayMenu(i));
        }
        System.out.println("Press any key to continue");
        scanner.nextLine();
    }

    public void exportAMenuCSV() {

    }

    public void importAMenuCSV() {

    }

    public void displayMain() {

        MenuManagement.setUpMenu();
        setUpMenuSelectMenuType();
        int choice;
        boolean flag = true;

        while (flag) {

            System.out.println("---------Menu Management---------------------");
            System.out.println("1. Add a menu");
            System.out.println("2. Delete a menu");
            System.out.println("3. Update a menu");
            System.out.println("4. Display a menu");
            System.out.println("5. Display menu's List");
            System.out.println("6. Export a menu to CSV file");
            System.out.println("7. Import a menu from CSV file");
            System.out.println("8. Back");
            System.out.println("9. Exit");
            System.out.println("Choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case MenuViewConstant.ADD_MENU:
                        addAMenu();
                        break;
                    case MenuViewConstant.DELETE_MENU:
                        deleteAMenu();
                        break;
                    case MenuViewConstant.UPDATE_MENU:
                        updateAMenu();
                        break;
                    case MenuViewConstant.DISPLAY_A_MENU:
                        displayAMenu();
                        break;
                    case MenuViewConstant.DISPLAY_MENU_LIST:
                        displayMenuList();
                        break;
                    case MenuViewConstant.EXPORT_A_MENU:
                        exportAMenuCSV();
                        break;
                    case MenuViewConstant.IMPORT_A_MENU:
                        importAMenuCSV();
                        break;
                    case MenuViewConstant.BACK_MAIN_VIEW:
                        flag = false;
                        new MainView().displayMainView();
                        break;
                    case MenuViewConstant.EXIT:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong choice!!Select again!!Press any key to continue");
                        scanner.nextLine();
                }

            } catch (RuntimeException e) {
                logger.fatal("Wrong input choice - " + e);
                scanner.nextLine();
                System.out.println("Select again. Press any key to continue");
                scanner.nextLine();
            }
        }
    }
}
