package com.vian4.SheepsHead.ui;

import javax.swing.*;

public class ChangeableText {
    private JLabel textLabel = new JLabel();
    private JFrame frame;
    private int x;
    private int y;
    private int width;
    private int height;

    public ChangeableText(JFrame frame, int x, int y) {
        this(frame, x, y, 80, 20);
    }

    public ChangeableText(JFrame frame, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frame = frame;
        setText("");
    }

    public void setText(String newText) {
        //use html so text is wrapped
        setRawText("<html><p>"+newText+"</p></html>");
    }
    public void setRawText(String newText) {
        frame.remove(textLabel);

        textLabel = new JLabel(newText);

        frame.add(textLabel);
        textLabel.setBounds(x,y,width, height);

        frame.repaint();
    }
}
