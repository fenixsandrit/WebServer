package com.company.fileworker.Commands;

import com.company.fileworker.FileWorker;
import com.company.fileworker.executors.IExecutable;

public class FileCommandFactory implements CommandFactory
{
    private FileWorker fileworker;
    public FileCommandFactory(FileWorker fileworker)
    {
        this.fileworker = fileworker;
    }
    @Override
    public ICommand instanceCommand(IExecutable executor)
    {
        return new FileCommand(fileworker, executor);
    }
}
