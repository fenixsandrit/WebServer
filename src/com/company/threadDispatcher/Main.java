package com.company.threadDispatcher;

import com.company.threadDispatcher.ThreadTasks.SleepTask;
import com.company.threadDispatcher.ThreadTasks.SortingTask;
import com.company.threadDispatcher.gui.SimpleGUI;

public class Main
{

    public static void main(String[] args)
    {
        SimpleGUI gui = new SimpleGUI();

        ThreadDispatcher threadDispatcher = ThreadDispatcher.getInstance();
        threadDispatcher.setPoolSize(1);

        for (int i = 0; i < 20; i++)
        {
            threadDispatcher.addInQueue(new SortingTask());
            threadDispatcher.addInQueue(new SleepTask());
        }
        new ThreadWorker("1").start();
        new ThreadWorker("2").start();
        new ThreadWorker("3").start();
        new ThreadWorker("4").start();
        new ThreadWorker("5").start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        threadDispatcher.add(new SortingTask());
        threadDispatcher.add(new SortingTask());
        threadDispatcher.add(new SortingTask());



    }
}

