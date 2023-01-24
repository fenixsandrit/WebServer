package com.company.threadDispatcher.gui;

import javax.swing.*;
import java.awt.*;

public class SimpleGUI
{
    private static final JFrame frame = new JFrame("Thread Dispatcher");
    private static final JTextArea textArea = new JTextArea();

    public SimpleGUI()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().add(BorderLayout.CENTER, textArea);
        frame.setVisible(true);
    }
    public static void setText(String text)
    {
        textArea.setText(null);
        textArea.append(text);
    }
}
