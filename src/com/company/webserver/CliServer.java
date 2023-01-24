package com.company.webserver;

import com.company.threadDispatcher.ThreadTasks.ThreadedTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CliServer extends ThreadedTask
{
    private static final int PORT = 8001;

    private static ServerSocket server;

    public static boolean IS_ACTIVE = false;


    private CliServer()
    {
        try
        {
            server = new ServerSocket(PORT);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        IS_ACTIVE = true;

        while (true)
        {
            try
            {
                Socket clientSocket = server.accept();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            if(!IS_ACTIVE)
            {
                //...
            }
        }
    }
}
