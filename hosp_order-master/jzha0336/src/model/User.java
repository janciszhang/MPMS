package model;
/**
 * This class User is used to store user information
 * Attribute: userId, email, password, isAdmin, count
 *
 * @author Jingmin Zhang, mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class User
{
    /**
     * field
     */
    private int userId;
    private String email;
    private String password;
    private boolean isAdmin;
    private static int count = 0;

    /**
     * default constructor
     */
    public User()
    {
        this.count++;
        this.userId = count;
    }

    /**
     * non-default constructor
     *
     * @param email
     * @param password
     */
    public User(String email, String password)
    {
        this.count++;
        this.userId = count;
        this.email = email;
        this.password = password;
        this.isAdmin = this.email.equals("admin@monash.edu");
    }

    /**
     * non-default constructor
     *
     * @param userId
     * @param email
     * @param password
     */
    public User(int userId, String email, String password)
    {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.isAdmin = this.email.equals("admin@monash.edu");
    }

    /**
     * non-default constructor
     *
     * @param userId
     * @param email
     * @param password
     * @param isAdmin
     */
    public User(int userId, String email, String password, boolean isAdmin)
    {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
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
     * accessor of email
     *
     * @return String email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * accessor of isAdmin
     *
     * @return boolean isAdmin
     */
    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    /**
     * accessor of password
     *
     * @return String password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * accessor of userId
     *
     * @return int userId
     */
    public int getUserId()
    {
        return userId;
    }

    /**
     * mutator of count
     *
     * @param count
     */
    public static void setCount(int count)
    {
        User.count = count;
    }

    /**
     * mutator of email
     *
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * mutator of isAdmin
     *
     * @param isAdmin
     */
    public void setIsAdmin(boolean isAdmin)
    {
        isAdmin = isAdmin;
    }

    /**
     * mutator of userId
     *
     * @param userId
     */
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    /**
     * mutator of password
     *
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * override toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "User{" + "userId=" + userId + ", email='" + email + '\'' + ", password='" + password + '\'' + ", isAdmin=" + isAdmin + '}';
    }
}