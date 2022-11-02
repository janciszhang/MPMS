package view;

import java.util.HashMap;

/**
 * This class InputFormatValidator is to check th String if it is following the format
 * it can check email, password, phone, date, time
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class InputFormatValidator
{
    /**
     * field
     */
    private static HashMap<String, String> patternNameAndPattern;

    /**
     * default constructor
     */
    public InputFormatValidator()
    {
        patternNameAndPattern = new HashMap<>();
    }

    /**
     * check string format
     * it cannot contain "," because it separates the data in database file
     *
     * @param input
     * @return Boolean check result
     */
    public static Boolean formatValidation(String input)
    {
        setPatternNameAndPattern();
        return input.matches(patternNameAndPattern.get("noDBF"));
    }

    /**
     * check string format
     * it cannot contain "," because it separates the data in database file
     * it has other format requirement that need to follow
     *
     * @param input
     * @param patternName
     * @return
     */
    public static Boolean formatValidation(String input, String patternName)
    {
        setPatternNameAndPattern();
        return input.matches(patternNameAndPattern.get(patternName)) && formatValidation(input);
    }

    /**
     * accessor for patternNameAndPattern
     *
     * @return HashMap<String, String> patternNameAndPattern
     */
    public static HashMap<String, String> getPatternNameAndPattern()
    {
        return patternNameAndPattern;
    }

    /**
     * mutator for patternNameAndPattern
     */
    public static void setPatternNameAndPattern()
    {
        patternNameAndPattern = new HashMap<>();
        //data separate by "|", so any input which need store cannot contain "|"
        patternNameAndPattern.put("noDBF", "^((?!\\|).)*$");
        patternNameAndPattern.put("email", "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        //password: minimum 8 characters with at least one upper case and one number
        patternNameAndPattern.put("password", "^(?=.*?[A-Z])(?=.*?[0-9]).{8,}$");
        patternNameAndPattern.put("phone", "^[0-9]{10}$");
        patternNameAndPattern.put("date", "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02\\/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))\\/02\\/29)$");
        patternNameAndPattern.put("time", "^([0-1][0-9]|2[0-3]):([0-5][0-9])$");
    }
}

