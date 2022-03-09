package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Bill implements Serializable {

    private HashMap<MenuItem, Integer> menuItems;
    private LocalDateTime orderedTime;

    public Bill() {
        menuItems = new HashMap<>();
        orderedTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Bill{" +
                "orderedTime=" + orderedTime +
                '}');
        int i = 0;
        for (Map.Entry<MenuItem, Integer> entry : menuItems.entrySet()) {
            result.append("\n").append(i).append(". ").append(entry.getKey().getName()).append("\t\t").append(entry.getValue());
            i++;
        }
        return result.toString();
    }

    public HashMap<MenuItem, Integer> getMenuItems() {
        return menuItems;
    }

    public Bill(HashMap<MenuItem, Integer> menuItems) {
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

    public void deleteMenuItem(MenuItem menuType) {
        if (menuItems.containsKey(menuType)) {
            menuItems.remove(menuType);
            this.orderedTime = LocalDateTime.now();
        }
    }

    public void updateMenuItem(MenuItem menu, int quantity) {
        if (menuItems.containsKey(menu)) {
            menuItems.replace(menu, quantity);
        } else {
            menuItems.put(menu, quantity);
        }
        this.orderedTime = LocalDateTime.now();
    }

}
