package views;

import controllers.BillManagement;
import models.Bill;
import models.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.BillViewConstant;
import utils.MenuViewConstant;
import utils.Message;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class BillView {
    private static final Logger logger = LogManager.getLogger(BillView.class);
    private static final Scanner scanner = new Scanner(System.in);
    public static BillManagement billManagement = new BillManagement();

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
        while (flag) {
            System.out.println(Message.INPUT_MENU_TO_ADD_INTO_BILL);
            input = scanner.nextLine();
            addStatus = billManagement.addMenuItemIntoBill(input, id);
            if (addStatus == -1) {
                System.out.println(Message.WRONG_FORMAT);
            } else if (addStatus == 0) {
                System.out.println(Message.INDEX_OUT_OF_BOUND);
            }
            if (!isContinue()) {
                flag = false;
            }
        }
    }

    private void stopScreen() {
        System.out.println(Message.PRESS_TO_CONTINUE);
        scanner.nextLine();
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

    public void deleteBill() {
        System.out.println(Message.DELETE_A_BILL);
        int indexBill;
        printBillList();
        System.out.println(Message.INPUT_INDEX_BILL);
        boolean flag = true;
        while (flag) {
            try {
                indexBill = scanner.nextInt();
                Bill deletedBill = billManagement.deleteBill(indexBill);
                if (deletedBill == null) {
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
                logger.fatal("deleteBill() - " + e);
                scanner.nextLine();
                System.out.println(Message.INPUT_AGAIN);
            }
        }
    }
    public void updateBill(){
        System.out.println(Message.UPDATE_A_BILL);

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
                    default:
                        System.out.println(Message.CHOICE_NOT_EXISTED);
                }
            } catch (RuntimeException e) {
                logger.fatal("updateBill() - " + e);
                scanner.nextLine();
            }
            if (!isContinue()) {
                flag = false;
            }
        }
    }

    private void updateQuantityMenuItem(int selectedBill) {
        boolean flag = true;
        int addStatus;
        String input;
        while (flag) {
            System.out.println(Message.INPUT_MENU_TO_ADD_INTO_BILL);
            input = scanner.nextLine();
//            addStatus = billManagement.addMenuItemIntoBill(input, selectedBill);
//            if (addStatus == -1) {
//                System.out.println(Message.WRONG_FORMAT);
//            } else if (addStatus == 0) {
//                System.out.println(Message.INDEX_OUT_OF_BOUND);
//            }
//            if (!isContinue()) {
//                flag = false;
//            }
        }
    }

    private void updateByDeleteMenuItem(int selectedBill) {
        int indexItem;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println(Message.INPUT_INDEX_ITEM_IN_BILL);
                indexItem = scanner.nextInt();
                if(billManagement.updateBillByDeleteItem(indexItem,selectedBill)){
                    System.out.println(Message.SUCCESS);
                    flag=false;
                }
                else{
                    System.out.println(Message.FAILED_WRONG_INDEX);
                    if(!isContinue()){
                        flag=false;
                    }
                }
            } catch (RuntimeException e) {
                logger.fatal("deleteBill() - " + e);
                scanner.nextLine();
            }
        }
    }

    private void updateByAddMenuItem(int selectedBill) {
        addMenuItemIntoBill(selectedBill);
    }

    private int printBill(){
        int indexBill=-1;
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
                    stopScreen();
                    scanner.nextLine();
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
    public void displayBill() {
        System.out.println(Message.DISPLAY_A_BILL);
       printBill();
       stopScreen();
    }

    private void printBillList() {
        for (int i = 0; i < billManagement.getBills().size(); i++) {
            System.out.println(i + ". " + billManagement.displayBill(i));
        }
    }

    public void displayBillList() {
        System.out.println(Message.DISPLAY_BILL_LIST);
        printBillList();
        stopScreen();
    }

    private void displayMenuList() {
        for (int i = 0; i < MenuView.menuManagement.getMenuList().size(); i++) {
            System.out.println(i + ". " + MenuView.menuManagement.getMenuName(i) + "\t\t:\t" + MenuView.menuManagement.getMenuPrice(i));
        }
    }

    public void displayMain() {
        int choice;
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
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
                        stopScreen();
                }

            } catch (RuntimeException e) {
                logger.fatal(" displayMain()" + e);
                scanner.nextLine();
                System.out.println(Message.SELECT_AGAIN + "." + Message.PRESS_TO_CONTINUE);
                scanner.nextLine();
            }
        }
    }

    private void saveBillData() {
        System.out.println(Message.SAVE_BILLS);

        try {
            File myObj = new File(BillViewConstant.FILE_NAME_BILL_DATA);
            myObj.createNewFile();
            FileOutputStream fos = new FileOutputStream(BillViewConstant.FILE_NAME_BILL_DATA);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(billManagement.getBills());
            // closing resources
            oos.close();
            fos.close();
            System.out.println(Message.SUCCESS);
            stopScreen();
        } catch (IOException e) {
            logger.fatal("saveBillData() - " + e);
            System.out.println(Message.SAVE_DATA_FAILED);
            stopScreen();
        }
    }

    private void getBillData() {
        System.out.println(Message.GET_BILLS);
        try {
            File f = new File(BillViewConstant.FILE_NAME_BILL_DATA);
            if (!f.exists()) {
                System.out.println(Message.NO_DATA);
            }
            FileInputStream is = new FileInputStream(BillViewConstant.FILE_NAME_BILL_DATA);
            ObjectInputStream ois = new ObjectInputStream(is);
            List<Bill> items = (List<Bill>) ois.readObject();
            billManagement.setBills(items);
            ois.close();
            is.close();
            System.out.println(Message.SUCCESS);
            stopScreen();
        } catch (Exception e) {
            logger.fatal("getBillData() - " + e);
            System.out.println(Message.GET_DATA_FAILED);
            stopScreen();
        }

    }


}
