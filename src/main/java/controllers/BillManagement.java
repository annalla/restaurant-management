package controllers;

import models.Bill;
import models.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.MenuView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class BillManagement {
    private static final Logger logger = LogManager.getLogger(BillManagement.class);

    public static List<Bill> bills;

    public BillManagement() {
        bills = new ArrayList<>();
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public int addBill(LinkedHashMap<MenuItem, Integer> menuItems) {
        bills.add(new Bill(menuItems));
        return bills.size() - 1;
    }


    /**
     * @param menuItem
     * @param index
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


    public int addBill() {
        bills.add(new Bill());
        return bills.size() - 1;
    }

    public boolean checkBillData() {
        return bills.size() != 0;
    }

    public Bill deleteBill(int index) {
        try {
            return bills.remove(index);
        } catch (RuntimeException e) {
            logger.fatal("Update bill failed due to " + e);
            return null;
        }
    }

    public boolean updateBillByDeleteItem(int itemsIndex, int index) {
        try {
            return bills.get(index).deleteMenuItem(itemsIndex);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("updateBillByDeleteItem() - " + e);
            return false;
        }
    }

    /**
     * @param menuItem
     * @param index
     * @return 1 succeeded, -1:wrong format, 0: index out of bound
     */
    public int updateBillByUpdateItem(String menuItem, int index) {
        try {
            return bills.get(index).updateMenuItem(menuItem,MenuManagement.menuList);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("updateBillByDeleteItem() - " + e);
            return 0;
        }
    }

    public boolean updateBillByUpdateItem(MenuItem menu, int quantity, int index) {
        try {
            bills.get(index).updateMenuItem(menu, quantity);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("updateBillByUpdateItem() - " + e);
            return false;
        }
    }

    public String displayBill(int index) {
        try {
            return bills.get(index).toString();
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("displayBIll() " + e);
            return null;
        }
    }

    public Bill getBill(int index) {
        try {
            return bills.get(index);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("getBill() - " + e);
            return null;
        }
    }

    public String displayBillInfo(int index) {
        try {
            return bills.get(index).getInfo();
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("displayBill() " + e);
            return null;
        }
    }
}
