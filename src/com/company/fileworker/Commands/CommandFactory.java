package com.company.fileworker.Commands;

import com.company.fileworker.executors.IExecutable;

public interface CommandFactory {
    ICommand instanceCommand(IExecutable executor);
}
