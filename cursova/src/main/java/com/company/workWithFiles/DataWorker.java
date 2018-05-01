package com.company.workWithFiles;

import com.company.status.Completed;
import com.company.status.IStatus;
import com.company.status.InProcess;
import com.company.toDoObjects.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DataWorker implements IDataWorker {
    final String inProgress = " In process";
    final String completed = " Completed";
    IStatus defineStatus(String statusStr) {
        IStatus status = null;
        switch (statusStr) {
            case inProgress:
                status = new InProcess();
                break;
            case completed:
                status = new Completed();
                break;
        }

        return status;
    }

    @Override
    public IMainList convertDataFromFiles(File dir) throws Exception {
        IMainList list = new MainList();

        for (File file : dir.listFiles()) {
            String listName = file.getName().split("\\.")[0];
            IToDoList newList = new ToDoList(listName);

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line;
            line = reader.readLine();
            while (line != null) {
                String[] lineArr = line.split(":");
                IStatus dealStatus;
                dealStatus = defineStatus(lineArr[1]);
                IDeal newDeal = new Deal(lineArr[0], dealStatus);
                newList.addDeal(newDeal);

                line = reader.readLine();
            }
            list.addList(newList);
            reader.close();
        }

        return list;
    }

    @Override
    public void convertDataToFiles(IMainList mainList, File dir) throws Exception {
        List<IToDoList> lists = mainList.getLists();

        deleteDifferences(mainList, dir);

        String catalogPath = dir.toString() + "\\";
        for (IToDoList list : lists) {
            String pathStr = catalogPath + list.getName() + ".txt";

            File newFile = new File(pathStr);
            if(!newFile.exists()) {
                newFile.createNewFile();
                newFile.setExecutable(true);
            }
            FileWriter writer = new FileWriter(pathStr);

            List<IDeal> deals = list.getDeals();
            for (IDeal deal : deals) {
                String str = deal.toString();
                writer.write(str);
                writer.append('\n');

            }
            writer.flush();
            writer.close();
        }
    }

    List<String> getFileNamesFromList(List<IToDoList> lists) {
        List<String> fileNames = new ArrayList<>();
        for (IToDoList list : lists) {
            fileNames.add(list.getName());
        }
        return fileNames;
    }

    public void deleteDifferences(IMainList mainList, File dir) throws  Exception {
        IMainList filesList = convertDataFromFiles(dir);
        if (!mainList.equals(filesList)) {
            List<IToDoList> mainLists = mainList.getLists();
            List<IToDoList> loadedLists = filesList.getLists();

            List<String> mainFileNames = getFileNamesFromList(mainLists);
            List<String> fileNames = getFileNamesFromList(loadedLists);

            for (String fileName : fileNames) {
                if (!mainFileNames.contains(fileName)) {
                    fileName = dir.toString() + "\\" + fileName + ".txt";
                    File file = new File(fileName);
                    file.delete();
                }
            }
        }
    }
}