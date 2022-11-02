package model;
/**
 * This class Reason is used to store reason for going to gp
 * Attribute: reasonId, type, duration, count
 *
 * @author Jingmin Zhang, mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class Reason
{
    /**
     * field
     */
    private int reasonId;
    private String type;
    private int duration;
    private static int count = 0;

    /**
     * default constructor
     */
    public Reason()
    {
        this.count++;
        this.reasonId = count;
    }

    /**
     * non-default constructor
     *
     * @param reasonId
     * @param type
     * @param duration
     */
    public Reason(int reasonId, String type, int duration)
    {
        this.count++;
        this.reasonId = reasonId;
        this.type = type;
        this.duration = duration;
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
     * accessor of duration
     *
     * @return int duration
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     * accessor of reasonId
     * @return int reasonId
     */
    public int getReasonId()
    {
        return reasonId;
    }

    /**
     * accessor of type
     *
     * @return String type
     */
    public String getType()
    {
        return type;
    }

    /**
     * mutator of count
     *
     * @param count
     */
    public static void setCount(int count)
    {
        Reason.count = count;
    }

    /**
     * mutator of duration
     * @param duration
     */
    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    /**
     * mutator of reasonId
     *
     * @param reasonId
     */
    public void setReasonId(int reasonId)
    {
        this.reasonId = reasonId;
    }

    /**
     * mutator of type
     *
     * @param type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * override toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Reason{" + "reasonId=" + reasonId + ", type='" + type + '\'' + ", duration=" + duration + '}';
    }
}