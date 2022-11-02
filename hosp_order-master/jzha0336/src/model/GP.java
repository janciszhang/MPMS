package model;

/**
 * This class GP is used to store GP information
 * Attribute: gpId, firstName, lastName, phone, interest, count
 *
 * @author Jingmin Zhang, mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class GP
{
    /**
     * field
     */
    private int gpId;
    private String firstName;
    private String lastName;
    private String phone;
    private String interest;
    private static int count =0;

    /**
     * default constructor
     */
    public GP()
    {
        this.count ++;
        this.gpId = count;
    }

    /**
     * non-default constructor
     *
     * @param gpId
     * @param firstName
     * @param lastName
     * @param phone
     * @param interest
     */
    public GP(int gpId, String firstName, String lastName, String phone,String interest)
    {
        this.count ++;
        this.gpId = gpId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.interest = interest;
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
     * accessor of firstName
     *
     * @return String firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * accessor of gpId
     *
     * @return int gpId
     */
    public int getGpId()
    {
        return gpId;
    }

    /**
     * accessor of interest
     *
     * @return String interest
     */
    public String getInterest(){
        return interest;
    }

    /**
     * accessor of lastName
     *
     * @return String lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * accessor of phone
     *
     * @return String phone
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * mutator of count
     *
     * @param count
     */
    public static void setCount(int count)
    {
        GP.count = count;
    }

    /**
     * mutator of firstName
     *
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * mutator of gpId
     *
     * @param gpId
     */
    public void setGpId(int gpId)
    {
        this.gpId = gpId;
    }

    /**
     * mutator of interest
     *
     * @param interest
     */
    public void setInterest(String interest)
    {
        this.interest = interest;
    }

    /**
     * mutator of lastName
     *
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * mutator of phone
     *
     * @param phone
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * override toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "First name: " + firstName + "\nLast name: " + lastName + "\nPhone: " + phone + "\nInterest: " + interest;
    }
}