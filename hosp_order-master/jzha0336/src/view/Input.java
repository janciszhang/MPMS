package view;

import java.util.Scanner;

/**
 * This class Input get input content via Scanner class.
 * To get input String, no need to check.
 * To get input int/natural number/positive integer/integer in a range, need check.
 * if not, ask input again until the input is valid.
 * To get input required length string array of something (with whole format)
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */

public class Input
{
    /**
     * Default constructor for class Input
     */
    public Input()
    {
    }

    /**
     * input date String with format check and input time limit
     * YYYY/MM/DD
     * if up to limit time, do not have chance to input
     *
     * @param limitTime
     * @return String inputString
     */
    public static String inputDateStringLimitTime(int limitTime)
    {
        String inputString = inputString();
        int inputCount = 1;
        while (true)
        {
            if (!InputFormatValidator.formatValidation(inputString, "date") && inputCount == limitTime)
            {
                System.out.println("\nError input up to three times! Back to home page...");
                inputString = "";
                break;
            }
            if (InputFormatValidator.formatValidation(inputString, "date"))
            {
                break;
            }
            else
            {
                System.out.print("Input Format Error!\n>>> Please input a valid date (YYYY/MM/DD): ");
                inputString = inputString();
            }
            inputCount++;
        }
        return inputString;
    }

    /**
     * input format string on limit times
     * if up to limit time, do not have chance to input
     *
     * @param patternName
     * @param limitTime
     * @return String inputString
     */
    public static String inputFormatStringLimitTime(String patternName, int limitTime)
    {
        String inputString = inputString();
        int inputCount = 1;
        while (true)
        {
            if (!InputFormatValidator.formatValidation(inputString, patternName) && inputCount == limitTime)
            {
                System.out.println("\nError input up to three times! Back to home page...");
                inputString = "";
                break;
            }
            if (InputFormatValidator.formatValidation(inputString, patternName))
            {
                break;
            }
            else
            {
                System.out.print("Input Format Error!\n>>> Please input a valid " + patternName + ": ");
                inputString = inputString();
            }
            inputCount++;
        }
        return inputString;
    }

    /**
     * Input int via Scanner class
     * check if the input is int (scan.hasNextInt())
     * still ask input until the input is int (while loop)
     *
     * @return int inputNumber
     */
    public static int inputInt()
    {
        int inputNumber = 0;
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt())
        {
            scan.next();
            System.out.print("INVALID ENTRY! Please enter a integer number: ");
        }
        inputNumber = scan.nextInt();
        // scan.close(); // cannot close, in loop will error!!!
        return inputNumber;
    }

    /**
     * input a natural number
     * check input is a non-negative integer
     * if not, print error message and ask input again and again until it is valid
     *
     * @return a int, natural number
     */
    public static int inputNaturalNumber()
    {
        int number = inputInt();
        while (number < 0)
        {
            System.out.print("Input Error!\n>>> Please input a non-negative integer: ");
            number = inputInt();
        }
        return number;
    }

    /**
     * input integer in a range
     * input a integer from range start to end
     * if input out of the range, print error massage and ask input again until it is valid
     *
     * @param rangeStart: a integer, the start of range
     * @param rangeEnd:   a integer, the end of range
     * @return the selected option
     */
    public static int inputIntegerInRange(int rangeStart, int rangeEnd)
    {
        int option = inputInt();
        while (option < rangeStart || option > rangeEnd)
        {
            System.out.print("INVALID ENTRY! Please input number between " + rangeStart + " and " + rangeEnd + ": ");
            option = inputInt();
        }
        return option;
    }

    /**
     * input a positive integer
     * check input is positive integer
     * if not, print error message and ask input again and again until it is valid
     *
     * @return a int, positive integer
     */
    public static int inputPositiveInteger()
    {
        int number = inputInt();
        while (number <= 0)
        {
            System.out.print("Input Error!\n>>> Please input a positive integer: ");
            number = inputInt();
        }
        return number;
    }

    /**
     * Input String via Scanner
     * no check, no loop
     *
     * @return String inputString
     */
    public static String inputString()
    {
        String inputString = "";
        Scanner scan = new Scanner(System.in);
        inputString = scan.nextLine().trim();
        // scan.close(); // cannot close, in loop will error!!!
        return inputString;
    }

    /**
     * input required length string array of something
     * must have at least one
     * If it does have enough item, press ENTER to skip and set it as empty string
     *
     * @param sth:    a string, something
     * @param length: a int, the requirement length of the string array
     * @return a string array
     */
    public static String[] inputStringArray(String sth, int length)
    {
        String[] strings = new String[3];
        System.out.println("\n--------------------Input " + sth + "--------------------");
        System.out.println("Please input " + length + " item.\nIf it does have enough (but have" + " at least one), press ENTER" + " to skip" + ".\n");
        for (int i = 0; i < 3; i++)
        {
            System.out.print(">>> Please input " + sth + " " + (i + 1) + ": ");
            strings[i] = inputString();
            while (strings[0].isEmpty())
            {
                System.out.print("Error! The first one cannot be empty. Please input again: ");
                strings[0] = inputString();
            }

        }
        System.out.println("------------------------done------------------------");
        return strings;
    }

    /**
     * input time string with format check and input time limit
     * hh24:mm
     * if up to limit time, do not have chance to input
     *
     * @param limitTime
     * @return String inputString
     */
    public static String inputTimeStringLimitTime(int limitTime)
    {
        String inputString = inputString();
        int inputCount = 1;
        while (true)
        {
            if (!InputFormatValidator.formatValidation(inputString, "time") && inputCount == limitTime)
            {
                System.out.println("\nError input up to three times! Back to home page...");
                inputString = "";
                break;
            }
            if (InputFormatValidator.formatValidation(inputString, "time"))
            {
                break;
            }
            else
            {
                System.out.print("Input Format Error!\n>>> Please input a valid time (hh24:mm): ");
                inputString = inputString();
            }
            inputCount++;
        }
        return inputString;
    }
}
