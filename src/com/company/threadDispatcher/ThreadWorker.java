package com.company.threadDispatcher;

import com.company.threadDispatcher.ThreadTasks.ThreadedTask;

public class ThreadWorker extends Thread
{

    private String name;

    public ThreadWorker(String name)
    {
        this.setDaemon(true);
        this.name = name;
    }

    @Override
    public void run()
    {
        ThreadedTask task = null;
        while (true)
        {
            synchronized (ThreadDispatcher.getSyncObject())
            {
                if (ThreadDispatcher.canPeek())
                {
                    task = ThreadDispatcher.getTaskWithRemoval();
                }
            }

            if(task != null)
            {
                task.start();
                try
                {
                    task.join();
                    synchronized (ThreadDispatcher.getSyncObject())
                    {
                        ThreadDispatcher.decreaseActiveThreadWorkerCount();
                        ThreadDispatcher.getSyncObject().notifyAll();
                    }
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                task = null;
            }
            else
            {
                synchronized (ThreadDispatcher.getSyncObject())
                {
                    try {
                        ThreadDispatcher.getSyncObject().wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }

    }
}
