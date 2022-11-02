package model;

/**
 * This class Branch is used to store information of branches
 * Attribute: branchId, branchName, phone, openingHours, street, suburb, state, postcode, count
 *
 * @author Jingmin Zhang, mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class Branch
{
    /**
     * field
     */
    //BranchId,OpeningHours,Street,Suburb,State,Postcode
    private int branchId;
    private String branchName;
    private String phone;
    private String openingHours;
    private String street;
    private String suburb;
    private String state;
    private String postcode;
    private static int count = 0;

    /**
     * default constructor
     */
    public Branch()
    {
        this.count++;
        this.branchId = count;
    }

    /**
     * non-default constructor
     */
    public Branch(int branchId, String branchName, String phone,String openingHours,
                  String street, String suburb, String state, String postcode)
    {
        this.count++;
        this.branchId = branchId;
        this.branchName = branchName;
        this.phone=phone;
        this.openingHours = openingHours;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
    }

    /**
     * accessor of branchId
     *
     * @return int branchId
     */
    public int getBranchId()
    {
        return branchId;
    }

    /**
     * accessor of branchName
     *
     * @return String branchName
     */
    public String getBranchName()
    {
        return branchName;
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
     * accessor of openingHours
     *
     * @return String openingHours
     */
    public String getOpeningHours()
    {
        return openingHours;
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
     * accessor of postcode
     *
     * @return String postcode
     */
    public String getPostcode()
    {
        return postcode;
    }

    /**
     * accessor of state
     *
     * @return String state
     */
    public String getState()
    {
        return state;
    }

    /**
     * accessor of street
     *
     * @return String street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * accessor of suburb
     *
     * @return String suburb
     */
    public String getSuburb()
    {
        return suburb;
    }

    /**
     * mutator of branchId
     *
     * @param branchId
     */
    public void setBranchId(int branchId)
    {
        this.branchId = branchId;
    }

    /**
     * mutator of branchName
     *
     * @param branchName
     */
    public void setBranchName(String branchName)
    {
        this.branchName = branchName;
    }

    /**
     * mutator of count
     *
     * @param count
     */
    public static void setCount(int count)
    {
        Branch.count = count;
    }

    /**
     * mutator of setOpeningHours
     *
     * @param openingHours
     */
    public void setOpeningHours(String openingHours)
    {
        this.openingHours = openingHours;
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
     * mutator of postcode
     *
     * @param postcode
     */
    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    /**
     * mutator of state
     *
     * @param state
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * mutator of street
     *
     * @param street
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     * mutator of suburb
     *
     * @param suburb
     */
    public void setSuburb(String suburb)
    {
        this.suburb = suburb;
    }

    /**
     * override toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Branch name: " + branchName + "\nAddress: " + street + ", " + suburb + " " + state + " " + postcode + "\nPhone: "+phone+"\nOpening hours:"+openingHours;
    }
}