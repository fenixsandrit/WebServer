package com.company.fileworker;

import com.company.fileworker.Commands.FileCommandFactory;
import com.company.fileworker.Commands.ICommand;
import com.company.fileworker.executors.CopyExecutor;
import com.company.fileworker.executors.Md5Executor;
import com.company.fileworker.resulters.ConsoleResulter;
import com.company.fileworker.resulters.Resulter;

public class Main
{
    public static void main(String[] args)
    {
        FileWorker fileWorker = new FileWorker("/home/iyumagulov/test/test.txt");
        fileWorker.setIsRecursive(false);

        Resulter consoleResulter = new ConsoleResulter(fileWorker);
        //Resulter htlmResulter = new HTMLResulter(fileWorker);


        FileCommandFactory fileCommandFactory = new FileCommandFactory(fileWorker);
        CommandQueue.addCommand(fileCommandFactory.instanceCommand(new CopyExecutor()));
        CommandQueue.addCommand(fileCommandFactory.instanceCommand(new Md5Executor()));

        for (ICommand command: CommandQueue.getIterator())
        {
            command.execute();
            consoleResulter.showResult();
            //htlmResulter.showResult();
        }
    }
}
