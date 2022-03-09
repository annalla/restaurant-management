package controllers;

import models.Bill;
import models.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.MenuView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillManagement {
    private static final Logger logger = LogManager.getLogger(BillManagement.class);

    private List<Bill> bills;

    public BillManagement() {
        bills = new ArrayList<>();
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public int addBill(HashMap<MenuItem, Integer> menuItems) {
        bills.add(new Bill(menuItems));
        return bills.size() - 1;
    }

    public List<Bill> getBills() {
        return bills;
    }

    /**
     * @param menuItem
     * @param index
     * @return 1 succeeded, -1 wrong format, 0: index of menu item out of bound
     */
    public int addMenuItemIntoBill(String menuItem, int index) {
        int menuIndex, quantity;
        String[] items = menuItem.split("-");
        if (items.length != 0) {
            return -1;
        }
        try {
            menuIndex = Integer.parseInt(items[0]);
            quantity = Integer.parseInt(items[1]);
            if (quantity < 0) {
                return -1;
            }
        } catch (RuntimeException e) {
            logger.fatal("addMenuItemIntoBill() - " + e);
            return -1;
        }
        MenuItem menuItem1 = MenuView.menuManagement.getMenu(index);
        if (menuItem1 == null) {
            return 0;
        }
        return addMenuItemIntoBill(menuItem1, quantity, index);
    }

    public int addMenuItemIntoBill(MenuItem menu, int quantity, int index) {
        try {
            bills.get(index).addMenuItem(menu, quantity);
            return 1;
        } catch (IndexOutOfBoundsException e) {
            logger.fatal("addMenuItemIntoBill() - " + e);
            return 0;
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
            logger.fatal("updateBillByDeleteItem() - " + e);
            return false;
        }
    }

    public boolean updateBillByDeleteItem(int itemsIndex, int index) {
        try {
            List keys = new ArrayList(bills.get(index).getMenuItems().keySet());
            MenuItem deletedItem = (MenuItem) keys.get(itemsIndex);
            return updateBillByDeleteItem(deletedItem, index);
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
        int menuIndex, quantity;
        String[] items = menuItem.split("-");
        if (items.length != 0) {
            return -1;
        }
        try {
            menuIndex = Integer.parseInt(items[0]);
            quantity = Integer.parseInt(items[1]);
            if (quantity < 0) {
                return -1;
            }
        } catch (RuntimeException e) {
            logger.fatal("updateBillByUpdateItem() - " + e);
            return -1;
        }
        try {
            List keys = new ArrayList(bills.get(index).getMenuItems().keySet());
            MenuItem updatedItem = (MenuItem) keys.get(menuIndex);
            if (updateBillByUpdateItem(updatedItem, quantity, index)) return 1;
            return 0;
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
}
