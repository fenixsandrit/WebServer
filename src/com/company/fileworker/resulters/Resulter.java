package com.company.fileworker.resulters;

import com.company.fileworker.FileWorker;
import com.company.fileworker.executors.IExecutable;

import java.io.IOException;

abstract public class Resulter
{

    protected FileWorker fileworker;

    Resulter(FileWorker fileworker)
    {
        this.fileworker = fileworker;
    }

    abstract public void showResult();

    public void execute(IExecutable executor) throws IOException
    {
        fileworker.execute(executor);
    }
}
