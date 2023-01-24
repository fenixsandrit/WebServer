package com.company.fileworker.resulters;

import com.company.fileworker.FileWorker;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HTMLResulter extends Resulter {

    public HTMLResulter(FileWorker fileworker)
    {
        super(fileworker);
    }

    @Override
    public void showResult()
    {
        File htmlResult = new File(fileworker.getPath() + "\\"  + "HtmlResult.html");
        String result = fileworker.getResult();
        StringBuilder prefix = new StringBuilder("<html>\n" +
                " <head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <title>Моя первая веб-страница</title>\n" +
                " </head>\n" +
                " <body>\n" +
                "\n" +
                "  <h1>Html_Result</h1>\n" +
                "  <p>");

        prefix.append(result).append("<br>");

        String suffix = "</p>\n\n </body>\n</html>";

        try (FileOutputStream fos = new FileOutputStream(htmlResult))
        {
            fos.write(prefix.append(suffix).toString().getBytes());
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        try
        {
            Desktop.getDesktop().browse(htmlResult.toURI());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
