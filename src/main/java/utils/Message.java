package utils;

public class Message {

    public static final String MAIN_VIEW_MENU = "\n-------------------RESTAURANT-----------------------\n\n1. Menu Management\n2. Bill Management\n3. Exit";
    public static final String BILL_VIEW_MENU = "\n---------Bill Management---------------------\n\n1. Add a bill\n2. Delete a bill\n3. Update a bill\n4. Display a bill\n5. Display a bill's list\n6. Get bill data\n7. Save bill data\n8. Back\n9. Exit";
    public static final String MENU_VIEW_MENU = "\n---------Menu Management---------------------\n\n1. Add a menu item\n2. Delete a menu item\n3. Update a menu item\n4. Display a menu item\n5. Display a menu's list\n6. Export a menu item to CSV file\n7. Import a menu item to CSV file\n8. Get menu data\n9. Save menu data\n10. Back\n11. Exit";

    public static final String ADD_A_BILL = "\n1. Add a bill---------------------------\n";
    public static final String DELETE_A_BILL = "\n2. Delete a bill---------------------------\n";
    public static final String UPDATE_A_BILL = "\n3. Update a bill---------------------------\n";
    public static final String DISPLAY_A_BILL = "\n4. Display a bill---------------------------\n";
    public static final String DISPLAY_BILL_LIST = "\n5. Display bill's list---------------------------\n";
    public static final String GET_BILLS = "\n6. Get bill data---------------------------\n";
    public static final String SAVE_BILLS = "\n7. Save bill data---------------------------\n";
    public static final String UPDATE_BILL_MENU = "\n1. Update by add new menu item\n2. Update by delete item\n3. Update by modify quantity of item\n4. Back\n";


    public static final String ADD_A_MENUITEM = "\n1. Add a menu item---------------------------";
    public static final String DELETE_A_MENUITEM = "\n2. Delete a menu item---------------------------";
    public static final String UPDATE_A_MENUITEM = "\n3. Update a menu item---------------------------";
    public static final String DISPLAY_A_MENUITEM = "\n4. Display a menu item---------------------------";
    public static final String DISPLAY_MENUITEM_LIST = "\n5. Display menu's list---------------------------";
    public static final String EXPORT_MENUITEM_CSV = "\n5. Export a menu item to CSV file---------------------------";
    public static final String IMPORT_MENUITEM_CSV = "\n5. Import a menu item to CSV file---------------------------";
    public static final String GET_MENU_ITEMS = "\n8. Get menu data---------------------------";
    public static final String SAVE_MENU_ITEMS = "\n9. Save menu data---------------------------";
    public static final String UPDATE_MENU_ITEM_MENU = "\n1. Update name\n2. Update description\n3. Update image url\n4. Update price\n5. Update more than one property\n6. Back\n ";
    public static final String HEADER_MENU_LIST = String.format("\n%-10s%-50s%-25s%-20s","index","name","MenuType","price");

    public static final String SUCCESS = "\nSucceeded";
    public static final String FAILED = "\nFailed";
    public static final String SAVE_DATA_FAILED = "\nSave data failed";
    public static final String GET_DATA_FAILED = "\nGet data failed";
    public static final String NO_DATA = "\nNo data";
    public static final String NO_DATA_MENU = "\nNo data menu items. You should add menu";
    public static final String FAILED_WRONG_INDEX = "\nFailed due to wrong index";
    public static final String CREATED_FILE_FAILED = "\nCreated file failed";

    public static final String CHOICE_NOT_EXISTED = "\nChoice is not existed";
    public static final String INPUT_CHOICE = "\nInput choice: ";
    public static final String SELECT_AGAIN = "Select again.";


    public static final String WRONG_INPUT = "\nWrong input";
    public static final String INPUT_AGAIN = "\nInput again: ";
    public static final String EMPTY_STRING = "\nEmpty string ";
    public static final String EXISTED_NAME = "\nExisted name ";

    public static final String INPUT_INDEX_MENU = "\nInput index a menu:";
    public static final String INPUT_INDEX_BILL = "\nInput index a bill:";
    public static final String INPUT_INDEX_ITEM_IN_BILL = "\nInput index a item in bill:";
    public static final String INDEX_NOT_EXISTED = "\nIndex is not existed";
    public static final String INPUT_NAME = "\nInput name:";
    public static final String INPUT_DESCRIPTION = "\nInput description:";
    public static final String INPUT_IMAGE_URL = "\nInput image url:";
    public static final String INPUT_PRICE = "\nInput price (price>=0):";
    public static final String CHOOSE_TYPE_MENU = "\nChoose type of a menu item:";
    public static final String INPUT_PROPERTIES = "\nInput follow format {name}-{description}-{imageUrl}-{price}(price>=0)(if keep stable just leaving space).Example: New Name-New description- -8.5";
    public static final String WRONG_FORMAT = "\nWrong format";
    public static final String INPUT_MENU_TO_ADD_INTO_BILL = "\nSelect index of menu items and quantity(format index-quantity(quantity>0).example: 0-3):";
    public static final String INVALID_VALUE = "\nInvalid value";

    public static final String PRESS_TO_CONTINUE = "\nPress any key to continue";
    public static final String PRESS_1_TO_CONTINUE = "\nPress 1 to continue, another key to cancel.";


}
