package controller;

import connector.DbConnector;
import model.Reason;

import java.util.ArrayList;

/**
 * This class ReasonController is to control the reason
 * To read Reason information from file and display
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class ReasonController
{

    /**
     * field
     */
    private ArrayList<Reason> listOfReason;
    private DbConnector dbConnector;

    /**
     * This is the default constructor
     */
    public ReasonController()
    {
        listOfReason = new ArrayList<>();
        dbConnector = new DbConnector();
    }

    /**
     * This is the parameterized constructor
     *
     * @param listOfReason the list of reason object
     * @param dbConnector  the DbConnector object
     */
    public ReasonController(ArrayList<Reason> listOfReason, DbConnector dbConnector)
    {
        this.listOfReason = listOfReason;
        this.dbConnector = dbConnector;
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
     * This method is to get the list of reason
     *
     * @return reason arraylist
     */
    public ArrayList<Reason> getListOfReason()
    {
        return listOfReason;
    }

    /**
     * This method is to set the list of reason
     *
     * @param listOfReason the list of reason
     */
    public void setListOfReason(ArrayList<Reason> listOfReason)
    {
        this.listOfReason = listOfReason;
    }

    /**
     * This method is to read Reason.txt to get the reason information
     */
    public void fetch()
    {
        dbConnector.setFileName("Reason.txt");
        dbConnector.setHasHeader(true);
        ArrayList<String> lines = dbConnector.readDataFromFile();
        for (String line : lines)
        {
            String[] lineArray = line.split(",");
            Reason reason = new Reason(Integer.parseInt(lineArray[0]), lineArray[1], Integer.parseInt(lineArray[2]));
            listOfReason.add(reason);
        }
    }

    /**
     * This method is to get the duration time by the reason id
     *
     * @param reasonId the id of reason
     * @return the duration of this reason int
     */
    public int getDurationById(int reasonId)
    {
        return listOfReason.get(reasonId - 1).getDuration();
    }

    /**
     * This method is to get the index by the reason id
     *
     * @param index the index of the list of reason
     * @return index int
     */
    public int getReasonIdByIndex(int index)
    {
        return listOfReason.get(index).getReasonId();
    }

    /**
     * This method is to get the type of the reason by id
     *
     * @param reasonId the id of reason
     * @return the type of reason String
     */
    public String getReasonTypeById(int reasonId)
    {
        return listOfReason.get(reasonId - 1).getType();
    }

    /**
     * This method is to get the type of the reason
     *
     * @param reasonId the id of reason
     * @return the type of reason String
     */
    public String getTypeById(int reasonId)
    {
        String type = "";
        for (Reason reason : listOfReason)
        {
            if (reason.getReasonId() == reasonId)
            {
                return reason.getType();
            }
        }
        return type;
    }

    /**
     * This method is to display all the reason information
     */
    public void showReasonList()
    {
        for (Reason reason : listOfReason)
        {
            System.out.println("(" + reason.getReasonId() + ") " + reason.getType() + " (" + reason.getDuration() + " minutes)");
        }
    }
}
