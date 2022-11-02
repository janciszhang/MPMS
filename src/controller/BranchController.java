package controller;

import connector.DbConnector;
import model.Branch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class BranchController is to control the branch
 * To read branch information from file and display
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class BranchController
{

    /**
     * field
     */
    private ArrayList<Branch> listOfBranch;
    private DbConnector dbConnector;

    /**
     * This is the default constructor
     */
    public BranchController()
    {
        listOfBranch = new ArrayList<>();
        dbConnector = new DbConnector();
    }

    /**
     * This is the parameterized constructor
     *
     * @param listOfBranch the list of branch object
     * @param dbConnector  the DbConnector object
     */
    public BranchController(ArrayList<Branch> listOfBranch, DbConnector dbConnector)
    {
        this.listOfBranch = listOfBranch;
        this.dbConnector = dbConnector;
    }

    /**
     * This method is to get the list of branch
     *
     * @return branch arraylist
     */
    public ArrayList<Branch> getListOfBranch()
    {
        return listOfBranch;
    }

    /**
     * This method is to set the list of branch
     *
     * @param listOfBranch the list of branch
     */
    public void setListOfBranch(ArrayList<Branch> listOfBranch)
    {
        this.listOfBranch = listOfBranch;
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
     * This method is to read Branch.txt to get the branch information
     */
    public void fetch()
    {
        dbConnector.setFileName("Branch.txt");
        dbConnector.setHasHeader(true);
        ArrayList<String> lines = dbConnector.readDataFromFile();
        for (String line : lines)
        {
            String[] lineArray = line.split(",");
            //BranchId,OpeningHours,Street,Suburb,State,Postcode
            Branch branch = new Branch(Integer.parseInt(lineArray[0]), lineArray[1], lineArray[2], lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[7]);
            listOfBranch.add(branch);
        }
    }

    /**
     * This method is to order the branch name
     */
    public static Comparator<Branch> NameComparator = new Comparator<Branch>()
    {
        public int compare(Branch branch1, Branch branch2)
        {
            // 正序排列
            return branch1.getBranchName().compareToIgnoreCase(branch2.getBranchName());
        }
    };


    /**
     * This method is to get the branch by the branch id
     *
     * @param branchId the id of branch
     * @return the branch object
     */
    public Branch getBranchById(int branchId)
    {
        for (Branch branch : listOfBranch)
        {
            if (branch.getBranchId() == branchId)
            {
                return branch;
            }
        }
        System.out.println("Error! There are no this branch!");
        return null;
    }

    /**
     * This method is to get the branch id by the order number
     *
     * @param orderNumber the order number of the branch in the list
     * @return the id of the branch int
     */
    public int getBranchIdByOrderNumber(int orderNumber)
    {
        return listOfBranch.get(orderNumber - 1).getBranchId();
    }

    /**
     * This method is to get the branch location by the branch id
     *
     * @param branchId the id of branch
     * @return the location of this branch String
     */
    public String getBranchLocationById(int branchId)
    {
        Branch branch = getBranchById(branchId);
        return branch.getStreet() + ", " + branch.getSuburb() + ", " + branch.getState() + " " + branch.getPostcode();
    }

    /**
     * This method is to order the branch name by alphabet
     */
    public void orderAlphabet()
    {
        Collections.sort(listOfBranch, NameComparator);
    }

    /**
     * This method is to print branch details by branch order number
     */
    public void showBranchDetails(int orderNumber)
    {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("Here are the details about this branch:");
        System.out.println("---------------------------------------");
        System.out.println(listOfBranch.get(orderNumber - 1));
        System.out.println("∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨");
        System.out.println();
    }

    /**
     * This method is to return the branch list String
     *
     * @return branch list String
     */
    public String showBranchList()
    {
        orderAlphabet();
        String listOfBranchStr = "";
        for (Branch branch : listOfBranch)
        {
            listOfBranchStr += branch.getBranchId() + ". " + branch.getBranchName() + ", " + branch.getStreet() + ", " + branch.getSuburb() + ", " + branch.getState() + " " + branch.getPostcode() + " [" + branch.getOpeningHours() + "]\n";
        }
        return listOfBranchStr;
    }

    /**
     * This method is to show the ordered branch name list
     */
    public void showBranchNameList()
    {
        orderAlphabet();
        int orderNumber = 1;
        for (Branch branch : listOfBranch)
        {
            System.out.println("(" + orderNumber + ") " + branch.getBranchName());
            orderNumber++;
        }
    }

}