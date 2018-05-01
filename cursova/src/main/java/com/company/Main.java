package com.company;

import com.company.handlers.CommandHandler;
import com.company.handlers.ICommandHandler;
import com.company.toDoObjects.*;
import com.company.view.ViewController;
import com.company.workWithFiles.DataWorker;
import com.company.workWithFiles.IDataWorker;

import java.io.File;
import java.util.*;

public class Main {
    static IMainList loadFiles(IDataWorker worker, File dir) {
        IMainList list = null;
        try {
            list = worker.convertDataFromFiles(dir);
        } catch (Exception ex) {
        }

        return list;
    }

    public static void main(String[] args) {
        final String mainPath = "src/main/java/com/company/TODO";

        File dir = new File(mainPath);
        if (!dir.exists()) {
            System.out.println("Directory \"" + dir.toString() + "\" is not exists");
            return;
        }

        IDataWorker dWorker = new DataWorker();
        IMainList mainList = loadFiles(dWorker, dir);

        if (mainList == null) {
            System.out.println("Failed to load files");
            return;
        }

        Scanner in = new Scanner(System.in);
        ICommandHandler handler = new CommandHandler();

        ViewController viewer = new ViewController();
        viewer.addToViewer(mainList);

        Timer timer = new Timer();
        IMainList timerMainList = mainList;
        TimerTask autoSave = new TimerTask() {
            public void run() {
                try {
                    dWorker.deleteDifferences(mainList, dir);
                    dWorker.convertDataToFiles(timerMainList, dir);
                } catch (Exception e) {
                    System.out.println("Auto save process failed");
                }
            }
        };
        timer.schedule(autoSave, 5000, 10000);

        while (!viewer.empty()) {
            viewer.display();
            String command = in.nextLine();
            handler.handleCommand(command, viewer);
        }

        timer.cancel();
        try {
            dWorker.convertDataToFiles(mainList, dir);
        } catch (Exception ex) {
            System.out.println("Failed to save files, last saved version of files was saved.");
        }
    }
}