package ru.vsu.cs.galimov.tasks.drawers;

import java.awt.*;

public class GraphicsPixelDrawer implements PixelDrawer{

    private final Graphics2D g2d;

    public GraphicsPixelDrawer(Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public void drawPixel(int x, int y, Color c) {
        g2d.setColor(c);
        g2d.fillRect(x,y,1,1);
    }
}
