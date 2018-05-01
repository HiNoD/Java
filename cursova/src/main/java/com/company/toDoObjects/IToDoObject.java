package com.company.toDoObjects;

import com.company.status.IStatus;

public interface IToDoObject {
    IToDoObject openList(int num);
    void createList(String listName);
    void createDeal(String dealName);
    void deleteList(int listNum);
    void deleteDeal(int dealNum);
    void renameTo(int num, String newName);
    void changeStatus(int dealNum, IStatus newStatus);
    void setName(String name);
    String getName();
    IStatus getStatus();
    String toString();
}
