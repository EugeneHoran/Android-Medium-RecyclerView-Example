package com.eugene.recylerviewexample;

public class Log {
    private String item;

    public String getItem() {
        return this.item;
    }

    public void setItem(String strItem) {
        this.item = strItem;
    }

    Log logs;

    private void setLog(Log log) {
        this.logs = log;
    }

    private Log getLog() {
        return this.logs;
    }
}

