package model;

/**
 * This class BranchGp is used to store gp information in chosen branch
 * Attribute: branchGpId, gpId, branchId, dayOfWeek, startTime, endTime, count
 *
 * @author Jingmin Zhang, mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class BranchGp
{
    /**
     * field
     */
    //BranchGpId,GpId,BranchId,DayOfWeek,Hours
    private int branchGpId;
    private int gpId;
    private int branchId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private static int count = 0;

    /**
     * default constructor
     */
    public BranchGp()
    {
        this.count++;
        this.branchGpId = count;
    }

    /**
     * non-default constructor
     *
     * @param branchGpId
     * @param gpId
     * @param branchId
     * @param dayOfWeek
     * @param startTime
     * @param endTime
     */
    public BranchGp(int branchGpId, int gpId, int branchId, String dayOfWeek, String startTime, String endTime)
    {
        this.count++;
        this.branchGpId = branchGpId;
        this.gpId = gpId;
        this.branchId = branchId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * accessor of branchGpId
     *
     * @return int branchGpId
     */
    public int getBranchGpId()
    {
        return branchGpId;
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
     * accessor of count
     *
     * @return int count
     */
    public static int getCount()
    {
        return count;
    }

    /**
     * accessor of dayOfWeek
     *
     * @return String dayOfWeek
     */
    public String getDayOfWeek()
    {
        return dayOfWeek;
    }

    /**
     * accessor of endTime
     *
     * @return String endTime
     */
    public String getEndTime()
    {
        return endTime;
    }

    /**
     * accessor of gpId
     *
     * @return int gpId
     */
    public int getGpId()
    {
        return gpId;
    }

    /**
     * accessor of startTime
     *
     * @return String startTime
     */
    public String getStartTime()
    {
        return startTime;
    }

    /**
     * mutator of branchGpId
     *
     * @param branchGpId
     */
    public void setBranchGpId(int branchGpId)
    {
        this.branchGpId = branchGpId;
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
     * mutator of count
     *
     * @param count
     */
    public static void setCount(int count)
    {
        BranchGp.count = count;
    }

    /**
     * mutator of dayOfWeek
     *
     * @param dayOfWeek
     */
    public void setDayOfWeek(String dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * mutator of endTime
     *
     * @param endTime
     */
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    /**
     * mutator gpId
     *
     * @param gpId
     */
    public void setGpId(int gpId)
    {
        this.gpId = gpId;
    }

    /**
     * mutator of startTime
     *
     * @param startTime
     */
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    /**
     * override toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "BranchGp{" + "branchGpId=" + branchGpId + ", gpId=" + gpId + ", branchId=" + branchId + ", dayOfWeek='" + dayOfWeek + '\'' + ", startTime='" + startTime + '\'' + ", endTime='" + endTime + '\'' + '}';
    }
}