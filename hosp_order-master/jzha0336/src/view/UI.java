package view;

/**
 * This class UI is to design for the screen
 * it has head, command, exit and other related screen
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class UI
{
    /**
     * default constructor
     */
    public UI()
    {
    }

    /**
     * this is command screen
     *
     * @param command
     */
    private static void commandScreen(String command)
    {
        String starLine = "****************************************************";
        if ((46 - command.length()) >= 0)
        {
            String substring = starLine.substring(0, (46 - command.length()) / 2);
            System.out.println(" " + substring + " " + command + " " + substring);
        }
        else
        {
            System.out.println("* " + command + " *");
        }
    }

    /**
     * this is exit screen
     */
    public static void exitScreen()
    {
        systemHead();
        System.out.println("               Exit System Success!");
        System.out.println("\n\n");
        System.out.println("       ฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅ");
        System.out.println("\n            THANK YOU FOR USING MPMS!             \n");
        System.out.println("       ฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅฅʕ•̫͡•ʔฅ");
        System.out.println("\n\n\n\n\n");
    }

    /**
     * this is whole head screen no return
     *
     * @param title
     * @param command
     */
    public static void headScreenNoReturn(String title, String command)
    {
        systemHead();
        titleScreen(title);
        System.out.println("");
        commandScreen(command);
        System.out.println();
    }

    /**
     * this is whole head screen with return
     *
     * @param title
     * @param command
     */
    public static void headScreenWithReturn(String title, String command)
    {
        systemHead();
        titleScreen(title);
        System.out.println("");
        commandScreen(command);
        System.out.println("<0> Enter 0 to return home screen");
        System.out.println();
    }

    /**
     * this is MPMS system head
     */
    private static void systemHead()
    {
        System.out.println(" ==============================================");
        System.out.println("|                                              |");
        System.out.println("|       Monash Patient Management System       |");
        System.out.println("|                                              |");
        System.out.println(" ==============================================");
        System.out.println("");
    }

    /**
     * this is title screen
     *
     * @param title
     */
    private static void titleScreen(String title)
    {
        String blackLine = "                                            ";
        String waveLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(blackLine.substring(0, (36 - title.length()) / 2) + waveLine.substring(0, title.length() + 10) + blackLine.substring(0, (36 - title.length()) / 2));
        System.out.println(blackLine.substring(0, (45 - title.length()) / 2) + title + blackLine.substring(0, (50 - title.length()) / 2));
        System.out.println(blackLine.substring(0, (36 - title.length()) / 2) + waveLine.substring(0, title.length() + 10) + blackLine.substring(0, (36 - title.length()) / 2));
    }
}
