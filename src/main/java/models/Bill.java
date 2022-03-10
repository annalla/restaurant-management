package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Bill implements Serializable {

    private Map<MenuItem, Integer> menuItems;
    private LocalDateTime orderedTime;

    public Bill() {
        menuItems = new LinkedHashMap<>();
        orderedTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Bill{" +
                "orderedTime=" + orderedTime +
                '}');
        int i = 0;
        result.append("\nMenu Items (name-type-quantity):");
        for (Map.Entry<MenuItem, Integer> entry : menuItems.entrySet()) {
            result.append("\n\t").append(i).append(". ").append(entry.getKey().getName()).append("\t\t").append(entry.getKey().getMenuType()).append("\t\t").append(entry.getValue());
            i++;
        }
        return result.toString();
    }

    public Map<MenuItem, Integer> getMenuItems() {
        return menuItems;
    }

    public Bill(LinkedHashMap<MenuItem, Integer> menuItems) {
        this.menuItems = menuItems;
    }

    public void addMenuItem(MenuItem menuType, int quantity) {
        if (menuItems.containsKey(menuType)) {
            menuItems.replace(menuType, menuItems.get(menuType) + quantity);
        } else {
            menuItems.put(menuType, quantity);
        }
        this.orderedTime = LocalDateTime.now();
    }

    public boolean deleteMenuItem(MenuItem deletedItem) {
        if (menuItems.containsKey(deletedItem)) {
            menuItems.remove(deletedItem);
            this.orderedTime = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public void updateMenuItem(MenuItem menu, int quantity) {
        if (menuItems.containsKey(menu)) {
            menuItems.replace(menu, quantity);
        } else {
            menuItems.put(menu, quantity);
        }
        this.orderedTime = LocalDateTime.now();
    }

    public String getInfo() {
        return "Bill{" + "orderedTime=" + orderedTime + "}";
    }

    /**
     *
     * @param menuItem string index of menu + quantity of menuitem {index}-{quantity}
     * @param menuItemList List of MenuItem
     * @return 1 add succeeded, 0: index of Menu out of bound in menu list, -1: wrong format string
     */
    public int addMenuItem(String menuItem, List<MenuItem> menuItemList) {
        List<Integer> arguments=checkFormat(menuItem);
        if (arguments == null) {
            return -1;
        }
        if(arguments.get(0)<0||arguments.get(0)>=menuItemList.size()){
            return 0;
        }
        addMenuItem(menuItemList.get(arguments.get(0)),arguments.get(1));
        return 1;
    }
    public int updateMenuItem(String menuItem, List<MenuItem> menuItemList) {
        List<Integer> arguments=checkFormat(menuItem);
        if (arguments == null) {
            return -1;
        }
        if(arguments.get(0)<0||arguments.get(0)>=menuItemList.size()){
            return 0;
        }
        List keys = new ArrayList(menuItems.keySet());
        MenuItem updatedItem = (MenuItem) keys.get(arguments.get(0));
        updateMenuItem(updatedItem,arguments.get(1));
        return 1;
    }

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

    public boolean deleteMenuItem(int itemsIndex) {
        MenuItem deletedItem;
        List keys = new ArrayList(menuItems.keySet());
        try {
            deletedItem = (MenuItem) keys.get(itemsIndex);
        }
        catch (RuntimeException e){
            return false;
        }
        return deleteMenuItem(deletedItem);
    }


}
