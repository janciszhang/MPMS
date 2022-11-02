package view;

import controller.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.StrictMath.round;

/**
 * This class MPMS is the software main class
 * start point in this class
 * it can log in the system by email and password
 * admin can enter date range, system can generator reason report
 * user can make an appointment by choosing branch, DP, date, time, reason
 * <p>
 * for integer input, it can enter 0 to return the home screen
 * for string input, if format errors up to three times, it will also return the home screen
 *
 * @author Jingmin Zhang, Mingliang Peng, Diwen Si
 * @version java 16.0 2021/10/15
 */
public class MPMS
{
    /**
     * field
     */
    private AppointmentController appointmentController;
    private BranchController branchController;
    private BranchGpController branchGpController;
    private GPController gpController;
    private boolean isAdmin;
    private boolean loginFlag;
    private ReasonController reasonController;
    private UserController userController;
    private int userId;

    /**
     * default construct
     */
    public MPMS()
    {
        appointmentController = new AppointmentController();
        reasonController = new ReasonController();
        branchController = new BranchController();
        gpController = new GPController();
        branchGpController = new BranchGpController();
        userController = new UserController();
        this.loginFlag = false;
        this.isAdmin = false;
    }

    /**
     * login to the system
     * user need input the email and password
     * user has three time for each input (just for format error)
     * if password format is right, but not match, it will return home screen
     * get the loginFlag that the login is Success or failed
     * get this user if it is admin, default flase
     * get the user id, default 0
     *
     * @return ArrayList, [Boolean, Boolean, int],[loginFlag, isAdmin, userId]
     */
    public boolean attemptLogin()
    {
        //choose to login
        UI.headScreenWithReturn("Login", "Please choose the option:");
        System.out.println("(1) Login\n(2) Exit system");
        System.out.print("\n>>> option: ");
        int option = Input.inputIntegerInRange(0, 2);
        switch (option)
        {
            case 0:
            {
                attemptLogin();
                break;
            }
            case 1:
            {
                UI.headScreenNoReturn("Login", "Please enter your email:");
                System.out.print("\n>>> email: ");
                String email = Input.inputFormatStringLimitTime("email", 3);
                if (!email.isEmpty())
                {
                    UI.headScreenNoReturn("Login", "Please enter your password:");
                    System.out.print("\n>>> password: ");
                    String password = Input.inputFormatStringLimitTime("password", 3);
                    if (!password.isEmpty())
                    {
                        if (userController.matchEmailAndPassword(email, password))
                        {
                            loginFlag = true;
                            isAdmin = userController.isUserAdmin(email, password);
                            userId = userController.getUserIdByEmail(email);
                            System.out.println("\nLog in success...");
                        }
                        else
                        {
                            System.out.println("\nError! Password and email are not matching!");
                            System.out.println("\nLog in failed...");
                            attemptLogin();
                        }
                    }
                    else
                    {
                        System.out.println("\nLog in failed...");
                        attemptLogin();
                    }
                }
                else
                {
                    System.out.println("\nLog in failed...");
                    attemptLogin();
                }
                break;
            }
            case 2:
            {
                exitSystem();
                break;
            }
            default:
            {
                break;
            }
        }
        return loginFlag;
    }

    /**
     * choose branch from branch list
     *
     * @param userId
     * @return int branchId
     * @throws FileNotFoundException
     * @throws ParseException
     */
    private int chooseBranch(int userId) throws FileNotFoundException, ParseException
    {
        int branchId = 0;
        // list all branches and user can choose one of them
        UI.headScreenWithReturn("Booking", "Please choose one of the branches above:");
        branchController.showBranchNameList();
        System.out.print("\n>>> option: ");
        int userSelection = Input.inputIntegerInRange(0, branchController.getListOfBranch().size());
        if (userSelection == 0)
        {
            makeNewAppointment(userId);
        }
        else
        {
            UI.headScreenWithReturn("Booking", "Please choose the option:");
            branchController.showBranchDetails(userSelection);
            System.out.println("(1) Book this clinic branch");
            System.out.print("\n>>> option: ");
            if (Input.inputIntegerInRange(0, 1) == 0)
            {
                makeNewAppointment(userId);
            }
            else
            {
                branchId = branchController.getBranchIdByOrderNumber(userSelection);
            }
        }
        return branchId;
    }

