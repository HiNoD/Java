package com.company.toDoObjects;

import com.company.status.IStatus;
import com.company.status.InProcess;
import com.company.status.Status;

public class Deal implements IDeal {
    private String name;
    private IStatus status;

    public Deal() {
        name = "Undefined";
        status = new InProcess();
    }

    public Deal(String name) {
        this.name = name;
        this.status = new InProcess();
    }

    public Deal(String name, IStatus status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public IToDoObject openList(int num) {
        System.out.println("You can't open list here");
        return null;
    }

    @Override
    public void createList(String listName) {
        System.out.println("You can't create list here");
    }

    @Override
    public void createDeal(String dealName) {
        System.out.println("You can't create deal here");
    }

    @Override
    public void deleteList(int listNum) {
        System.out.println("You can't delete list here");
    }

    @Override
    public void deleteDeal(int dealNum) {
        System.out.println("You can't delete deal here");
    }

    @Override
    public void renameTo(int num, String newName) {
        //setName(newName);
    }

    @Override
    public void changeStatus(int dealNum, IStatus newStatus) {
        //status = newStatus;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return name + ": " + status.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deal) {
            Deal deal = (Deal) obj;
            return deal.getName().equals(this.getName());
        }

        return false;
    }
}