/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.util.Calendar;

/**
 *
 * @author Otto
 */
public class Utility {

    public String dateToString(Calendar c) {
        String date = "";
        date = "" + c.get(Calendar.YEAR) + (c.get(Calendar.MONTH) + 1) + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MINUTE) + c.get(Calendar.SECOND);
        return date;
    }

    public String completeDateToUpper(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String s = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + " 23:59:59";
        return s;
    }

    public String completeDateToLower(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String s = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + " 00:00:00";
        return s;
    }
}
