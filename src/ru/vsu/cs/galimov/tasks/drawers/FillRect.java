package ru.vsu.cs.galimov.tasks.drawers;

import java.awt.*;

public class FillRect {
    private final PixelDrawer pd;

    public FillRect(PixelDrawer pd) {
        this.pd = pd;
    }

    public void drawRect(int x, int y, int width, int height, Color color){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pd.drawPixel(x + j, y + i , color);
            }
        }
    }
}
