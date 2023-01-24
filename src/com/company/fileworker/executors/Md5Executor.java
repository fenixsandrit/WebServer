package com.company.fileworker.executors;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class Md5Executor implements IExecutable {

    @Override
    public String process(File file)
    {
        //System.out.println(file.getAbsoluteFile() + " " + result);
        return file.getName() + " " + getHash(file);
    }

    private static String getHash(File file)
    {
        StringBuilder resultHash = new StringBuilder();

        try
        {
            InputStream input = new FileInputStream(file);
            byte[] bytesBuffer = new byte[1024];
            MessageDigest md5MessageDigest = MessageDigest.getInstance("MD5");
            int numbersOfReadedBytes = 0;
            
            while (numbersOfReadedBytes != -1)
            {
                numbersOfReadedBytes = input.read(bytesBuffer);
                if(numbersOfReadedBytes > 0)
                {
                    md5MessageDigest.update(bytesBuffer, 0, numbersOfReadedBytes);
                }
            }
            input.close();

            byte[] hashBytes = md5MessageDigest.digest();
            for (byte b: hashBytes)
            {
                resultHash.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return resultHash.toString().toUpperCase();
    }
}
