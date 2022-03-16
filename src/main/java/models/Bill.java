package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Class of bill information (orderedTime, menu item and quantity of each)
 */
public class Bill implements Serializable {
    private double total;
    private int billId;
    private Map<MenuItem, Integer> menuItems;
    private LocalDateTime orderedTime;

    public int getBillId() {
        return billId;
    }

    public Bill(int billId) {
        menuItems = new LinkedHashMap<>();
        orderedTime = LocalDateTime.now();
        this.total = 0;
        this.billId = billId;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\nBillId: " + billId + "\t\t" +
                "orderedTime = " + orderedTime);
        int i = 0;
        result.append("\nMenu Items:");
        result.append(String.format("\n%-10s%-50s%-25s%-20s%-20s", "index", "name", "type", "price", "quantity"));
        for (Map.Entry<MenuItem, Integer> entry : menuItems.entrySet()) {
            result.append(String.format("\n%-10d%-50s%-25s%-20.2f%-20d", i, entry.getKey().getName(), entry.getKey().getMenuType(), entry.getKey().getPrice(), entry.getValue()));
            i++;
        }
        result.append(String.format("\n\nTotal:\t%.2f", total));
        return result.toString();
    }

    private void calculateTotalBill() {
        total = 0;
        for (Map.Entry<MenuItem, Integer> entry : menuItems.entrySet()) {
            total += (entry.getKey().price * entry.getValue());
        }
    }

    public Map<MenuItem, Integer> getMenuItems() {
        return menuItems;
    }

    public Bill(LinkedHashMap<MenuItem, Integer> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * add menu item into bill
     *
     * @param menuItem MenuItem need to add
     * @param quantity quantity od menu item
     */
    public void addMenuItem(MenuItem menuItem, int quantity) {
        if (menuItems.containsKey(menuItem)) {
            menuItems.replace(menuItem, menuItems.get(menuItem) + quantity);
        } else {
            menuItems.put(MenuItemFactory.createMenuItem(menuItem.getMenuType(), menuItem.getName(), menuItem.getDescription(), menuItem.getImage(), menuItem.getPrice()), quantity);
        }
        this.orderedTime = LocalDateTime.now();
        calculateTotalBill();
    }


    /**
     * add menu item into bill with string {index}-{quantity}
     *
     * @param menuItem     string index of menu + quantity of menuitem {index}-{quantity} with index is index of menu item in menu list
     * @param menuItemList List of MenuItem
     * @return 1 add succeeded, 0: index of Menu out of bound in menu list, -1: wrong format string
     */
    public int addMenuItem(String menuItem, List<MenuItem> menuItemList) {
        List<Integer> arguments = checkFormat(menuItem);
        if (arguments == null) {
            return -1;
        }
        if (arguments.get(0) < 0 || arguments.get(0) >= menuItemList.size()) {
            return 0;
        }
        addMenuItem(menuItemList.get(arguments.get(0)), arguments.get(1));
        return 1;
    }

    /**
     * delete menu item in bill
     *
     * @param deletedItem MenuItem need to delete
     * @return true if deleted successfully, false if MenuItem not existed
     */
    public boolean deleteMenuItem(MenuItem deletedItem) {
        if (menuItems.containsKey(deletedItem)) {
            menuItems.remove(deletedItem);
            this.orderedTime = LocalDateTime.now();
            calculateTotalBill();
            return true;
        }
        return false;
    }

    /**
     * delete menu item in bill
     *
     * @param itemsIndex index of item in bill
     * @return true if delete successfully, false if index out of bound
     */
    public boolean deleteMenuItem(int itemsIndex) {
        MenuItem deletedItem;
        List keys = new ArrayList(menuItems.keySet());
        try {
            deletedItem = (MenuItem) keys.get(itemsIndex);
        } catch (RuntimeException e) {
            return false;
        }
        return deleteMenuItem(deletedItem);
    }

    /**
     * update menu item in bill
     *
     * @param menu     MenuItem need to update
     * @param quantity updated quantity of menu item
     */
    public void updateMenuItem(MenuItem menu, int quantity) {
        if (menuItems.containsKey(menu)) {
            menuItems.replace(menu, quantity);
        } else {
            menuItems.put(menu, quantity);
        }
        this.orderedTime = LocalDateTime.now();
        calculateTotalBill();
    }

    public String getInfo() {
//
        return String.format("\tBillId = %s\torderedTime = %s", billId, orderedTime);
    }


    /**
     * update menuItem in bill with string {index}-{quantity}
     *
     * @param menuItem     string index of menu + quantity of menuitem {index}-{quantity}
     *                     with index is index of menu item in menu list
     * @param menuItemList list of all menu items in program
     * @return 1 update successfully, 0 index of menu item not existed on menuItemList,
     * -1 if menuItem string wrong format
     */
    public int updateMenuItem(String menuItem, List<MenuItem> menuItemList) {
        List<Integer> arguments = checkFormat(menuItem);
        if (arguments == null) {
            return -1;
        }
        if (arguments.get(0) < 0 || arguments.get(0) >= menuItemList.size()) {
            return 0;
        }
        List keys = new ArrayList(menuItems.keySet());
        MenuItem updatedItem = (MenuItem) keys.get(arguments.get(0));
        updateMenuItem(updatedItem, arguments.get(1));
        return 1;
    }

    /**
     * check format of string menuItem {index-quantity}
     *
     * @param menuItem string of menuItem {index-quantity}
     * @return true if true format, false if wrong format
     */
    private List<Integer> checkFormat(String menuItem) {
        int menuIndex, quantity;
        String[] items = menuItem.split("-");
        if (items.length != 2) {
            return null;
        }
        try {
            menuIndex = Integer.parseInt(items[0]);
            quantity = Integer.parseInt(items[1]);
            if (quantity < 0) {
                return null;
            }
        } catch (RuntimeException e) {
            return null;
        }
        return new ArrayList<Integer>(
                Arrays.asList(menuIndex, quantity));
    }

}
