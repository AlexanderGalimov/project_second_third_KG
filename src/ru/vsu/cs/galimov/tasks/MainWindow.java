package ru.vsu.cs.galimov.tasks;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private DrawPanel panel;

    public MainWindow() throws HeadlessException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        panel = new DrawPanel();
        this.add(panel);

    }


}
