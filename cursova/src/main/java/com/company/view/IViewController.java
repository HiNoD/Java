package com.company.view;

import com.company.toDoObjects.IToDoObject;

public interface IViewController {
    void addToViewer(IToDoObject newObj);
    void deleteLastView();
    IToDoObject getLast();
    void display();
    boolean empty();
}
