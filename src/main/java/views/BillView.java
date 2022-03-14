package views;

import controllers.BillManagement;
import controllers.MenuManagement;
import models.Bill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.BillViewConstant;
import utils.MainViewConstant;
import utils.Message;

import java.io.*;
import java.util.List;

import static utils.ScreenUtility.isContinue;
import static utils.ScreenUtility.stopScreen;
import static utils.ScreenUtility.scanner;


/**
 * The class of BillView: view of bill management
 *
 * @see BillManagement
 */
public class BillView {
    private static final Logger logger = LogManager.getLogger(BillView.class);

    private BillManagement billManagement = new BillManagement();

    /**
     * Add a bill
     */
    public void addBill() {
        System.out.println(Message.ADD_A_BILL);
        displayMenuList();
        int id = billManagement.addBill();
        addMenuItemIntoBill(id);
    }

    private void addMenuItemIntoBill(int id) {
        boolean flag = true;
        int addStatus;
        String input;
        scanner.nextLine();
        while (flag) {
            System.out.println(Message.INPUT_MENU_TO_ADD_INTO_BILL);
            input = scanner.nextLine();
            addStatus = billManagement.addMenuItemIntoBill(input, id);
            if (addStatus == -1) {
                System.out.println(Message.WRONG_FORMAT);
            } else if (addStatus == 0) {
                System.out.println(Message.INDEX_OUT_OF_BOUND);
            } else {
                System.out.println(Message.SUCCESS);
            }
            if (!isContinue()) {
                flag = false;
            }

        }
    }

