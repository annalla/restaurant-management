package controllers;

import models.Bill;
import models.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillManagement {
    private static final Logger logger = LogManager.getLogger(BillManagement.class);

    private List<Bill> bills;

    public BillManagement() {
        bills = new ArrayList<>();
    }

    public int addBill(HashMap<MenuItem, Integer> menuItems) {
        bills.add(new Bill(menuItems));
        return bills.size() - 1;
    }

    public void addMenuItemIntoBill(MenuItem menu, int quantity, int index) {
        try {
            bills.get(index).addMenuItem(menu, quantity);

        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Add MenuItem into Bill failed due to " + e);

        }
    }

    public int addBill() {
        bills.add(new Bill());
        return bills.size() - 1;
    }

    public Bill deleteBill(int index) {
        try {
            return bills.remove(index);
        } catch (RuntimeException e) {
            logger.fatal("Update bill failed due to " + e);
            return null;
        }
    }

    public boolean updateBillByDeleteItem(MenuItem menu, int index) {
        try {
            bills.get(index).deleteMenuItem(menu);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Update bill failed due to " + e);
            return false;
        }
    }

    public boolean updateBillByUpdateItem(MenuItem menu, int quantity, int index) {
        try {
            bills.get(index).updateMenuItem(menu, quantity);
            return true;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Update bill failed due to " + e);
            return false;
        }
    }

    public String displayBill(int index) {
        try {
            return bills.get(index).toString();
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Get string display bill failed due to " + e);
            return null;
        }
    }

    public Bill getBill(int index) {
        try {
            return bills.get(index);
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("Get detailed Bill failed due to" + e);
            return null;
        }
    }
}
