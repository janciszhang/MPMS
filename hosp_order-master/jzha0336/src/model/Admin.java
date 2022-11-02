package model;

/**
 * This class Admin contains information from class User, and is used for storing Admin account
 * Attribute: email and password
 *
 * @author Jingmin Zhang, mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class Admin extends User
{
    /* PK:email */

    /**
     * default constructor
     */
    public Admin()
    {
    }

    /** non-default constructor
     *
     * @param email
     * @param password
     */
    public Admin(String email, String password)
    {
        super(email, password);
    }
}