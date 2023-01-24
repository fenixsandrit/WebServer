package com.company.fileworker.executors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class CopyExecutor implements IExecutable
{

    @Override
    public String process(File originalFile)
    {
        String originalFileName = originalFile.getName();

        File copyFile = new File(originalFile.getParentFile().getPath()
                + "//"
                + originalFileName.substring(0, originalFileName.lastIndexOf('.'))
                + "_copy."
                + getFileExtension(originalFile)
        );

        try
        {
            if(!copyFile.createNewFile())
            {
                throw new Exception("File copy is not created, there is file with same name");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(originalFile);
             FileOutputStream fos = new FileOutputStream(copyFile))
        {
            byte[] bytesBuffer = new byte[fis.available()];
            fis.read(bytesBuffer, 0, bytesBuffer.length);
            fos.write(bytesBuffer, 0, bytesBuffer.length);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //System.out.println(f.getAbsoluteFile() + " " + newFile.getAbsoluteFile());
        return "originalFile: " + originalFileName + ", copyFile:" + copyFile.getName();
    }
    private static String getFileExtension(File file)
    {
        if (file.getName().contains("."))
        {
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        else
        {
            return "";
        }
    }
}
