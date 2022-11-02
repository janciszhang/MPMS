package controller;

import connector.DbConnector;
import model.GP;

import java.util.ArrayList;
import java.util.List;

/**
 * This class GpController is to control the gp
 * To read gp information from file and display
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class GPController
{

    /**
     * field
     */
    //GpId,FirstName,LastName,Mobile
    private ArrayList<GP> listOfGp;
    private DbConnector dbConnector;

    /**
     * This is the default constructor
     */
    public GPController()
    {
        listOfGp = new ArrayList<>();
        dbConnector = new DbConnector();
    }

    /**
     * This is the parameterized constructor
     *
     * @param listOfGp    the list of gp object
     * @param dbConnector the DbConnector object
     */
    public GPController(ArrayList<GP> listOfGp, DbConnector dbConnector)
    {
        this.listOfGp = listOfGp;
        this.dbConnector = dbConnector;
    }

    /**
     * This method is to get the list of gp
     *
     * @return gp arraylist
     */
    public ArrayList<GP> getListOfGp()
    {
        return listOfGp;
    }

    /**
     * This method is to set the list of gp
     *
     * @param listOfGp the list of gp
     */
    public void setListOfGp(ArrayList<GP> listOfGp)
    {
        this.listOfGp = listOfGp;
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
     * This method is to read GP.txt to get the gp information
     */
    public void fetch()
    {
        dbConnector.setFileName("GP.txt");
        dbConnector.setHasHeader(true);
        ArrayList<String> lines = dbConnector.readDataFromFile();
        for (String line : lines)
        {
            String[] lineArray = line.split(",");
            //GpId,FirstName,LastName,Phone,Interest
            GP gp = new GP(Integer.parseInt(lineArray[0]), lineArray[1], lineArray[2], lineArray[3], lineArray[4]);
            listOfGp.add(gp);
        }
    }

    /**
     * This method is to get the gp object by the gp id
     *
     * @param gpId the id of gp
     * @return the gp object GP
     */
    public GP getGpById(int gpId)
    {
        for (GP gp : listOfGp)
        {
            if (gp.getGpId() == gpId)
                return gp;
        }
        return null;
    }

    /**
     * This method is to get the gp id by the order number
     *
     * @param gpIds       the id list of gp
     * @param orderNumber the order number
     * @return the id of gp
     */
    public int getGpIdByOrderNumber(List<Integer> gpIds, int orderNumber)
    {
        return gpIds.get(orderNumber - 1);
    }


    /**
     * This method is to get the gp name by the gp id
     *
     * @param gpId the id of gp
     * @return gp name String
     */
    public String getNameByGpId(int gpId)
    {
        GP gp = getGpById(gpId);
        if (gp == null)
        {
            return "";
        }
        else
        {
            return gp.getFirstName() + " " + gp.getLastName();
        }
    }

    /**
     * This method is to display the gp details information
     *
     * @param gpId the id of gp
     */
    public void showGpDetails(int gpId)
    {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("Here are the details about this GP:");
        System.out.println("---------------------------------------");
        System.out.println(getGpById(gpId));
        System.out.println("∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨");
        System.out.println();
    }
}
