package com.company.webserver.controller;

import java.net.Socket;

public interface BaseController
{
    void process(Socket clientSocket);
}
