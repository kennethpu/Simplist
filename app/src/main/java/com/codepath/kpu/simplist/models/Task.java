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

    public Task() {
        super();
    }

    public Task(String name) {
        super();
        this.name = name;
    }
}
