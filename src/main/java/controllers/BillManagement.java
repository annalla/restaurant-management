package controllers;

import models.Bill;
import models.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.BillViewConstant;
import views.MainView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class of BillManagement: manage (add, delete, update, display) bills in program
 */
public class BillManagement {
    private static final Logger logger = LogManager.getLogger(BillManagement.class);
    private static int nextBillId = 1;
    public static List<Bill> bills = new ArrayList<>();

    public BillManagement() {
    }

    public static void updateNextBillId() {
        if (bills.size() != 0) {
            int lastId = bills.get(bills.size() - 1).getBillId();
            if (lastId > nextBillId) {
                nextBillId = lastId + 1;
            }
        }

    }

    public void setBills(List<Bill> bills) {
        for(Bill bill :bills){
            if(!checkBillExist(bill)){
                BillManagement.bills.add(bill);
            }
        }
    }

    private boolean checkBillExist(Bill bill) {
        for (Bill bill1:bills) {
            if(bill1.getBillId()==bill.getBillId()){
                return true;
            }
        }
        return false;
    }

    /**
     * add a MenuItem into bill
     *
     * @param menuItem string of menuitem {index}-{quantity}
     * @param index    index of bill in bill list
     * @return 1 succeeded, -1 wrong format, 0: index of menu item out of bound
     */
    public int addMenuItemIntoBill(String menuItem, int index) {
        try {
            return bills.get(index).addMenuItem(menuItem, MenuManagement.menuList);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("addMenuItemIntoBill() - " + e);
            return 0;
        }
    }

    /**
     * add bill into bill list
     *
     * @return index of added bill in bill list
     */
    public int addBill() {
        bills.add(new Bill(nextBillId));
        nextBillId++;
        return bills.size() - 1;
    }

    /**
     * delete bill in bill list
     *
     * @param index index of bill in bill list
     * @return deletedBill if succeeded, null if failed
     */
    public Bill deleteBill(int index) {
        try {
            return bills.remove(index);
        } catch (RuntimeException e) {
            logger.fatal("Update bill failed due to " + e);
            return null;
        }
    }

    /**
     * update bill by deleting menu item
     *
     * @param itemsIndex index of menu items in the bill
     * @param index      index of bill in bill list
     * @return true if succeeded, false if failed
     */
    public boolean updateBillByDeleteItem(int itemsIndex, int index) {
        try {
            return bills.get(index).deleteMenuItem(itemsIndex);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("updateBillByDeleteItem() - " + e);
            return false;
        }
    }

    /**
     * update bill by update a MenuItem
     *
     * @param menuItem string of menuitem {index}-{quantity}
     * @param index    index of bill in bill list
     * @return 1 succeeded, -1:wrong format, 0: index out of bound
     */
    public int updateBillByUpdateItem(String menuItem, int index) {
        try {
            return bills.get(index).updateMenuItem(menuItem, MenuManagement.menuList);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("updateBillByDeleteItem() - " + e);
            return 0;
        }
    }

    /**
     * update bill in bill list
     *
     * @param menu     MenuItem need to update
     * @param quantity quantity of MenuItem in bill list
     * @param index    index of bill in bill list
     * @return true if succeeded, false if index out of bound
     */
    public boolean updateBillByUpdateItem(MenuItem menu, int quantity, int index) {
        try {
            bills.get(index).updateMenuItem(menu, quantity);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("updateBillByUpdateItem() - " + e);
            return false;
        }
    }

    /**
     * get string of bill's information
     *
     * @param index index of bill in bill list
     * @return String if succeeded, null if index out of bound
     */
    public String displayBill(int index) {
        try {
            return bills.get(index).toString();
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("displayBill() " + e);
            return null;
        }
    }

    /**
     * get Bill in bill list
     *
     * @param index index of bill in bil list
     * @return Bill if succeeded, false if index out of bound
     */
    public Bill getBill(int index) {
        try {
            return bills.get(index);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("getBill() - " + e);
            return null;
        }
    }

    /**
     * get string of bill information
     *
     * @param index index of bill in bill list
     * @return string of information if succeeded, null if failed
     */
    public String displayBillInfo(int index) {
        try {
            return bills.get(index).getInfo();
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("displayBill() " + e);
            return null;
        }
    }

    public static boolean checkBillData() {
        try {
            return bills.size() != 0;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Save bill list in  data/bill_list.bin
     *
     * @return true if save bill list successfully, false if failed
     * @throws IOException
     */
    public boolean saveBillData() throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fos=null;
        try {
            File theDir = new File(MainView.dataDirectory);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            File myObj = new File(MainView.dataDirectory + "//" + BillViewConstant.FILE_NAME_BILL_DATA);
            myObj.createNewFile();
            fos = new FileOutputStream(MainView.dataDirectory + "//" + BillViewConstant.FILE_NAME_BILL_DATA);
            oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(BillManagement.bills);
            // closing resources
            return true;

        } catch (IOException e) {
            return false;
        }
        finally {
            if(oos!=null){
                oos.close();
            }
            if(fos!=null){
                fos.close();
            }
        }
    }
    /**
     * Get bill list in data/bill_list.bin
     *
     * @return 1 if get bill list successfully, -1 if failed, 0 if file not existed
     * @throws IOException
     */
    public int getBillData() throws IOException {
        FileInputStream is=null;
        ObjectInputStream ois=null;
        try {
            File f = new File(MainView.dataDirectory + "//" + BillViewConstant.FILE_NAME_BILL_DATA);
            if (!f.exists()) {
                return 0;
            }
            is = new FileInputStream(MainView.dataDirectory + "//" + BillViewConstant.FILE_NAME_BILL_DATA);
           ois = new ObjectInputStream(is);
            List<Bill> items = (List<Bill>) ois.readObject();
            setBills(items);
            return 1;
        } catch (Exception e) {
            return -1;
        }
        finally {
            if(ois!=null){
                ois.close();
            }
            if(is!=null){
                is.close();
            }
        }
    }
}
