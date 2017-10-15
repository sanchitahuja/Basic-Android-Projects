package com.example.pramo.to_dolist.Dbhelper;

/**
 * Created by pramo on 07-02-2017.
 */

public class Todo {
    int tid;
    String task;
    boolean check;

    public Todo(int tid, String task, boolean check) {
        this.tid = tid;
        this.task = task;
        this.check = check;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