    /**
     * choose appointment date time
     * the date time should be valid
     * book at least one day in advance
     * according to GP's available date time
     *
     * @param userId
     * @param branchId
     * @param gpId
     * @return String[], reasonDateTime
     * @throws ParseException
     * @throws FileNotFoundException
     */
    private String[] chooseDateTime(int userId, int branchId, int gpId) throws ParseException, FileNotFoundException
    {
        String[] reasonDateTime = new String[4];
        // input date and time
        UI.headScreenNoReturn("Booking", "Please input an appointment date: ");
        System.out.print("\n>>> appointment date (YYYY/MM/DD): ");
        String userInputDateStr = Input.inputDateStringLimitTime(3);
        if (userInputDateStr.isEmpty())
        {
            makeNewAppointment(userId);
        }
        else
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate appDate = LocalDate.parse(userInputDateStr, formatter);
            //check the date is after current date
            if (!appDate.isAfter((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
            {
                System.out.println("\nAppointment date should not before current date!");
                makeNewAppointment(userId);
            }
            else
            {
                // check GP working day
                ArrayList<String> workingHourList = branchGpController.findWorkingHoursByGpIdAndBranchId(gpId, branchId);
                boolean isValidDayOfWeek = true;
                // loop all working day of this GP
                for (String workingHour : workingHourList)
                {
                    // if this day of week is valid
                    if (appDate.getDayOfWeek().toString().substring(0, 3).equalsIgnoreCase(workingHour.split(" ")[0]))
                    {
                        isValidDayOfWeek = true;
                        reasonDateTime = chooseTime(userId, branchId, gpId, appDate, workingHour);
                        break;
                    }
                    else
                    {
                        isValidDayOfWeek = false;
                    }
                }
                if (!isValidDayOfWeek)
                {
                    System.out.println("\nError! This GP does not work on " + appDate.getDayOfWeek() + "!");
                    makeNewAppointment(userId);
                }
            }
        }
        return reasonDateTime;
    }

    /**
     * choose GP from one branch
     * user choose a GP by self or
     * automatically assigned based on GP who has the least appointments in this branch
     *
     * @param userId
     * @param branchId
     * @return int GpId
     * @throws FileNotFoundException
     * @throws ParseException
     */
    private int chooseGP(int userId, int branchId) throws FileNotFoundException, ParseException
    {
        //ask if user want to choose a GP
        UI.headScreenWithReturn("Booking", "Do you want to choose a GP by yourself in this branch?");
        showGpListAndWorkingHours(gpController, branchGpController, branchGpController.findGpByBranch(branchId), branchId);
        System.out.println("(1) Yes\n(2) No");
        System.out.print("\n>>> option: ");
        int gpId = 0;
        int option = Input.inputIntegerInRange(0, 2);
        switch (option)
        {
            case 0:
            {
                makeNewAppointment(userId);
                break;
            }
            case 1:
            {
                UI.headScreenWithReturn("Booking", "Please choose one of the GPs above: ");
                // list all available GP in this branch and user can choose one of them
                showGpListAndWorkingHours(gpController, branchGpController, branchGpController.findGpByBranch(branchId), branchId);
                System.out.print("\n>>> option: ");
                List<Integer> gpIds = branchGpController.findGpByBranch(branchId);
                int userSelection = Input.inputIntegerInRange(0, gpIds.size());
                if (userSelection == 0)
                {
                    makeNewAppointment(userId);
                }
                else
                {
                    gpId = gpController.getGpIdByOrderNumber(gpIds, userSelection);
                    UI.headScreenWithReturn("Booking", "Please choose the option:");
                    gpController.showGpDetails(gpId);
                    System.out.println("(1) Book this GP");
                    System.out.print("\n>>> option: ");
                    if (Input.inputIntegerInRange(0, 1) == 0)
                    {
                        makeNewAppointment(userId);
                    }
                    else
                    {
                        gpId = gpController.getGpIdByOrderNumber(gpIds, userSelection);
                        System.out.println("\nThe GP you booking is: " + gpController.getNameByGpId(gpId));
                    }
                }
                break;
            }
            case 2:
            {
                // get the Less Booking GP
                HashMap<Integer, Integer> bookings = appointmentController.generateAppointmentReport(branchId);
                for (int assignedGPId : branchGpController.findGpByBranch(branchId))
                {
                    if (!bookings.containsKey(assignedGPId))
                    {
                        gpId = assignedGPId;
                    }
                }
                if (gpId == 0)
                {
                    int minValue = 9999;
                    for (Map.Entry<Integer, Integer> entry : bookings.entrySet())
                    {
                        if (entry.getValue() < minValue)
                        {
                            minValue = entry.getValue();
                            gpId = entry.getKey();
                        }
                    }
                }
                System.out.println();
                gpController.showGpDetails(gpId);
                System.out.println("\nThe GP you booking is: " + gpController.getNameByGpId(gpId));
                break;
            }
            default:
            {
                break;
            }
        }
        return gpId;
    }

    /**
     * choose appointment time
     * the time should be valid
     * the time range should be in the opening hour
     * it cannot crash with other appointments time
     *
     * @param userId
     * @param branchId
     * @param gpId
     * @param appDate
     * @param workingHour
     * @return String[], reasonDateTime
     * @throws FileNotFoundException
     * @throws ParseException
     */
    private String[] chooseTime(int userId, int branchId, int gpId, LocalDate appDate, String workingHour) throws FileNotFoundException, ParseException
    {
        String[] reasonDateTime = new String[4];
        // Time
        UI.headScreenNoReturn("Booking", "Please input time you want to start: ");
        System.out.print("\n>>> appointment start time (hh24:mm): ");
        String startTimeStr = Input.inputTimeStringLimitTime(3);
        if (startTimeStr.isEmpty())
        {
            makeNewAppointment(userId);
        }
        else
        {
            UI.headScreenWithReturn("Booking", "Please choose the appointment type: ");
            reasonController.showReasonList();
            System.out.print("\n>>> option: ");
            int reasonId = Input.inputIntegerInRange(0, reasonController.getListOfReason().size());
            // enter 0 to return home page
            if (reasonId == 0)
            {
                makeNewAppointment(userId);
            }
            else
            {
                int minute = reasonController.getDurationById(reasonId);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");//HH24, hh12
                LocalTime workStartTime = LocalTime.parse(workingHour.split(" ")[1].split("~")[0], dateTimeFormatter);
                LocalTime workEndTime = LocalTime.parse(workingHour.split(" ")[1].split("~")[1], dateTimeFormatter);
                LocalTime appStartTime = LocalTime.parse(startTimeStr, dateTimeFormatter);
                LocalTime appEndTime = appStartTime.plusMinutes(minute);
                ArrayList<LocalTime[]> timeFrames = appointmentController.getTimeFrameByGpAndDate(gpId, appDate);
                // {[10:00,10:05], [14:00,14:05]}
                boolean isValidTime = true;
                // if this startTime - endTime is invalid (not in opening hour)
                if ((appStartTime.isBefore(workStartTime)) || (appEndTime.isAfter(workEndTime)))
                {
                    isValidTime = false;
                    System.out.println("\nError! Out of the working Hour! You cannot make the booking this time!");
                    makeNewAppointment(userId);
                }
                // if this startTime - endTime is valid (in opening hour)
                else
                {
                    // if this startTime - endTime is valid (not crash with other appointments)
                    for (LocalTime[] timeFrame : timeFrames)
                    {
                        // if crash with one of the appointment or not in opening hours
                        if (((appStartTime.isAfter(timeFrame[0]) || appStartTime.equals(timeFrame[0])) && appStartTime.isBefore(timeFrame[1])) || (appEndTime.isAfter(timeFrame[0]) && (appEndTime.isBefore(timeFrame[1]) || appEndTime.equals(timeFrame[1]))))
                        {
                            isValidTime = false;
                            System.out.println("\nError! This time has been booking! You cannot make the booking this time!");
                            makeNewAppointment(userId);
                            break;
                        }
                    }
                }
                if (isValidTime)
                {
                    reasonDateTime[0] = "" + reasonId;
                    reasonDateTime[1] = appDate.getYear() + "/" + appDate.getMonthValue() + "/" + appDate.getDayOfMonth();
                    reasonDateTime[2] = appStartTime.toString();
                    reasonDateTime[3] = appEndTime.toString();
                    System.out.println("\nYour booking will be processed shortly...");
                }
            }
        }
        return reasonDateTime;
    }

    /**
     * exit the system
     * clear memory
     * stop run the program
     */
    public void exitSystem()
    {
        UI.exitScreen();
        // clear
        appointmentController.getListOfAppointment().clear();
        reasonController.getListOfReason().clear();
        branchController.getListOfBranch().clear();
        gpController.getListOfGp().clear();
        branchGpController.getListOfBranchGp().clear();
        userController.getListOfUser().clear();
        System.exit(0);
    }

    /**
     * generate report that appointment distribution in different reason
     * user can input start date and end date
     * if date range valid, it will show the distribution percentage
     * if the date input is valid, it has three times to input
     * error input three times, it will return home screen
     */
    public void generateReasonReport()
    {
        // choose generate report option
        UI.headScreenWithReturn("Admin home", "Please choose the option");
        System.out.println("(1) Generate report\n(2) Exit system");
        System.out.print("\n>>> option: ");
        int option = Input.inputIntegerInRange(0, 2);
        switch (option)
        {
            case 0:
            {
                generateReasonReport();
                break;
            }
            case 1:
            {
                UI.headScreenNoReturn("Appointment reasons report", "Please enter the start date: ");
                System.out.print("\n>>> start time (YYYY/MM/DD): ");
                String startDateStr = Input.inputDateStringLimitTime(3);
                if (startDateStr.isEmpty())
                {
                    generateReasonReport();
                }
                else
                {
                    UI.headScreenNoReturn("Appointment reasons report", "Please enter the end date :");
                    System.out.print("\n>>> end time (YYYY/MM/DD): ");
                    String endDateStr = Input.inputDateStringLimitTime(3);
                    if (endDateStr.isEmpty())
                    {
                        generateReasonReport();
                    }
                    else
                    {
                        try
                        {
                            Date startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(startDateStr + " 00:00");
                            Date endDate = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(endDateStr + " 23:59");
                            if (startDate.after(endDate))
                            {
                                System.out.println("\nError! Start date should before end date!");
                                generateReasonReport();
                            }
                            else
                            {
                                UI.headScreenNoReturn(" Appointment reasons report  ", "Here are the Reason Report during " + startDateStr + "-" + endDateStr + ":");
                                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                                HashMap<Integer, Integer> resultMap = appointmentController.generateReasonReport(startDate, endDate);
                                int resultSum = 0;
                                for (Integer integer : resultMap.values())
                                {
                                    resultSum += integer;
                                }
                                for (int i = 0; i < reasonController.getListOfReason().size(); i++)
                                {
                                    if (resultMap.containsKey(reasonController.getReasonIdByIndex(i)))
                                    {
                                        System.out.println(reasonController.getTypeById(reasonController.getReasonIdByIndex(i)) + ": " + (round(resultMap.get(i + 1) * 1.0 / resultSum * 10000)) / 100.0 + "%");
                                    }
                                    else
                                    {
                                        System.out.println(reasonController.getTypeById(reasonController.getReasonIdByIndex(i)) + ": " + "0%");
                                    }
                                }
                                System.out.println("∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨");
                                System.out.println();
                                generateReasonReport();
                            }
                        }
                        catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }
            case 2:
            {
                exitSystem();
                break;
            }
            default:
            {
                break;
            }
        }
    }

    /**
     * accessor of appointmentController
     *
     * @return appointmentController
     */
    public AppointmentController getAppointmentController()
    {
        return appointmentController;
    }

    /**
     * accessor of branchController
     *
     * @return branchController
     */
    public BranchController getBranchController()
    {
        return branchController;
    }

    /**
     * accessor of branchGpController
     *
     * @return branchGpController
     */
    public BranchGpController getBranchGpController()
    {
        return branchGpController;
    }

    /**
     * accessor of gpController
     *
     * @return gpController
     */
    public GPController getGpController()
    {
        return gpController;
    }

    /**
     * accessor of isAdmin
     *
     * @return isAdmin
     */
    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    /**
     * accessor of loginFlag
     *
     * @return loginFlag
     */
    public boolean getLoginFlag()
    {
        return loginFlag;
    }

    /**
     * accessor of reasonController
     *
     * @return reasonController
     */
    public ReasonController getReasonController()
    {
        return reasonController;
    }

    /**
     * accessor of userController
     *
     * @return userController
     */
    public UserController getUserController()
    {
        return userController;
    }

    /**
     * accessor of userId
     *
     * @return userId
     */
    public int getUserId()
    {
        return userId;
    }

    /**
     * Start point
     * run this program
     *
     * @param args
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws ParseException, FileNotFoundException
    {
        MPMS mpms = new MPMS();
        mpms.start();
        System.out.println();
    }

    /**
     * make an appointment
     * choose branch
     * choose GP in this branch
     * choose appointment date time and reason that also related to end time
     *
     * @param userId
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public void makeNewAppointment(int userId) throws ParseException, FileNotFoundException
    {
        //choose book appointment
        UI.headScreenWithReturn("User home", "Please choose the option");
        System.out.println("(1) Book appointment\n(2) Exit system");
        System.out.print("\n>>> option: ");
        int option = Input.inputIntegerInRange(0, 2);
        switch (option)
        {
            case 0:
            {
                makeNewAppointment(userId);
                break;
            }
            case 1:
            {
                // choose Branch
                int branchId = chooseBranch(userId);
                // choose GP
                int gpId = chooseGP(userId, branchId);
                // choose reason,date,startTime,endTime
                String[] reasonDateTime = chooseDateTime(userId, branchId, gpId);
                if (!(reasonDateTime[0] == null))
                {
                    // Confirm the information about the appointment
                    UI.headScreenWithReturn("Booking", "Please confirm your information:");
                    System.out.println("Date time: " + reasonDateTime[1] + " " + reasonDateTime[2]);
                    System.out.println("GP: " + gpController.getNameByGpId(gpId));
                    System.out.println("Type of appointment: " + reasonController.getReasonTypeById(Integer.parseInt(reasonDateTime[0])) + " (" + reasonController.getDurationById(Integer.parseInt(reasonDateTime[0])) + " minutes)");
                    System.out.println("Location: " + branchController.getBranchLocationById(branchId));
                    System.out.println("\n(1) Yes\n(2) No");
                    System.out.print("\n>>> option: ");
                    int option2 = Input.inputIntegerInRange(0, 2);
                    switch (option2)
                    {
                        case 1:
                        {
                            appointmentController.addNewAppointment(userId, branchId, gpId, Integer.parseInt(reasonDateTime[0]), reasonDateTime[1] + " " + reasonDateTime[2], reasonDateTime[1] + " " + reasonDateTime[3]);
                            appointmentController.passNewAppointment();
                            System.out.println("\nBooking success!");
                            System.out.println();
                            break;
                        }
                        case 2:
                        {
                            System.out.println("\nDo not Confirm the appointment information!");
                            System.out.println("\nBooking failed...");
                            break;
                        }
                        default:
                        {
                            break;
                        }
                    }
                    makeNewAppointment(userId);
                }
                break;
            }
            case 2:
            {
                exitSystem();
                break;
            }
            default:
            {
                break;
            }
        }
    }

    /**
     * mutator of appointmentController
     *
     * @param appointmentController
     */
    public void setAppointmentController(AppointmentController appointmentController)
    {
        this.appointmentController = appointmentController;
    }

    /**
     * mutator of branchController
     *
     * @param branchController
     */
    public void setBranchController(BranchController branchController)
    {
        this.branchController = branchController;
    }

    /**
     * mutator of branchGpController
     *
     * @param branchGpController
     */
    public void setBranchGpController(BranchGpController branchGpController)
    {
        this.branchGpController = branchGpController;
    }

    /**
     * mutator of gpController
     *
     * @param gpController
     */
    public void setGpController(GPController gpController)
    {
        this.gpController = gpController;
    }

    /**
     * mutator of isAdmin
     *
     * @param isAdmin
     */
    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    /**
     * mutator of loginFlag
     *
     * @param loginFlag
     */
    public void setLoginFlag(boolean loginFlag)
    {
        this.loginFlag = loginFlag;
    }

    /**
     * mutator of reasonController
     *
     * @param reasonController
     */
    public void setReasonController(ReasonController reasonController)
    {
        this.reasonController = reasonController;
    }

    /**
     * mutator of userController
     *
     * @param userController
     */
    public void setUserController(UserController userController)
    {
        this.userController = userController;
    }

    /**
     * mutator of userId
     *
     * @param userId
     */
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    /**
     * show GP name list with their working hours
     *
     * @param gpController
     * @param branchGpController
     * @param gpIds
     * @param branchId
     */
    private void showGpListAndWorkingHours(GPController gpController, BranchGpController branchGpController, List<Integer> gpIds, int branchId)
    {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("Here are the GP list for this branch:");
        System.out.println("---------------------------------------");
        int orderNumber = 1;
        for (int id : gpIds)
        {
            System.out.println("(" + orderNumber + ") " + gpController.getNameByGpId(id) + " " + branchGpController.findWorkingHoursByGpIdAndBranchId(id, branchId));
            orderNumber++;
        }
        System.out.println("∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨");
        System.out.println();
    }

    /**
     * start login and other function
     * for integer input, it can enter 0 to return the home screen
     * for string input, if format errors up to three times, it will also return the home screen
     *
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public void start() throws ParseException, FileNotFoundException
    {
        // fetch
        appointmentController.fetch();
        reasonController.fetch();
        branchController.fetch();
        gpController.fetch();
        branchGpController.fetch();
        userController.fetch();
        attemptLogin();
        if (loginFlag)
        {
            if (isAdmin)
            {
                generateReasonReport();
            }
            else
            {
                makeNewAppointment(userId);
            }
        }
    }

}
