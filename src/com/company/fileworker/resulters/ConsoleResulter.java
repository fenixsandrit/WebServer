package com.company.fileworker.resulters;


import com.company.fileworker.Commands.ICommand;
import com.company.fileworker.FileWorker;
import com.company.fileworker.executors.IExecutable;

public class ConsoleResulter extends Resulter
{
    public ConsoleResulter(FileWorker fileworker)
    {
        super(fileworker);
    }
    @Override
    public void showResult()
    {
        System.out.println("ConsoleResulter:\n" + fileworker.getResult());
    }

    public static class FileCommand implements ICommand {

        private FileWorker fileWorker;
        private IExecutable executor;
        @Override
        public void execute(){
            fileWorker.execute(executor);
        }
        public FileCommand(FileWorker fileworker,IExecutable executor){
            this.fileWorker = fileworker;
            this.executor = executor;
        }


    }
}
