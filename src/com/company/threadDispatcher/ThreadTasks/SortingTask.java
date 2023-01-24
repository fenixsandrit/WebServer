package com.company.threadDispatcher.ThreadTasks;


import java.util.*;

public class SortingTask extends ThreadedTask {

    public static final int LIMIT = 9999999;

    public SortingTask()
    {
        super("CalculationTask", () -> {
            List<Integer> listToSort = new ArrayList<>();

            for (int i = 0; i < LIMIT; i++)
            {
                listToSort.add((int) (Math.random() * 100000));
            }

            Collections.sort(listToSort);
        });
    }

}
