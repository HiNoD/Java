package com.company.toDoObjects;

import com.company.status.IStatus;

import java.util.ArrayList;
import java.util.List;

public class MainList implements IMainList {
    List<IToDoList> lists = new ArrayList<>();

    public MainList() {
    }

    public MainList(List<IToDoList> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < lists.size(); i++) {
            res += (i + 1);
            res += ". ";
            res += lists.get(i).getName();
            res += '\n';
        }

        if(res.equals("")) {
            res = "Empty";
        }

        return res;
    }

    @Override
    public IToDoObject openList(int num) {
        num--;
        if(num < lists.size() && num >= 0) {
            return lists.get(num);
        }

        return null;
    }

    @Override
    public void createList(String listName) {
        lists.add(new ToDoList(listName));
    }

    @Override
    public void createDeal(String dealName) {
        System.out.println("You can't create deal here");
    }

    @Override
    public void deleteList(int listNum) {
        listNum--;
        if(listNum < lists.size()) {
            lists.remove(listNum);
        }
    }

    @Override
    public void deleteDeal(int dealNum) {
        System.out.println("You can't delete deal here");
    }

    @Override
    public void renameTo(int num, String newName) {
        num--;
        if(num < lists.size()) {
            lists.get(num).setName(newName);
        }
    }

    @Override
    public void changeStatus(int dealNum, IStatus newStatus) {
        System.out.println("You can't change status here");
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public IStatus getStatus() {
        return null;
    }

    @Override
    public List<IToDoList> getLists() {
        return lists;
    }

    @Override
    public void addList(IToDoList list) {
        lists.add(list);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MainList) {
            MainList list = (MainList) obj;
            return list.getLists().equals(this.getLists());
        }
        return false;
    }
}