    /**
     * delete a selected bill
     */
    public void deleteBill() {
        System.out.println(Message.DELETE_A_BILL);
        if (BillManagement.checkBillData()) {
            int indexBill;
            printBillList();

            boolean flag = true;
            while (flag) {
                System.out.println(Message.INPUT_INDEX_BILL);
                try {
                    indexBill = scanner.nextInt();
                    Bill deletedBill = billManagement.deleteBill(indexBill);
                    if (deletedBill == null) {
                        System.out.println(Message.FAILED_WRONG_INDEX);
                        scanner.nextLine();
                        if (!isContinue()) {
                            flag = false;
                        }
                    } else {
                        System.out.println(Message.SUCCESS);
                        scanner.nextLine();
                        stopScreen();
                        flag = false;
                    }
                } catch (RuntimeException e) {
                    logger.fatal("deleteBill() - " + e);
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
     * update a selected bill
     *
     * @see BillView#updateByAddMenuItem(int)
     * @see BillView#updateByDeleteMenuItem(int)
     * @see BillView#updateQuantityMenuItem(int)
     */
    public void updateBill() {
        System.out.println(Message.UPDATE_A_BILL);
        if (BillManagement.checkBillData()) {
            int selectedBill = printBill();
            int choice;
            boolean flag = true;

            while (flag) {
                System.out.println(Message.UPDATE_BILL_MENU);
                System.out.println(Message.INPUT_CHOICE);
                try {
                    choice = scanner.nextInt();
                    switch (choice) {
                        case BillViewConstant.UPDATE_BY_ADD_MENUITEM_INDEX:
                            updateByAddMenuItem(selectedBill);
                            break;
                        case BillViewConstant.UPDATE_BY_DELETE_MENUITEM_INDEX:
                            updateByDeleteMenuItem(selectedBill);
                            break;
                        case BillViewConstant.UPDATE_QUANTITY_MENUITEM_INDEX:
                            updateQuantityMenuItem(selectedBill);
                            break;
                        case BillViewConstant.UPDATE_BACK:
                            flag = false;
                            break;
                        default:
                            System.out.println(Message.CHOICE_NOT_EXISTED);
                    }
                } catch (RuntimeException e) {
                    logger.fatal("updateBill() - " + e);
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println(Message.NO_DATA);
            stopScreen();
        }
    }

    /**
     * update quantity of menu item in a bill
     *
     * @param selectedBill index of selected bill
     */
    private void updateQuantityMenuItem(int selectedBill) {

        boolean flag = true;
        int updatedStatus;
        String input;
        scanner.nextLine();
        while (flag) {
            System.out.println(Message.INPUT_MENU_TO_ADD_INTO_BILL);
            input = scanner.nextLine();
            updatedStatus = billManagement.updateBillByUpdateItem(input, selectedBill);
            if (updatedStatus == -1) {
                System.out.println(Message.WRONG_FORMAT);
            } else if (updatedStatus == 0) {
                System.out.println(Message.INDEX_OUT_OF_BOUND);
            } else {
                System.out.println(Message.SUCCESS);
                System.out.println(billManagement.displayBill(selectedBill));
            }
            if (!isContinue()) {
                flag = false;
            }
        }
    }

    /**
     * delete menu item in a bill
     *
     * @param selectedBill index of selected bill
     */
    private void updateByDeleteMenuItem(int selectedBill) {
        int indexItem;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println(Message.INPUT_INDEX_ITEM_IN_BILL);
                indexItem = scanner.nextInt();
                if (billManagement.updateBillByDeleteItem(indexItem, selectedBill)) {
                    System.out.println(Message.SUCCESS);
                    System.out.println(billManagement.displayBill(selectedBill));
                    flag = false;
                } else {
                    System.out.println(Message.FAILED_WRONG_INDEX);
                    if (!isContinue()) {
                        flag = false;
                    }
                }
            } catch (RuntimeException e) {
                logger.fatal("deleteBill() - " + e);
                scanner.nextLine();
            }
        }
    }

    /**
     * add menu item in a existed bill
     *
     * @param selectedBill index of selected bill
     */
    private void updateByAddMenuItem(int selectedBill) {
        displayMenuList();
        addMenuItemIntoBill(selectedBill);
        System.out.println(billManagement.displayBill(selectedBill));
    }

    private int printBill() {
        int indexBill = -1;
        printBillList();
        boolean flag = true;
        while (flag) {
            try {
                System.out.println(Message.INPUT_INDEX_BILL);
                indexBill = scanner.nextInt();
                String selectedBill = billManagement.displayBill(indexBill);
                if (selectedBill == null) {
                    System.out.println(Message.FAILED_WRONG_INDEX);
                } else {
                    System.out.println(selectedBill);
                    flag = false;
                }
            } catch (RuntimeException e) {
                logger.fatal("displayBill() - " + e);
                scanner.nextLine();
                System.out.println(Message.WRONG_INPUT);
            }
        }
        return indexBill;
    }

    /**
     * display information of a bill (ordered time and quantity of all menu items)
     */
    public void displayBill() {
        System.out.println(Message.DISPLAY_A_BILL);
        if (BillManagement.checkBillData()) {
            printBill();
            scanner.nextLine();
        } else {
            System.out.println(Message.NO_DATA);
        }
        stopScreen();
    }

    /**
     * print all bills in bill list
     */
    private void printBillList() {
        for (int i = 0; i < BillManagement.bills.size(); i++) {
            System.out.println(i + ". " + billManagement.displayBillInfo(i));
        }
    }

    /**
     * display bill list view
     */
    public void displayBillList() {
        scanner.nextLine();
        System.out.println(Message.DISPLAY_BILL_LIST);
        printBillList();
        stopScreen();
    }

    /**
     * display all menu items in menu list
     */
    private void displayMenuList() {
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
     * display main view of bill management
     */
    public void displayMain() {
        int choice;
        boolean flag = true;
        if (!MenuManagement.checkMenuData()&&!BillManagement.checkBillData()) {
            System.out.println(Message.NO_DATA_MENU);
            stopScreen();
            new MenuView().displayMain();
            return;
        }
        while (flag) {
            System.out.println(Message.BILL_VIEW_MENU);
            System.out.println(Message.INPUT_CHOICE);
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case BillViewConstant.ADD_BILL_INDEX:
                        addBill();
                        break;
                    case BillViewConstant.DELETE_BILL_INDEX:
                        deleteBill();
                        break;
                    case BillViewConstant.UPDATE_BILL_INDEX:
                        updateBill();
                        break;
                    case BillViewConstant.DISPLAY_A_BILL_INDEX:
                        displayBill();
                        break;
                    case BillViewConstant.DISPLAY_BILL_LIST_INDEX:
                        displayBillList();
                        break;
                    case BillViewConstant.GET_BILL_DATA_INDEX:
                        getBillData();
                        break;
                    case BillViewConstant.SAVE_BILL_DATA_INDEX:
                        saveBillData();
                        break;
                    case BillViewConstant.BACK_MAIN_VIEW_INDEX:
                        flag = false;
                        break;
                    case BillViewConstant.EXIT_INDEX:
                        System.exit(0);
                        break;
                    default:
                        System.out.println(Message.CHOICE_NOT_EXISTED);
                        scanner.nextLine();
                        stopScreen();
                }

            } catch (RuntimeException e) {
                logger.fatal(" displayMain()" + e);
                scanner.nextLine();
                System.out.println(Message.WRONG_INPUT);
                stopScreen();
            }
        }
    }

    /**
     * Save data of all bills in bill List in file
     */
    private void saveBillData() {
        System.out.println(Message.SAVE_BILLS);

        try {
            File theDir = new File(MainView.dataDirectory);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            File myObj = new File(MainView.dataDirectory + "//" + BillViewConstant.FILE_NAME_BILL_DATA);
            myObj.createNewFile();
            FileOutputStream fos = new FileOutputStream(MainView.dataDirectory + "//" + BillViewConstant.FILE_NAME_BILL_DATA);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(BillManagement.bills);
            // closing resources
            oos.close();
            fos.close();
            System.out.println(Message.SUCCESS);
            stopScreen();
            scanner.nextLine();
        } catch (IOException e) {
            logger.fatal("saveBillData() - " + e);
            System.out.println(Message.SAVE_DATA_FAILED);
            stopScreen();
        }
    }

    /**
     * Get data of all bills in bill List in file
     */
    private void getBillData() {
        System.out.println(Message.GET_BILLS);
        try {
            File f = new File(MainView.dataDirectory + "//" + BillViewConstant.FILE_NAME_BILL_DATA);
            if (!f.exists()) {
                System.out.println(Message.NO_DATA);
            }
            FileInputStream is = new FileInputStream(MainView.dataDirectory + "//" + BillViewConstant.FILE_NAME_BILL_DATA);
            ObjectInputStream ois = new ObjectInputStream(is);
            List<Bill> items = (List<Bill>) ois.readObject();
            billManagement.setBills(items);
            ois.close();
            is.close();
            System.out.println(Message.SUCCESS);
            scanner.nextLine();
            stopScreen();
        } catch (Exception e) {
            logger.fatal("getBillData() - " + e);
            scanner.nextLine();
            System.out.println(Message.GET_DATA_FAILED);
            stopScreen();

        }

    }
}
