package controller;

import connector.DbConnector;
import model.Appointment;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * This class AppointmentController is to control the appointment
 * To read appointment information from file and add a new appointment
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class AppointmentController
{

    /**
     * field
     */
    private ArrayList<Appointment> listOfAppointment;
    private DbConnector dbConnector;

    /**
     * This is the default constructor
     */
    public AppointmentController()
    {
        listOfAppointment = new ArrayList<>();
        dbConnector = new DbConnector();
    }

    /**
     * This is the parameterized constructor
     *
     * @param listOfAppointment the list of appointment object
     * @param dbConnector       the DbConnector object
     */
    public AppointmentController(ArrayList<Appointment> listOfAppointment, DbConnector dbConnector)
    {
        this.listOfAppointment = listOfAppointment;
        this.dbConnector = dbConnector;
    }

    /**
     * This method is to get the list of appointment
     *
     * @return appointment Arraylist
     */
    public ArrayList<Appointment> getListOfAppointment()
    {
        return listOfAppointment;
    }

    /**
     * This method is to set the list of appointment
     *
     * @param listOfAppointment the list of appointment
     */
    public void setListOfAppointment(ArrayList<Appointment> listOfAppointment)
    {
        this.listOfAppointment = listOfAppointment;
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
     * This method is to add a new appointment to the appointment database.
     *
     * @param patientId           the id of patient
     * @param branchId            the id of branch
     * @param gpId                the id of gp
     * @param reasonId            the id of the reason
     * @param appStartDateTimeStr the start date time of the appointment
     * @param appEndDateTimeStr   the end date time of the appointment
     * @throws ParseException
     */
    public void addNewAppointment(int patientId, int branchId, int gpId, int reasonId, String appStartDateTimeStr, String appEndDateTimeStr) throws ParseException
    {
        Date appStartDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(appStartDateTimeStr);
        Date appEndDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(appEndDateTimeStr);
        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setBranchId(branchId);
        appointment.setGpId(gpId);
        appointment.setReasonId(reasonId);
        appointment.setAppStartDateTime(appStartDateTime);
        appointment.setAppEndDateTime(appEndDateTime);
        this.listOfAppointment.add(appointment);
    }

    /**
     * This method is to read Appointment.txt to get the appointment history information
     */
    public void fetch()
    {
        dbConnector.setFileName("Appointment.txt");
        dbConnector.setHasHeader(true);
        ArrayList<String> lines = dbConnector.readDataFromFile();
        for (String line : lines)
        {
            // AppointmentId,PatientId,BranchId,GpId,ReasonId,AppDateTime
            // 1,1,1,1,1,2021/09/10 10:00
            String[] lineArray = line.split(",");
            Appointment appointment = new Appointment();
            appointment.setAppointmentId(Integer.parseInt(lineArray[0]));
            appointment.setPatientId(Integer.parseInt(lineArray[1]));
            appointment.setBranchId(Integer.parseInt(lineArray[2]));
            appointment.setGpId(Integer.parseInt(lineArray[3]));
            appointment.setReasonId(Integer.parseInt(lineArray[4]));
            try
            {
                Date appStartDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(lineArray[5] + " " + lineArray[6]);
                Date appEndDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(lineArray[5] + " " + lineArray[7]);
                appointment.setAppStartDateTime(appStartDateTime);
                appointment.setAppEndDateTime(appEndDateTime);
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
            listOfAppointment.add(appointment);
        }
    }

    /**
     * This method is to get the appointment by the id
     *
     * @param appointmentId the id of appointment
     * @return Appointment object
     */
    private Appointment getAppointmentById(int appointmentId)
    {
        for (Appointment appointment : listOfAppointment)
        {
            if (appointment.getAppointmentId() == appointmentId)
            {
                return appointment;
            }
        }
        System.out.println("Error! There are no this appointment!");
        return null;
    }

    /**
     * This method is to generate the appointment report
     * AppointmentReport for All branches  [{gpId: appCount}]
     *
     * @return a hashmap for the report
     */
    public HashMap<Integer, Integer> generateAppointmentReport()
    {
        // [{1:3},{2:4},{3:2}]  {gpId: appCount}
        HashMap<Integer, Integer> bookings = new HashMap<>();
        Date currentDateTime = new Date();
        for (Appointment appointment : listOfAppointment)
        {
            if (appointment.getAppStartDateTime().after(currentDateTime))
            {
                if (bookings.get(appointment.getGpId()) != null)
                {
                    bookings.replace(appointment.getGpId(), bookings.get(appointment.getGpId()) + 1);
                }
                else
                {
                    bookings.put(appointment.getGpId(), 1);
                }
            }
        }
        return bookings;
    }

    /**
     * This method is to generate the appointment report
     * AppointmentReport for one branch [{gpId: appCount}]
     *
     * @param branchId the id of branch
     * @return a hashmap for the report
     */
    public HashMap<Integer, Integer> generateAppointmentReport(int branchId)
    {
        // [{1:3},{2:4},{3:2}]
        HashMap<Integer, Integer> bookings = new HashMap<>();
        Date currentDateTime = new Date();
        for (Appointment appointment : listOfAppointment)
        {
            if (appointment.getBranchId() == branchId)
            {
                if (appointment.getAppStartDateTime().after(currentDateTime))
                {
                    if (bookings.get(appointment.getGpId()) != null)
                    {
                        bookings.replace(appointment.getGpId(), bookings.get(appointment.getGpId()) + 1);
                    }
                    else
                    {
                        bookings.put(appointment.getGpId(), 1);
                    }
                }
            }
        }
        return bookings;
    }

    /**
     * This method is to get the Reason report
     *
     * @param startDate the date of the start date
     * @param endDate   the date of the end date
     * @return a hashmap for the report
     */
    public HashMap<Integer, Integer> generateReasonReport(Date startDate, Date endDate)
    {
        // {1: 2, 2: 3}
        HashMap<Integer, Integer> resultMap = new HashMap<>();
        for (Appointment appointment : listOfAppointment)
        {
            Date appDate = appointment.getAppStartDateTime();
            // (appDate >= startDate) && (appDate <= endDate)
            if ((appDate.after(startDate) || appDate.equals(startDate)) && (appDate.before(endDate) || appDate.equals(endDate)))
            {
                int reasonId = appointment.getReasonId();
                if (resultMap.get(reasonId) != null)
                {
                    int count = resultMap.get(reasonId);
                    count += 1;
                    resultMap.replace(reasonId, count);
                }
                else
                    resultMap.put(appointment.getReasonId(), 1);
            }
        }

        return resultMap;
    }

    /**
     * This method is to get the appointment id
     *
     * @return Id int
     */
    public int getNewId()
    {
        int maxID = 0;
        for (Appointment appointment : listOfAppointment)
        {
            if (appointment.getAppointmentId() > maxID)
            {
                maxID = appointment.getAppointmentId();
            }
        }
        return maxID;
    }

    /**
     * This method is to find the appointment start time adn the end time
     *
     * @param gpId the id of gp
     * @param date the date
     * @return time arraylist
     */
    public ArrayList<LocalTime[]> getTimeFrameByGpAndDate(int gpId, LocalDate date)
    {
        // use loop to find AppStartTime, AppEndTime {[10:00,10:05], [14:00,14:05]}
        ArrayList<LocalTime[]> timesFrames = new ArrayList<>();
        for (Appointment appointment : listOfAppointment)
        {

            LocalDate localDate = appointment.getAppEndDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (appointment.getGpId() == gpId && localDate.equals(date))
            {
                LocalTime startTime = appointment.getAppStartDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                LocalTime endTime = appointment.getAppEndDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                LocalTime[] timeFrame = {startTime, endTime};
                timesFrames.add(timeFrame);
            }
        }
        return timesFrames;
    }

    /**
     * This method is to add an appointment to the file
     *
     * @throws FileNotFoundException
     */
    public void passNewAppointment() throws FileNotFoundException
    {
        Appointment appointment = getAppointmentById(getNewId());
        String line = "\n" + appointment.toString();
        dbConnector.writeNewLineToFile(line);
    }

}
