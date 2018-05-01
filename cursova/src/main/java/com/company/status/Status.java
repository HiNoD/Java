package com.company.status;

public class Status implements IStatus {
    private String name = "undefined";

    public Status(String statusName) {
        name = statusName;
    }

    @Override
    public String toString() {
        return name;
    }
}
