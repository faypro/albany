package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtils {
    public static String returnNextMonth () {
        //Creating Data Object
        Date dNow = new Date();

        //Creating Calendar Object for Gregorian calendar
        Calendar calendar = new GregorianCalendar();

        // Set calendar date to current date
        calendar.setTime(dNow);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM-YYYY");
        calendar.add(Calendar.MONTH, 1);
        return sdf.format(calendar.getTime());

    }
}
