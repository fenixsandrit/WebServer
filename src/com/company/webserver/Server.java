package com.company.webserver;

import com.company.threadDispatcher.ThreadDispatcher;
import com.company.threadDispatcher.ThreadTasks.ThreadedTask;
import com.company.webserver.controller.BaseController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server extends ThreadedTask
{
    private static int port;
    private static boolean isActive = false;
    private static ServerSocket server;
    private final BaseController controller;

    protected Server(int port, BaseController controller)
    {
        this.port = port;
        this.controller = controller;
        try
        {
            server = new ServerSocket(port);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        isActive = true;

        while (true)
        {
            try
            {
                Socket clientSocket = server.accept();

                ThreadDispatcher.getInstance().addInQueue(new ThreadedTask("ServerTask", () -> controller.process(clientSocket)));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            if(!isActive)
            {
                //...
            }
        }
    }
}
