package com.codepath.kpu.simplist.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by kpu on 1/23/16.
 */
@Table(name = "Tasks")
public class Task extends Model {
    // task name
    @Column(name = "Name")
    public String name;

    @Column(name = "Year")
    public int year;

    @Column(name = "Month")
    public int month;

    @Column(name = "Day")
    public int day;

    @Column(name = "Complete")
    public boolean complete;

    public Task() {
        super();
    }

    public Task(String name, int year, int month, int day, boolean complete) {
        super();
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.complete = complete;
    }
}
