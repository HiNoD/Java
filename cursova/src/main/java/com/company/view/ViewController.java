package com.company.view;

import com.company.toDoObjects.IToDoObject;

import java.util.Stack;

public class ViewController implements IViewController {
    Stack<IToDoObject> objs = new Stack<>();

    @Override
    public void addToViewer(IToDoObject newObj) {
        objs.add(newObj);
    }

    @Override
    public void deleteLastView() {
        objs.pop();
    }

    @Override
    public IToDoObject getLast() {
        return objs.peek();
    }

    @Override
    public void display() {
        if(objs.size() > 0) {
            System.out.println(objs.peek());
        }
    }

    @Override
    public boolean empty() {
        return objs.empty();
    }
}
