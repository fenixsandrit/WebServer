package com.company.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(8000); // серверсокет прослушивает порт 4004
                System.out.println("Сервер запущен!"); // хорошо бы серверу
                //   объявить о своем запуске
                clientSocket = server.accept(); // accept() будет ждать пока
                //кто-нибудь не захочет подключиться
                try { // установив связь и воссоздав сокет для общения с клиентом можно перейти
                    // к созданию потоков ввода/вывода.
                    // теперь мы можем принимать сообщения
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // и отправлять
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    //String word = in.lines().reduce((x,y)->x + " " + y).get(); // ждём пока клиент что-нибудь нам напишет
                    //System.out.println(word);
                    // не долго думая отвечает клиенту
                    out.write(HTTP_RESPONSE + "\n");
                    out.flush(); // выталкиваем все из буфера

                }
                finally
                { // в любом случае сокет будет закрыт
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            }
            finally
            {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
    public static final String HTTP_RESPONSE = """
            HTTP/1.1 200 OK
            Date: Mon, 27 Jul 2009 12:28:53 GMT
            Server: Apache/2.2.14 (Win32)
            Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
            Content-Length: 88
            Content-Type: text/html
            Connection: Closed
            
            <html>
            <body>
            <h1>hello</h1>
            </body>
            </html>
            """;
}
