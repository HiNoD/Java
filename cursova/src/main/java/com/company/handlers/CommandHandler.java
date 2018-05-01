package com.company.handlers;

import com.company.status.Completed;
import com.company.status.IStatus;
import com.company.status.InProcess;
import com.company.toDoObjects.IToDoObject;
import com.company.view.IViewController;

public class CommandHandler implements ICommandHandler {
    final int MIN_COMMAND_SIZE = 1;
    final int MIN_CREATE_COMMAND_SIZE = 3;
    final int MIN_RENAME_COMMAND_SIZE = 3;
    final int CHANGE_COMMAND_SIZE = 4;
    final int OPEN_COMMAND_SIZE = 2;

    final String CREATE_COMMAND = "create";
    final String OPEN_COMMAND = "open";
    final String DELETE_COMMAND = "delete";
    final String RENAME_COMMAND = "rename";
    final String CHANGE_COMMAND = "change";
    final String EXIT_COMMAND = "exit";

    final String LIST = "list";
    final String DEAL = "deal";
    final String STATUS = "status";
    final String STATUS_IN_PROCESS = "process";
    final String STATUS_COMPLETED = "completed";

    final String ARGS_COUNT_ERROR_MSG = "Invalid args count. ";
    final String INVALID_COMMAND_ERROR_MSG = "Invalid command. ";
    final String UNKNOWN_STATUS_ERROR_MSG = "Unknown status. ";
    final String UNKNOWN_COMMAND_ERROR_MSG = "Unknown command. ";

    final String CREATE_USAGE_EXAMPLE = "Use: create <list|deal> <name> ";
    final String OPEN_USAGE_EXAMPLE = "Use: open <number> ";
    final String DELETE_USAGE_EXAMPLE = "Use: delete <list|deal> <number> ";
    final String RENAME_USAGE_EXAMPLE = "Use: rename <number> <new name> ";
    final String STATUS_USAGE_EXAMPLE = "Use: \"in process\" or \"completed\" ";
    final String CHANGE_STATUS_USAGE_EXAMPLE = "Use: change status <number> <" + STATUS_IN_PROCESS + "|" + STATUS_COMPLETED + ">";

    final String NUMBER_PARSE_ERROR_MSG = "Error! Using non-number value ";

    String findCommandArgs(String[] arr, int start, int end) {
        String res = "";
        for (int i = start; i < end; i++) {
            res += arr[i];
            res += " ";
        }
        return res;
    }

    IStatus defineStatus(String statusStr) {
        IStatus status = null;
        switch (statusStr) {
            case STATUS_IN_PROCESS:
                status = new InProcess();
                break;
            case STATUS_COMPLETED:
                status = new Completed();
                break;
        }

        return status;
    }

    @Override
    public boolean handleCommand(String commandLine, IViewController viewer) {
        boolean result = true;
        String[] commandArr = commandLine.split(" ");
        try {
            if (commandArr.length < MIN_COMMAND_SIZE) {
                throw new Exception();
            }

            //String command = commandArr[0];
            switch (commandArr[0]) {
                case CREATE_COMMAND:
                    if (commandArr.length < MIN_CREATE_COMMAND_SIZE) {
                        throw new Exception(ARGS_COUNT_ERROR_MSG + CREATE_USAGE_EXAMPLE);
                    }

                    String commandArgs = findCommandArgs(commandArr, 2, commandArr.length);
                    if (commandArr[1].equals(LIST)) {
                        viewer.getLast().createList(commandArgs);
                    } else if (commandArr[1].equals(DEAL)) {
                        viewer.getLast().createDeal(commandArgs);
                    } else {
                        throw new Exception(INVALID_COMMAND_ERROR_MSG + CREATE_USAGE_EXAMPLE);
                    }
                    break;
                case OPEN_COMMAND:
                    if (commandArr.length != OPEN_COMMAND_SIZE) {
                        throw new Exception(ARGS_COUNT_ERROR_MSG + OPEN_USAGE_EXAMPLE);
                    }

                    int num = Integer.parseInt(commandArr[1]);

                    IToDoObject obj = viewer.getLast().openList(num);
                    if (obj != null) {
                        viewer.addToViewer(obj);
                    } else {
                        throw new Exception(INVALID_COMMAND_ERROR_MSG);
                    }
                    break;
                case DELETE_COMMAND:
                    if (commandArr[1].equals(LIST)) {
                        viewer.getLast().deleteList(Integer.parseInt(commandArr[2]));
                    } else if (commandArr[1].equals(DEAL)) {
                        viewer.getLast().deleteDeal(Integer.parseInt(commandArr[2]));
                    } else {
                        throw new Exception(INVALID_COMMAND_ERROR_MSG + DELETE_USAGE_EXAMPLE);
                    }
                    break;
                case RENAME_COMMAND:
                    if (commandArr.length < MIN_RENAME_COMMAND_SIZE) {
                        throw new Exception(ARGS_COUNT_ERROR_MSG + RENAME_USAGE_EXAMPLE);
                    }
                    viewer.getLast().renameTo(Integer.parseInt(commandArr[1]), findCommandArgs(commandArr, 2, commandArr.length));
                    break;
                case CHANGE_COMMAND:
                    if (commandArr.length == CHANGE_COMMAND_SIZE && commandArr[1].equals(STATUS)) {
                        IStatus status = defineStatus(commandArr[3]);
                        if (status != null) {
                            viewer.getLast().changeStatus(Integer.parseInt(commandArr[2]), status);
                        } else {
                            throw new Exception(UNKNOWN_STATUS_ERROR_MSG + STATUS_USAGE_EXAMPLE);
                        }
                    } else {
                        throw new Exception(ARGS_COUNT_ERROR_MSG + CHANGE_STATUS_USAGE_EXAMPLE);
                    }
                    break;
                case EXIT_COMMAND:
                    viewer.deleteLastView();
                    break;
                default:
                    System.out.println(UNKNOWN_COMMAND_ERROR_MSG);
                    break;
            }
        } catch (NumberFormatException ex) {
            System.out.println(NUMBER_PARSE_ERROR_MSG + ex.getMessage());
            result = false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = false;
        }
        return result;
    }
}