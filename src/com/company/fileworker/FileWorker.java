package com.company.fileworker;

import com.company.fileworker.executors.IExecutable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class FileWorker {

    private boolean isRecursive;

    private String path;

    private StringBuffer strResult;

    //private ArrayDeque<String> result;

    private Stack<String> result;

    public void setIsRecursive(boolean isRecursive)
    {
        this.isRecursive = isRecursive;
    }

    public boolean getIsRecursive()
    {
        return isRecursive;
    }
    public String getPath()
    {
        return path;
    }

    FileWorker(String path)
    {
        this.result = new Stack<>();
        this.path = path;
        this.strResult = new StringBuffer();
    }
    public String getResult()
    {
        return result.pop();
    }

    public void execute(IExecutable executor)
    {
        if(strResult.length() > 0)
        {
            strResult.delete(0,strResult.length());
        }

        try
        {
            if(isRecursive)
            {
                recursiveProcessing(executor);
            }
            else
            {
                simpleProcessing(executor);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //result.offer(strResult.toString());
        result.push(strResult.toString());
    }

    private void simpleProcessing(IExecutable executor) throws IOException
    {
        for(File file: Objects.requireNonNull(new File(path).listFiles()))
        {
            if(!file.isDirectory())
            {
                //result.offer(executor.process(f));
                strResult.append(executor.process(file)).append('\n');
            }
        }
    }

    private void recursiveProcessing(IExecutable executor) throws IOException
    {
        for(File file: Objects.requireNonNull(new File(path).listFiles()))
        {
            if (file.isDirectory())
            {
                recursiveProcessing(executor);
            }
            else
            {
                strResult.append(executor.process(file)).append('\n');
            }
        }
    }
}
