package com.company.threadDispatcher.ThreadTasks;

public class SleepTask extends ThreadedTask
{
    public SleepTask()
    {
        super("SleepTask", () -> {
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        );
    }

}
