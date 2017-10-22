package com.example.habittracker2017;

import android.location.Location;

import java.io.File;
import java.util.Date;

/**
 * Created by Alex on 2017-10-20.
 */

public class HabitEvent {
    private String comment;
    private File picture;
    private Location location;
    private Date date;
    private Habit habit;

    HabitEvent(String comment){
        //TODO
    }

    HabitEvent(String comment, File picture){
        //TODO
    }

    HabitEvent(String comment, Location location){
        //TODO
    }

    HabitEvent(String comment, File picture, Location location){
        //TODO
    }

    public void delete(){
        //TODO
    }

    private File scaledownPicture(File picture){
        return null; //TODO, or implement this in a control class
    }

    public String getComment() {
        return comment;
    }

    public File getPicture() {
        return picture;
    }

    public Location getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDate(Date date) {        //Used for testing purposes, so don't delete
        this.date = date;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }
}
