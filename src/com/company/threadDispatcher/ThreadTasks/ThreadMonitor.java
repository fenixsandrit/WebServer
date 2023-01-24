package com.company.threadDispatcher.ThreadTasks;

import com.company.threadDispatcher.gui.SimpleGUI;

import java.util.HashMap;
import java.util.Map;

public class ThreadMonitor extends ThreadedTask
{
    public static final String THREADS_IN_QUEUE = "THREADS_IN_QUEUE";
    public static final String RUNNING_THREADS = "RUNNING_THREADS";
    public static final String TEXT_SEPARATOR = "---------------------------------------------------------------------";
    private static Map<ThreadedTask, String> runningThreads = new HashMap<>();

    private static Map<ThreadedTask, String> newThreads = new HashMap<>();

    public ThreadMonitor()
    {
        super("ThreadMonitor");
        this.setDaemon(true);
    }

    @Override
    public void run()
    {
        StringBuffer text = new StringBuffer();

        while(!Thread.interrupted())
        {
            text.append(THREADS_IN_QUEUE).append(":\n").append(TEXT_SEPARATOR).append("\n");
            synchronized (newThreads)
            {
                newThreads.values().forEach(t -> text.append(t).append('\n'));
            }

            text.append(RUNNING_THREADS).append(":\n").append(TEXT_SEPARATOR).append("\n");
            synchronized (runningThreads)
            {
                runningThreads.values().forEach(t -> text.append(t).append('\n'));
            }

            SimpleGUI.setText(text.toString());
            text.delete(0, text.length());

            try
            {
                sleep(700);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public void addInNewThread(ThreadedTask threadedTask)
    {
        synchronized (newThreads)
        {
            newThreads.put(threadedTask, threadedTask.toString());
        }
    }

    public void addInRunnableThread(ThreadedTask threadedTask)
    {
        synchronized (newThreads)
        {
            if(newThreads.containsKey(threadedTask))
            {
                newThreads.remove(threadedTask);
            }
        }
        synchronized (runningThreads)
        {
            runningThreads.put(threadedTask, threadedTask.toString());
        }
    }

    public void removeThread(ThreadedTask threadedTask)
    {
        synchronized (runningThreads)
        {
            if(runningThreads.containsKey(threadedTask))
            {
                runningThreads.remove(threadedTask);
            }
        }
    }

}
