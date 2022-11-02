package model;

import java.util.Date;
/**
 * This class Appointment is used for booking appointments
 * Attribute: appointmentId, patientId, branchId, gpId, reasonId, appStartDateTime, appEndDateTime, count
 *
 * @author Jingmin Zhang, mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class Appointment
{
    /**
     * field
     */
    private int appointmentId;
    private int patientId;
    private int branchId;
    private int gpId;
    private int reasonId;
    private Date appStartDateTime;
    private Date appEndDateTime;
    private static int count = 0;

    /**
     * default constructor
     */
    public Appointment()
    {
        this.count++;
        this.appointmentId = count;
    }

    /**
     * non-default constructor
     *
     * @param appointmentId
     * @param patientId
     * @param branchId
     * @param gpId
     * @param reasonId
     * @param appStartDateTime
     * @param appEndDateTime
     */
    public Appointment(int appointmentId, int patientId, int branchId, int gpId, int reasonId, Date appStartDateTime, Date appEndDateTime)
    {
        this.count++;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.branchId = branchId;
        this.gpId = gpId;
        this.reasonId = reasonId;
        this.appStartDateTime = appStartDateTime;
        this.appEndDateTime = appEndDateTime;
    }

    /**
     * accessor of appEndDateTime;
     *
     * @return Date appEndDateTime;
     */
    public Date getAppEndDateTime()
    {
        return appEndDateTime;
    }

    /**
     * accessor of appointmentId
     *
     * @return int appointmentId
     */
    public int getAppointmentId()
    {
        return appointmentId;
    }

    /**
     * accessor of appStartDateTime
     *
     * @return Date appStartDateTime
     */
    public Date getAppStartDateTime()
    {
        return appStartDateTime;
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
     * accessor of gpId
     *
     * @return int gpId
     */
    public int getGpId()
    {
        return gpId;
    }

    /**
     * accessor of patientId
     *
     * @return int patientId
     */
    public int getPatientId()
    {
        return patientId;
    }

    /**
     * accessor of reasonId
     *
     * @return int reasonId
     */
    public int getReasonId()
    {
        return reasonId;
    }

    /**
     * mutator of appEndDateTime;
     *
     * @param appEndDateTime
     */
    public void setAppEndDateTime(Date appEndDateTime)
    {
        this.appEndDateTime = appEndDateTime;
    }

    /**
     * mutator of appointmentId
     *
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId)
    {
        this.appointmentId = appointmentId;
    }

    /**
     * mutator of appStartDateTime
     *
     * @param appStartDateTime
     */
    public void setAppStartDateTime(Date appStartDateTime)
    {
        this.appStartDateTime = appStartDateTime;
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
        Appointment.count = count;
    }

    /**
     * mutator of gpId
     *
     * @param gpId
     */
    public void setGpId(int gpId)
    {
        this.gpId = gpId;
    }

    /**
     * mutator of patientId
     *
     * @param patientId
     */
    public void setPatientId(int patientId)
    {
        this.patientId = patientId;
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
     * override toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "" + appointmentId + "," + patientId + "," + branchId + "," + gpId + "," + reasonId + ","
                + ((appStartDateTime.getYear() + 1900) + "/" + (appStartDateTime.getMonth() + 1) +"/" + appStartDateTime.getDate())+ ","
                + appStartDateTime.toString().substring(11, 16) + "," + appEndDateTime.toString().substring(11, 16);
    }
}