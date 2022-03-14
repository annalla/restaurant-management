package views;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.MainViewConstant;
import utils.Message;


import static utils.ScreenUtility.scanner;

/**
 * The class of MainView: View of main view
 *
 * @see BillView
 * @see MenuView
 */
public class MainView {
    private static final Logger logger = LogManager.getLogger(MainView.class);
    public static final String dataDirectory = System.getProperty("user.dir") + MainViewConstant.FOLDER_NAME_DATA;

    /**
     * Display main view of program
     */
    public void displayMainView() {

        int choice;
        while (true) {
            System.out.println(Message.MAIN_VIEW_MENU);
            System.out.println(Message.INPUT_CHOICE);
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case MainViewConstant.MENU_MANAGEMENT_INDEX:
                        new MenuView().displayMain();
                        break;
                    case MainViewConstant.BILL_MANAGEMENT_INDEX:
                        new BillView().displayMain();
                        break;
                    case MainViewConstant.EXIT_INDEX:
                        System.exit(0);
                        break;
                    default:
                        System.out.println(Message.CHOICE_NOT_EXISTED + "." + Message.PRESS_TO_CONTINUE);
                        scanner.nextLine();
                        scanner.nextLine();
                }


            } catch (RuntimeException e) {
                logger.fatal("displayMainView() - " + e);
                scanner.nextLine();
                System.out.println(Message.WRONG_INPUT);
                System.out.println(Message.PRESS_TO_CONTINUE);
                scanner.nextLine();
            }
        }
    }

}
