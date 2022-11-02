package model;
/**
 * This class Covid is used to store Covid questionnaire
 * Attribute: covidId, question, count
 *
 * @author Jingmin Zhang, mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class Covid
{
    /**
     * field
     */
    private int covidId;
    private String question;
    private static int count =0;

    /**
     * default constructor
     */
    public Covid()
    {
        this.count ++;
        this.covidId = count;
    }

    /**
     * non-default constructor
     *
     * @param covidId
     * @param question
     */
    public Covid(int covidId, String question)
    {
        this.count ++;
        this.covidId = covidId;
        this.question = question;
    }

    /**
     * accessor of count
     *
     * @return int count
     */
    public static int getCount()
    {
        return count;
    }

    /**
     * accessor of covidId
     *
     * @return int covidId
     */
    public int getCovidId()
    {
        return covidId;
    }

    /**
     * accessor of question
     *
     * @return String question
     */
    public String getQuestion()
    {
        return question;
    }

    /**
     * mutator of count
     *
     * @param count
     */
    public static void setCount(int count)
    {
        Covid.count = count;
    }

    /**
     * mutator of covidId
     *
     * @param covidId
     */
    public void setCovidId(int covidId)
    {
        this.covidId = covidId;
    }

    /**
     * mutator of question
     *
     * @param question
     */
    public void setQuestion(String question)
    {
        this.question = question;
    }
}