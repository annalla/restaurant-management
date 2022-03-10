package utils;

public class Message {

    public static final String MAIN_VIEW_MENU = "-------------------RESTAURANT-----------------------\n1. Menu Management\n2. Bill Management\n3. Exit";
    public static final String BILL_VIEW_MENU = "---------Bill Management---------------------\n1. Add a bill\n2. Delete a bill\n3. Update a bill\n4. Display a bill\n5. Display a bill's list\n6. Get bill data\n7. Save bill data\n8. Back\n9. Exit";
    public static final String MENU_VIEW_MENU = "---------Menu Management---------------------\n1. Add a menu item\n2. Delete a menu item\n3. Update a menu item\n4. Display a menu item\n5. Display a menu's list\n6. Export a menu item to CSV file\n7. Import a menu item to CSV file\n8. Get menu data\n9. Save menu data\n10. Back\n11. Exit";

    public static final String ADD_A_BILL = "1. Add a bill---------------------------";
    public static final String DELETE_A_BILL = "2. Delete a bill---------------------------";
    public static final String UPDATE_A_BILL = "3. Update a bill---------------------------";
    public static final String DISPLAY_A_BILL = "4. Display a bill---------------------------";
    public static final String DISPLAY_BILL_LIST = "5. Display bill's list---------------------------";
    public static final String GET_BILLS = "6. Get bill data---------------------------";
    public static final String SAVE_BILLS = "7. Save bill data---------------------------";
    public static final String UPDATE_BILL_MENU = "1. Update by add new menu item\n2. Update by delete item\n3. Update by modify quantity of item\n4. Back";


    public static final String ADD_A_MENUITEM = "1. Add a menu item---------------------------";
    public static final String DELETE_A_MENUITEM = "2. Delete a menu item---------------------------";
    public static final String UPDATE_A_MENUITEM = "3. Update a menu item---------------------------";
    public static final String DISPLAY_A_MENUITEM = "4. Display a menu item---------------------------";
    public static final String DISPLAY_MENUITEM_LIST = "5. Display menu's list---------------------------";
    public static final String EXPORT_MENUITEM_CSV = "5. Export a menu item to CSV file---------------------------";
    public static final String IMPORT_MENUITEM_CSV = "5. Import a menu item to CSV file---------------------------";
    public static final String GET_MENU_ITEMS = "8. Get menu data---------------------------";
    public static final String SAVE_MENU_ITEMS = "9. Save menu data---------------------------";
    public static final String UPDATE_MENU_ITEM_MENU = "1. Update name\n2. Update description\n3.Update image url\n4.Update price\n5. Update more than one property\n6. Back ";
    public static final String HEADER_MENU_LIST = "index\t\tname\t\tMenuType\t\tprice";

    public static final String SUCCESS = "Succeeded";
    public static final String FAILED = "Failed";
    public static final String SAVE_DATA_FAILED = "Save data failed";
    public static final String GET_DATA_FAILED = "Get data failed";
    public static final String NO_DATA = "No data";
    public static final String NO_DATA_MENU = "No data menu items. You should add menu";
    public static final String FAILED_WRONG_INDEX = "Failed due to wrong index";

    public static final String CHOICE_NOT_EXISTED = "Choice is not existed";
    public static final String INPUT_CHOICE = "Input choice: ";
    public static final String SELECT_AGAIN = "Select again.";


    public static final String WRONG_INPUT = "Wrong input";
    public static final String INPUT_AGAIN = "Input again: ";
    public static final String EMPTY_STRING = "Empty string: ";

    public static final String INPUT_INDEX_MENU = "Input index a menu:";
    public static final String INPUT_INDEX_BILL = "Input index a bill:";
    public static final String INPUT_INDEX_ITEM_IN_BILL = "Input index a item in bill:";
    public static final String INDEX_NOT_EXISTED = "Index is not existed";
    public static final String INPUT_NAME = "Input name:";
    public static final String INPUT_DESCRIPTION = "Input description:";
    public static final String INPUT_IMAGE_URL = "Input image url:";
    public static final String INPUT_PRICE = "Input price (price>=0):";
    public static final String CHOOSE_TYPE_MENU = "Choose type of a menu item:";
    public static final String INPUT_PROPERTIES = "Input follow format {name}-{description}-{imageUrl}-{price}(price>=0)(if keep stable just leaving space).Example: New Name-New description- -8.5";
    public static final String WRONG_FORMAT = "Wrong format";
    public static final String INPUT_MENU_TO_ADD_INTO_BILL = "Select index of menu items and quantity(format index-quantity(quantity>0).example: 0-3):";
    public static final String INDEX_OUT_OF_BOUND = "Index out of bound";
    public static final String INVALID_VALUE = "Invalid value";


    public static final String PRESS_TO_CONTINUE = "Press any key to continue";
    public static final String PRESS_1_TO_CONTINUE = "Press 1 to continue, another key to cancel.";


}
