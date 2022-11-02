package model;
/**
 * This class Patient contains information from class User, and is used for storing patient information
 * Attribute: firstName, lastName, gender, dateOfBirth, phone, streetDetails, suburb, postcode
 *
 * @author Jingmin Zhang, mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class Patient extends User
{
    /**
     * field
     */
    /* Fields PK:email */
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String phone;
    private String streetDetails;
    private String suburb;
    private String postcode;

    /**
     * default constructor
     */
    public Patient()
    {
    }

    /**
     * non-default constructor
     *
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @param gender
     * @param dateOfBirth
     * @param phone
     * @param streetDetails
     * @param suburb
     * @param postcode
     */
    public Patient(String email, String password, String firstName, String lastName, String gender, String dateOfBirth, String phone, String streetDetails, String suburb, String postcode)
    {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.streetDetails = streetDetails;
        this.suburb = suburb;
        this.postcode = postcode;
    }

    /**
     * accessor of dateOfBirth
     *
     * @return String dateOfBirth
     */
    public String getDateOfBirth()
    {
        return dateOfBirth;
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
     * accessor of gender
     *
     * @return String gender
     */
    public String getGender()
    {
        return gender;
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
     * accessor of postcode
     *
     * @return String postcode
     */
    public String getPostcode()
    {
        return postcode;
    }

    /**
     * accessor of streetDetails
     * @return String streetDetails
     */
    public String getStreetDetails()
    {
        return streetDetails;
    }

    /**
     * acccessor of suburb
     *
     * @return String suburb
     */
    public String getSuburb()
    {
        return suburb;
    }

    /**
     * mutator of dateOfBirth
     * @param dateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * mutator of firstName
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * mutator of gender
     * @param gender
     */
    public void setGender(String gender)
    {
        this.gender = gender;
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
     * mutator of postcode
     *
     * @param postcode
     */
    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    /**
     * mutator of streetDetails
     * @param streetDetails
     */
    public void setStreetDetails(String streetDetails)
    {
        this.streetDetails = streetDetails;
    }

    /**
     * mutator of suburb
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
        return "Patient{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", gender='" + gender + '\'' + ", dateOfBirth='" + dateOfBirth + '\'' + ", phone='" + phone + '\'' + ", streetDetails='" + streetDetails + '\'' + ", suburb='" + suburb + '\'' + ", postcode='" + postcode + '\'' + '}';
    }
}