package utils;

import java.util.Scanner;

/**
 * Class of utility of screen
 */
public class ScreenUtility {
    public static Scanner scanner =new Scanner(System.in);

    /**
     * check continue working with current screen or not
     * @return true if select 1,return false
     */
    public static boolean isContinue() {
        String select;
        System.out.println(Message.PRESS_1_TO_CONTINUE);
        try {
            select = scanner.nextLine();
            return select.equals("1");
        } catch (RuntimeException e) {
            scanner.nextLine();
            return false;
        }
    }

    /**
     * print message and stop screen
     */
    public static void stopScreen() {
        System.out.println(Message.PRESS_TO_CONTINUE);
        scanner.nextLine();
    }
}
