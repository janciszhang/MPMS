package controller;

import connector.DbConnector;
import model.User;

import java.util.ArrayList;

/**
 * This class UserController is to control the user
 * To read user information from file and to login
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class UserController
{

    /**
     * field
     */
    private ArrayList<User> listOfUser;
    private DbConnector dbConnector;

    /**
     * This is the default constructor
     */
    public UserController()
    {
        listOfUser = new ArrayList<>();
        dbConnector = new DbConnector();
    }

    /**
     * This is the parameterized constructor
     *
     * @param listOfUser  the list of user object
     * @param dbConnector the DbConnector object
     */
    public UserController(ArrayList<User> listOfUser, DbConnector dbConnector)
    {
        this.listOfUser = listOfUser;
        this.dbConnector = dbConnector;
    }

    /**
     * This method is to get the list of user
     *
     * @return user arraylist
     */
    public ArrayList<User> getListOfUser()
    {
        return listOfUser;
    }

    /**
     * This method is to set the list of user
     *
     * @param listOfUser the list of user
     */
    public void setListOfUser(ArrayList<User> listOfUser)
    {
        this.listOfUser = listOfUser;
    }

    /**
     * This method is to get the dbConnector object
     *
     * @return dbConnector DbConnector
     */
    public DbConnector getDbConnector()
    {
        return dbConnector;
    }

    /**
     * This method is to set the dbConnector
     *
     * @param dbConnector the dbConnector object
     */
    public void setDbConnector(DbConnector dbConnector)
    {
        this.dbConnector = dbConnector;
    }

    /**
     * This method is to read User.txt to get the user information
     */
    public void fetch()
    {
        dbConnector.setFileName("User.txt");
        dbConnector.setHasHeader(false);
        ArrayList<String> lines = dbConnector.readDataFromFile();
        for (String line : lines)
        {
            String[] lineArray = line.split(",");
            //userId,email,password
            User user = new User(Integer.parseInt(lineArray[0]), lineArray[1], lineArray[2]);
            listOfUser.add(user);
        }
    }

    /**
     * This method is to get the user id by the email
     *
     * @param email the email
     * @return the user id int
     */
    public int getUserIdByEmail(String email)
    {
        for (User user : listOfUser)
        {
            if (user.getEmail().equalsIgnoreCase(email))
            {
                return user.getUserId();
            }
        }
        return 0;
    }

    /**
     * This method is to check the user is patient or the admin
     *
     * @param email    the user email
     * @param password the user password
     * @return the check result
     */
    public boolean isUserAdmin(String email, String password)
    {
        User user = selectUserByEmailAndPassword(email, password);
        return user.getIsAdmin();
    }

    /**
     * This method is to check the email and the password match or not
     *
     * @param email    the user email
     * @param password the user password
     * @return the status of the match result
     */
    public boolean matchEmailAndPassword(String email, String password)
    {
        return selectUserByEmailAndPassword(email, password) != null;
    }

    /**
     * This method is to find the user have this email and the password
     *
     * @param email    the user email
     * @param password the user password
     * @return the user object
     */
    public User selectUserByEmailAndPassword(String email, String password)
    {
        return listOfUser.stream().filter(user -> user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)).findAny().orElse(null);
    }
}

