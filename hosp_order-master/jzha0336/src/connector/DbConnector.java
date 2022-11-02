package connector;

import java.io.*;
import java.util.ArrayList;

/**
 * This class DbConnector is to read data and write data to file
 * To get the data information from the txt file
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class DbConnector
{

    /**
     * field
     */
    private String fileName;
    private boolean hasHeader;
    private String headStr;

    /**
     * This is the default constructor
     */
    public DbConnector()
    {
        fileName = "";
        hasHeader = false;
        headStr = "";
    }

    /**
     * This is the parameterized constructor
     *
     * @param fileName  the file name
     * @param hasHeader the file have header or not
     * @param headStr   the head string
     */
    public DbConnector(String fileName, boolean hasHeader, String headStr)
    {
        this.fileName = fileName;
        this.hasHeader = hasHeader;
        this.headStr = headStr;
    }

    /**
     * This method is to get the file name
     *
     * @return filename String
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * This method is to set the file name
     *
     * @param fileName The name of file
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * This method is to get the header status of the file
     *
     * @return hasHeader boolean
     */
    public boolean isHasHeader()
    {
        return hasHeader;
    }

    /**
     * This method is to set the header status of the file
     *
     * @param hasHeader The status of the header of the file
     */
    public void setHasHeader(boolean hasHeader)
    {
        this.hasHeader = hasHeader;
    }

    /**
     * This method is to read data from file
     *
     * @return String arraylist
     */
    public ArrayList<String> readDataFromFile()
    {
        FileReader fileReader = null;
        ArrayList<String> data = new ArrayList<>();
        try
        {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                data.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("Error! Cannot find the file: " + fileName);
        } catch (IOException e)
        {
            System.out.println("Error! IO exception");
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
        if (hasHeader && data.size() > 0)
            headStr = data.get(0);
        data.remove(0);

        return data;
    }

    /**
     * This method is to writer data to file
     *
     * @param data the data String
     * @throws FileNotFoundException
     */
    public void writeDataToFile(ArrayList<String> data) throws FileNotFoundException
    {
        try
        {
            File file = new File(fileName);
            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (hasHeader && data.size() > 0)
                bufferedWriter.write(headStr);
            for (String line : data)
            {
                bufferedWriter.write(line);
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("Error! Cannot find the file: " + fileName);
        } catch (IOException e)
        {
            System.out.println("Error! IO exception");
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    /**
     * This method is to write a new line into the file
     *
     * @param newLine the new line String
     * @throws FileNotFoundException
     */
    public void writeNewLineToFile(String newLine) throws FileNotFoundException
    {
        try
        {
            File file = new File(fileName);
            // if file does not exist, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newLine);
            bufferedWriter.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("Error! Cannot find the file: " + fileName);
        } catch (IOException e)
        {
            System.out.println("Error! IO exception");
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
