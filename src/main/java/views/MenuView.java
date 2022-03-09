package views;

import controllers.MenuManagement;
import models.MenuItem;
import models.MenuType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import constants.MenuViewConstant;

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

    public void addAMenu() {

        String name, description, image;
        double price = 0;
        System.out.println("1.Add a menu---------------");
        boolean flag = true;
        int select;

        displayMenuType();
        System.out.println("Choose type of menu:");
        while (flag) {
            try {
                select = scanner.nextInt();
                scanner.nextLine();
                if (menuSelectMenuType.containsKey(select)) {
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
                            scanner.nextLine();
                            flag1 = false;
                        } catch (RuntimeException e) {
                            logger.fatal(e);
                            scanner.nextLine();
                            System.out.println("Input price again:");
                        }
                    }
                    menuManagement.createMenu(menuSelectMenuType.get(select), name, description, image, price);
                    flag = false;
                } else {
                    System.out.println("Wrong choice.Select again:");
                }
            } catch (RuntimeException e) {
                logger.fatal("Wrong input choice - " + e);
                System.out.println("Select again:");
                scanner.nextLine();
            }
        }

    }

    public void deleteAMenu() {
        System.out.println("2.Delete a menu---------------");
        int indexMenu;
        printMenuList();
        System.out.println("Input index a menu to delete:");
        boolean flag = true;
        while (flag) {
            try {
                indexMenu = scanner.nextInt();
                MenuItem deletedMenu = menuManagement.deleteMenu(indexMenu);
                if (deletedMenu == null) {
                    System.out.println("Delete menu failed due to wrong index.");
                    if (isContinue()) {
                        System.out.println("Select again:");
                    } else {
                        flag = false;
                    }

                } else {
                    System.out.println("Delete menu succeeded");
                    stopScreen();
                    scanner.nextLine();
                    flag = false;
                }
            } catch (RuntimeException e) {
                logger.fatal(e);
                scanner.nextLine();
                System.out.println("Input again:");
            }
        }
    }

    private void updateNameMenu(int selectedMenu) {
        scanner.nextLine();
        System.out.println("1. Input name");
        String name = scanner.nextLine();
        while (name.length() == 0) {
            System.out.println("Empty String. Input again:");
            name = scanner.nextLine();
        }
        if (menuManagement.updateMenuName(name, selectedMenu)) {
            System.out.println("Update name succeeded");
        } else {
            System.out.println("Update failed due to wrong index");
        }
    }
    private void updatePriceMenu(int selectedMenu) {
        double price=-1;
        while (price<=-1){
            try {
                System.out.println("1. Input price (Price>=0)");
                price = scanner.nextDouble();
            }
            catch (RuntimeException e){
                logger.fatal(e);
                System.out.println("Wrong input");
            }
        }
        if (menuManagement.updateMenuPrice(price, selectedMenu)) {
            System.out.println("Update price succeeded");
        } else {
            System.out.println("Update failed due to wrong index");
        }
    }
    private void updateDescriptionMenu(int selectedMenu) {
        scanner.nextLine();
        System.out.println("1. Input description:");
        String description = scanner.nextLine();
        while (description.length() == 0) {
            System.out.println("Empty String. Input again:");
            description = scanner.nextLine();
        }
        if (menuManagement.updateMenuDescription(description, selectedMenu)) {
            System.out.println("Update description succeeded");
        } else {
            System.out.println("Update failed due to wrong index");
        }
    }
    private void updateImageMenu(int selectedMenu) {
        scanner.nextLine();
        System.out.println("1. Input image url:");
        String image = scanner.nextLine();
        while (image.length() == 0) {
            System.out.println("Empty String. Input again:");
            image = scanner.nextLine();
        }
        if (menuManagement.updateMenuImage(image, selectedMenu)) {
            System.out.println("Update image succeeded");
        } else {
            System.out.println("Update failed due to wrong index");
        }
    }

    // TODO: 3/8/2022
    private void updateAMenuWithProperties(int selectedMenu){
        
    }
    public void updateAMenu() {
        System.out.println("3.Update a menu---------------");

        int selectedMenu = printAMenu();
        int choice;
        boolean flag = true;

        while (flag) {
            System.out.println("1. Update name");
            System.out.println("2. Update description");
            System.out.println("3. Update image url");
            System.out.println("4. Update price");
            System.out.println("5. Update more than one properties");
            System.out.println("Input choice:");
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case MenuViewConstant.UPDATE_NAME:
                        updateNameMenu(selectedMenu);
                        break;
                    case MenuViewConstant.UPDATE_DESCRIPTION:
                        updateDescriptionMenu(selectedMenu);
                        break;
                    case MenuViewConstant.UPDATE_IMAGE:
                        updateImageMenu(selectedMenu);
                        break;
                    case MenuViewConstant.UPDATE_PRICE:
                        updatePriceMenu(selectedMenu);
                        break;
                    case MenuViewConstant.UPDATE_MORE:
                        updateAMenuWithProperties(selectedMenu);
                        break;
                    default:
                        System.out.println("Wrong choice.");
                }
            } catch (RuntimeException e) {
                logger.fatal(e);
                scanner.nextLine();
                if (isContinue()) {
                    System.out.println("Select again:");
                } else {
                    flag = false;
                }
            }
            if (!isContinue()) {
                flag = false;
            }
        }
    }

    private int printAMenu() {
        int indexMenu = -1;
        printMenuList();
        System.out.println("Input index a menu to display:");
        boolean flag = true;
        while (flag) {
            try {
                indexMenu = scanner.nextInt();
                scanner.nextLine();
                String selectedMenu = menuManagement.displayMenu(indexMenu);
                if (selectedMenu == null) {
                    System.out.println("Wrong index");
                } else {
                    System.out.println(selectedMenu);
                    flag = false;
                }
            } catch (RuntimeException e) {
                logger.fatal(e);
                scanner.nextLine();
                System.out.println("Input again:");
            }
        }
        return indexMenu;
    }

    public void displayAMenu() {
        System.out.println("4.Display a menu---------------");
        printAMenu();
        stopScreen();
    }

    private void printMenuList() {
        for (int i = 0; i < menuManagement.getMenuList().size(); i++) {
            System.out.println(i + ". " + menuManagement.getMenuName(i));
        }
    }

    public void displayMenuList() {
        System.out.println("5. Display menu's List-------------");
        printMenuList();
        stopScreen();
    }

    private boolean isContinue() {
        int select;
        System.out.println("Press 1 to continue, another key to cancel.");
        try {
            select = scanner.nextInt();
            return select == 1 ? true : false;
        } catch (RuntimeException e) {
            logger.fatal("isContinue() exception: "+e);
            return false;
        }
    }

    private void stopScreen() {
        System.out.println("Press any key to continue");
        scanner.nextLine();
    }

    public void exportAMenuCSV() {
        System.out.println("6.Export A menu into CSV file---------------");

    }

    public void importAMenuCSV() {
        System.out.println("7.Import A menu from CSV file---------------");
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
            System.out.println("8. Get menu data");
            System.out.println("9. Save menu data");
            System.out.println("10. Back");
            System.out.println("11. Exit");
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
                        break;
                    case MenuViewConstant.EXIT:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong choice!");
                        stopScreen();
                }

            } catch (RuntimeException e) {
                logger.fatal("DisplayMain() - " + e);
                scanner.nextLine();
                stopScreen();
            }
        }
    }
}
