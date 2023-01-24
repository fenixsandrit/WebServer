package com.company.threadDispatcher.ThreadTasks;

import com.company.threadDispatcher.ThreadDispatcher;

public class ProxyThreadedTask extends ThreadedTask
{
    private ThreadedTask threadedTask;

    public ProxyThreadedTask(ThreadedTask threadedTask)
    {
        this.threadedTask = threadedTask;
        name = threadedTask.name;
    }

    @Override
    public void run()
    {
        ThreadDispatcher.threadWasStarted(this);
        threadedTask.start();

        try
        {
            threadedTask.join();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        ThreadDispatcher.threadWasDone(this);
    }
}
