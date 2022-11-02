package controller;

import connector.DbConnector;
import model.BranchGp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class BranchGpController is to control the branchgp
 * To read branch information from file and display
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class BranchGpController
{

    /**
     * field
     */
    //GpId,FirstName,LastName,Mobile
    private ArrayList<BranchGp> listOfBranchGp;
    private DbConnector dbConnector;

    /**
     * This is the default constructor
     */
    public BranchGpController()
    {
        listOfBranchGp = new ArrayList<>();
        dbConnector = new DbConnector();
    }

    /**
     * This is the parameterized constructor
     *
     * @param listOfBranchGp the list of branchgp object
     * @param dbConnector    the DbConnector object
     */
    public BranchGpController(ArrayList<BranchGp> listOfBranchGp, DbConnector dbConnector)
    {
        this.listOfBranchGp = listOfBranchGp;
        this.dbConnector = dbConnector;
    }

    /**
     * This method is to get the list of branchgp
     *
     * @return the branchgp arraylist
     */
    public ArrayList<BranchGp> getListOfBranchGp()
    {
        return listOfBranchGp;
    }

    /**
     * This method is to set the list of branchgp
     *
     * @param listOfBranchGp the list of branchgp
     */
    public void setListOfBranchGp(ArrayList<BranchGp> listOfBranchGp)
    {
        this.listOfBranchGp = listOfBranchGp;
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
     * This method is to read BranchGp.txt to get the branchgp information
     */
    public void fetch()
    {
        dbConnector.setFileName("BranchGp.txt");
        dbConnector.setHasHeader(true);
        ArrayList<String> lines = dbConnector.readDataFromFile();
        for (String line : lines)
        {
            String[] lineArray = line.split(",");
            //BranchGpId,GpId,BranchId,DayOfWeek,startTime,endTime
            BranchGp branchGp = new BranchGp(Integer.parseInt(lineArray[0]),
                    Integer.parseInt(lineArray[1]),
                    Integer.parseInt(lineArray[2]),
                    lineArray[3],
                    lineArray[4],
                    lineArray[5]
            );
            listOfBranchGp.add(branchGp);
        }
    }

    /**
     * This method is to find the gp by the branch id
     *
     * @param branchId the id of branch
     * @return the list of branch id
     */
    public List<Integer> findGpByBranch(int branchId)
    {
        ArrayList<Integer> gpList = new ArrayList<>();
        for (BranchGp branchGp : listOfBranchGp)
        {
            if (branchGp.getBranchId() == branchId)
            {
                gpList.add(branchGp.getGpId());
            }
        }
        // Java 8
        return gpList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * This method is to find the working hours by the gp id and the branch id
     *
     * @param gpId     the id of gp
     * @param branchId the id of the branch
     * @return the working hours String arraylist
     */
    public ArrayList<String> findWorkingHoursByGpIdAndBranchId(int gpId, int branchId)
    {
        ArrayList<String> workingHourList = new ArrayList<>();
        for (BranchGp branchGp : listOfBranchGp)
        {
            if (branchGp.getGpId() == gpId && branchGp.getBranchId() == branchId)
            {
                workingHourList.add(branchGp.getDayOfWeek() + " "
                        + branchGp.getStartTime() + "~" + branchGp.getEndTime());
            }
        }
        return workingHourList;
    }
}
