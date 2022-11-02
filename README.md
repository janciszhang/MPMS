# MPMS
Monash Patient Management System (MPMS) 

Text-base Application Software Engineering

## Installation Guidelines
- The IDE for our product is: Intellij
- JDK version: JAVA 16.0.2
- Frame: MVC

## Description
For Patient/Admin to login
As a patient/Admin, you can input '1' to login.
Note: you can input '0' in command to back to login screen, and input '2' to quit the product.
You have to use the specific email and the corresponding password to login to the MPMS system, if you input the wrong format of the email address (need '@' and followed by '.'), the product will allow you to try to re-intput up to 3 times, if there is still not the correct format, then go back to login screen.

After inputing the correct format of the email address, you need to input the password.
If the password you input is not the correct format(greater than 7 characters, must be integer and alphabatics, at least 1 upper alphabatic and 1 integer), the product will allow you try to re-input up to 3 times, if there is still not the correct format, then go back to login screen.
If the password has correct format, but do not match with email address, then go back to login screen.


For Patient,
After inputing the matching email address and password, you can go to the Patient main screen.
At the Patient Main screen, you can choose function 1 'Book appointment', by inputting '1' in the command.
Note: you can input '0' in command to back to Patient main screen, and input '2' to quit the product.

After choosing 'Book appointment', you need to choose a clinic, which are listed alphabetically, you can select the given integer, the information of chosen clinic will be displayed, and input '1' to confirm.
Note: you can input '0' in command to back to Patient main screen.

After picking a clicic, the all availalbe GP and their working time will be displayed. You can choose to manually pick a GP by inputting '1', or allow the product automatically assign a gp for you, by inputting '2'.
If you choose to manully pick a GP, you can select the given integer, and input '1' to confirm.
If you want the product automatically assign a GP for you, then the GP with lowest appointment will be automatically assigned for you.
Note: you can input '0' in command to back to Patient main screen.

After picking a GP, you need to input the appointment date, using the format of (YYYY/MM/DD), and the appointment date should be started at next day.
After inputting the correct date, you can input the preferred time, using the format of (hh:mm in 24 hours). The booking information will be displayed, you can input '1' to confirm, or input '2' to back to Patient main screen.
Note: you can input '0' in command to back to Patient main screen.

If there is the available time for the selected GP, and there is no crash with existed appointments , then booking success! Otherwise, booking is failed. Then back to Patient main screen.



For Admin,
After inputing the matching email address and password, you can go to the Admin main screen.
At the Admin Main screen, you can choose function 1 'Generate report', by inputting '1' in the command.
Note: you can input '0' in command to back to Admin main screen, and input '2' to quit the product.

After choosing the 'Generate report', you need to input the start date for the data you want to collect, using the format of (YYYY/MM/DD).
Note: the start time is the 00:00 time at the date you input.
Then you need to input the end date for the data you want to collect, using the format of (YYYY/MM/DD).
Note: the start time is the 23:59 time at the date you input.
The end date should be the same as the start date or after the start date, then the report will be generated based on the selected period.
If there are all 0% in the selected period, which means there is no appointment


## Troubleshooting
Any troubleshooting related information that you can provide.

If the user cannot open the program, check the java version
