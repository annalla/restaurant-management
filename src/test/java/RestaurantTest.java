import controllers.BillManagement;
import controllers.MenuManagement;
import models.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RestaurantTest {
    public static List<MenuItem> expectedMenuItemList = new ArrayList<>();
    public static MenuManagement menuManagement = new MenuManagement();

    @BeforeClass
    public static void setUp() {
        MenuItem menuItem1 = new LunchMenuItem("rice", "rice,...", "c://image1.jpg", 3.3);
        MenuItem menuItem2 = new BreakfastMenuItem("bread", "bread,egg,salad", "c://image2.jpg", 2.3);
        MenuItem menuItem3 = new DinnerMenuItem("noodle", "beef,noodle,vegetable", "c://image3.jpg", 7.3);
        MenuItem menuItem4 = new SoftDrinkMenuItem("pepsi", "...", "c://image4.jpg", 1.4);
        MenuItem menuItem5 = new AlcoholMenuItem("strongbow", "...", "c://image5.jpg", 1.3);
        expectedMenuItemList.add(menuItem1);
        expectedMenuItemList.add(menuItem2);
        expectedMenuItemList.add(menuItem3);
        expectedMenuItemList.add(menuItem4);
        expectedMenuItemList.add(menuItem5);

        //initiate menuManagement
        menuManagement.addMenuItem(MenuType.LunchMenu, "rice", "rice,...", "c://image1.jpg", 3.3);
        menuManagement.addMenuItem(MenuType.BreakfastMenu, "bread", "bread,egg,salad", "c://image2.jpg", 2.3);
        menuManagement.addMenuItem(MenuType.DinnerMenu, "noodle", "beef,noodle,vegetable", "c://image3.jpg", 7.3);
        menuManagement.addMenuItem(MenuType.SoftDrinkMenu, "pepsi", "...", "c://image4.jpg", 1.4);
        menuManagement.addMenuItem(MenuType.AlcoholMenu, "strongbow", "...", "c://image5.jpg", 1.3);

    }

    @Test
    public void testMenuManagement() {

        //test add MenuItem into restaurant
        Assert.assertArrayEquals(expectedMenuItemList.toArray(), MenuManagement.menuList.toArray());

        //test update MenuItem intoRestaurant
        menuManagement.updateMenuName("newPepsi",3);
        MenuItem updatedMenuItem4 = new SoftDrinkMenuItem("newPepsi", "...", "c://image4.jpg", 1.4);
        expectedMenuItemList.set(3,updatedMenuItem4);
        Assert.assertArrayEquals(expectedMenuItemList.toArray(), MenuManagement.menuList.toArray());

        menuManagement.updateMenuDescription("newDescription",3);
        MenuItem updatedDesMenuItem4 = new SoftDrinkMenuItem("newPepsi", "newDescription", "c://image4.jpg", 1.4);
        expectedMenuItemList.set(3,updatedDesMenuItem4);
        Assert.assertArrayEquals(expectedMenuItemList.toArray(), MenuManagement.menuList.toArray());

        menuManagement.updateMenuImage("c://newImage4.jpg",3);
        MenuItem updatedImgMenuItem4 = new SoftDrinkMenuItem("newPepsi", "newDescription", "c://newImage4.jpg", 1.4);
        expectedMenuItemList.set(3,updatedImgMenuItem4);
        Assert.assertArrayEquals(expectedMenuItemList.toArray(), MenuManagement.menuList.toArray());

        menuManagement.updateMenuPrice(1.9,3);
        MenuItem updatedPriceMenuItem4 = new SoftDrinkMenuItem("newPepsi", "newDescription", "c://newImage4.jpg", 1.9);
        expectedMenuItemList.set(3,updatedPriceMenuItem4);
        Assert.assertArrayEquals(expectedMenuItemList.toArray(), MenuManagement.menuList.toArray());

        menuManagement.updateMenuWithMoreProperties("newPepsi2-newDescription...--1.5",3);
        MenuItem updatedSomePropertiesMenuItem4 = new SoftDrinkMenuItem("newPepsi2", "newDescription...", "c://newImage4.jpg", 1.5);
        expectedMenuItemList.set(3,updatedSomePropertiesMenuItem4);
        Assert.assertArrayEquals(expectedMenuItemList.toArray(), MenuManagement.menuList.toArray());

        //test delete MenuItem in restaurant
        menuManagement.deleteMenu(0);
        MenuItem deletedMenu=new LunchMenuItem("rice", "rice,...", "c://image1.jpg", 3.3);
        expectedMenuItemList.remove(deletedMenu);
        Assert.assertArrayEquals(expectedMenuItemList.toArray(), MenuManagement.menuList.toArray());

        //test display
        String menuItem4String=menuManagement.displayMenu(3);
        String expectedMenuItem4="AlcoholMenu{name='strongbow', description='...', image='c://image5.jpg', price=1.3}";
        Assert.assertEquals(expectedMenuItem4,menuItem4String);
        System.out.println("Display a menu item:"+menuItem4String);
        //test Bill

    }
    @Test
    public void testBillManagement(){

        //add Bill
        BillManagement billManagement=new BillManagement();
        int id1 =billManagement.addBill();
        billManagement.addMenuItemIntoBill("1-4",id1);
        billManagement.addMenuItemIntoBill("1-2",id1);
        billManagement.addMenuItemIntoBill("2-5",id1);

        MenuItem menuItem2 = new BreakfastMenuItem("bread", "bread,egg,salad", "c://image2.jpg", 2.3);
        MenuItem menuItem3 = new DinnerMenuItem("noodle", "beef,noodle,vegetable", "c://image3.jpg",7.3);
        Map<MenuItem,Integer> expectedMenuItems=new LinkedHashMap<>();
        expectedMenuItems.put(menuItem2,6);
        expectedMenuItems.put(menuItem3,5);
        Assert.assertEquals(expectedMenuItems,billManagement.getBill(0).getMenuItems());

        //update
        billManagement.updateBillByUpdateItem("0-10",id1);
        Map<MenuItem,Integer> expectedUpdatedByUpdateQuantityMenuItems=new LinkedHashMap<>();
        expectedUpdatedByUpdateQuantityMenuItems.put(menuItem2,10);
        expectedUpdatedByUpdateQuantityMenuItems.put(menuItem3,5);
        Assert.assertEquals(expectedUpdatedByUpdateQuantityMenuItems,billManagement.getBill(0).getMenuItems());

        billManagement.updateBillByDeleteItem(0,id1);
        Map<MenuItem,Integer> expectedUpdatedByDeletedMenuItems=new LinkedHashMap<>();
        expectedUpdatedByDeletedMenuItems.put(menuItem3,5);
        Assert.assertEquals(expectedUpdatedByDeletedMenuItems,billManagement.getBill(0).getMenuItems());

        //display a bill

        String displayBill=billManagement.displayBill(0);
        System.out.println("Display a bill:"+displayBill);
        //deleteBill
        billManagement.deleteBill(0);
        Assert.assertEquals(BillManagement.bills.size(),0);

    }
}
