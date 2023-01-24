package com.company.threadDispatcher;

import com.company.threadDispatcher.ThreadTasks.ProxyThreadedTask;
import com.company.threadDispatcher.ThreadTasks.ThreadMonitor;
import com.company.threadDispatcher.ThreadTasks.ThreadedTask;

import java.util.*;

public class ThreadDispatcher
{

    private static volatile Queue<ThreadedTask> globalQueue;
    private static volatile ThreadDispatcher instance;
    private static volatile ThreadMonitor threadMonitor;
    private static final Object syncObject = new Object();
    private static int poolSize;
    private static volatile int activeThreadWorkerCount;

    private ThreadDispatcher()
    {
        poolSize = 3;
        globalQueue = new LinkedList<>();
        threadMonitor = new ThreadMonitor();
        threadMonitor.start();
    }

    public static ThreadDispatcher getInstance()
    {
        return instance == null ? new ThreadDispatcher() : instance;
    }

    public static Object getSyncObject()
    {
        return syncObject;
    }

    public static boolean canPeek()
    {
        return !globalQueue.isEmpty() && activeThreadWorkerCount < poolSize;
    }

    public void add(ThreadedTask task)
    {
        ProxyThreadedTask proxyThreadedTask = new ProxyThreadedTask(task);

        threadMonitor.addInNewThread(proxyThreadedTask);
        proxyThreadedTask.start();
    }

    public void addInQueue(ThreadedTask task)
    {
        ProxyThreadedTask proxyThreadedTask = new ProxyThreadedTask(task);

        threadMonitor.addInNewThread(proxyThreadedTask);
        synchronized (syncObject)
        {
            globalQueue.add(proxyThreadedTask);
        }
    }

    public static void threadWasDone(ThreadedTask task)
    {
        threadMonitor.removeThread(task);
    }

    public static int getPoolSize()
    {
        return poolSize;
    }

    void setPoolSize(int size)
    {
        poolSize = size;
    }

    public static ThreadedTask getTaskWithRemoval()
    {
        increaseActiveThreadWorkerCount();
        return globalQueue.poll();
    }

    public static void increaseActiveThreadWorkerCount()
    {
        activeThreadWorkerCount++;
    }

    public static void decreaseActiveThreadWorkerCount()
    {
        activeThreadWorkerCount--;
    }

    public static void threadWasStarted(ThreadedTask task)
    {
        threadMonitor.addInRunnableThread(task);
    }

}
