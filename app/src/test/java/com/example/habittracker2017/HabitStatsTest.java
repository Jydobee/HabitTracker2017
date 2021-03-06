/*
*viewTodayFragment
*
* version 1.0
*
* Dec 3, 2017
*
*Copyright (c) 2017 Team 16 (Jonah Cowan, Alexander Mackenzie, Hao Yuan, Jacy Mark, Shu-Ting Lin), CMPUT301, University of Alberta - All Rights Reserved.
*You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
*You can find a copy of the license in this project. Otherwise please contact contact@abc.ca.
*
*/
package com.example.habittracker2017;

import android.util.Log;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test for HabitStats
 *
 * @author team 16
 * @version 1.0
 * @see StatManager
 * @since 1.0
 */

public class HabitStatsTest {

    @Test
    public void testDaysBetween(){
        ArrayList<String> testDays = new ArrayList<>();
        testDays.add("330-11-2017");
        testDays.add("331-11-2017");
        testDays.add("332-11-2017");
        testDays.add("333-11-2017");
        testDays.add("334-11-2017");
        testDays.add("335-12-2017");
        testDays.add("336-12-2017");

        HashMap<Integer, Boolean> testSchedule =new HashMap<>();
        testSchedule.put(1,true);
        testSchedule.put(2,true);
        testSchedule.put(3,true);
        testSchedule.put(4,true);
        testSchedule.put(5,true);
        testSchedule.put(6,true);
        testSchedule.put(7,true);

        String originalStart = "26-11-2017";
        String originalEnd = "03-12-2017";
        SimpleDateFormat converter = new SimpleDateFormat("dd-MM-yyyy");
        Date originalDate = new Date();
        Date newDate = new Date();
        try {
            originalDate = converter.parse(originalStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            newDate = converter.parse(originalEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<String> returnedDays = new ArrayList<>();
        returnedDays = StatManager.daysBetween(originalDate,newDate,testSchedule);

        assertEquals("wrong dates returned by daysBetween function", testDays, returnedDays);
    }

}
