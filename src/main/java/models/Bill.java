package models;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Bill {

    private HashMap<MenuItem, Integer> menuItems;
    private LocalDateTime orderedTime;

    public Bill() {
        menuItems = new HashMap<>();
        orderedTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Bill{" +
                "menuItems=" + menuItems +
                ", orderedTime=" + orderedTime +
                '}';
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
        this.orderedTime=LocalDateTime.now();
    }
    public void deleteMenuItem(MenuItem menuType){
        if (menuItems.containsKey(menuType)) {
            menuItems.remove(menuType);
            this.orderedTime=LocalDateTime.now();
        }
    }
    public void updateMenuItem(MenuItem menu, int quantity){
        if (menuItems.containsKey(menu)) {
            menuItems.replace(menu, quantity);
        } else {
            menuItems.put(menu, quantity);
        }
        this.orderedTime=LocalDateTime.now();
    }
}
