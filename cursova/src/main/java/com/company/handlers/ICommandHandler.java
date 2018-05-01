package com.company.handlers;

import com.company.view.IViewController;

public interface ICommandHandler {
    boolean handleCommand(String command, IViewController viewer);
}
