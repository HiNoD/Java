package com.company.toDoObjects;

import com.company.status.IStatus;

import java.util.List;

public interface IToDoList extends IToDoObject{
    List<IDeal> getDeals();
    void addDeal(IDeal deal);
}
