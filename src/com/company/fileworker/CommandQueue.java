package com.company.fileworker;

import com.company.fileworker.Commands.ICommand;

import java.util.ArrayDeque;
import java.util.Queue;

public class CommandQueue
{
    private static Queue<ICommand> commandHistory = new ArrayDeque<>();

    public static void addCommand(ICommand command)
    {
        commandHistory.offer(command);
    }

    public static Iterable<ICommand> getIterator()
    {
        return commandHistory;
    }

    public static ICommand popCommand()
    {
        return commandHistory.peek();
    }
}
