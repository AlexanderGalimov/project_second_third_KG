package ru.vsu.cs.galimov.tasks.drawers;

import java.awt.*;

public class GraphicsLineDrawer implements LineDrawer {
    private final Graphics2D g2d;

    public GraphicsLineDrawer(Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        g2d.drawLine(x1,y1, x2, y2);
    }
}
