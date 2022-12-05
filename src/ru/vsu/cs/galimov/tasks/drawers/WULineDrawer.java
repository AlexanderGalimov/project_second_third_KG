package ru.vsu.cs.galimov.tasks.drawers;

import java.awt.*;

public class WULineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public WULineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    private double fPart(double x) {
        int tmp = (int) x;
        return x - tmp; //вернёт дробную часть числа
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        if (x2 < x1) {
            int t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }

        int dx = x2 - x1;
        double dy = y2 - y1;

        if (dy == 0) {
            for (int i = 0; i <= dx; i++) {
                pd.drawPixel(x1 + i, y2, Color.BLACK);
            }
        }

        double gradient = 0;
        if (dx > dy) {
            gradient =  dy / dx;
            double pixY = y1 + gradient;
            pd.drawPixel(x1, y1, Color.BLACK);
            for (int x = x1; x < x2; ++x) {
                if (fPart(pixY) * 255 > 255) {
                    pd.drawPixel(x, (int)pixY, new Color(0, 0, 0, 0));
                    pd.drawPixel(x, (int)pixY + 1,new Color(0, 0, 0, 0));
                } else if (fPart(pixY) * 255 < 0) {
                    pd.drawPixel(x, (int)pixY, new Color(0, 0, 0, 255));
                    pd.drawPixel(x, (int)pixY + 1,new Color(0, 0, 0, 255));
                } else {
                    pd.drawPixel(x, (int)pixY, new Color(0, 0, 0, (int) (255 - fPart(pixY) * 255)));
                    pd.drawPixel(x, (int)pixY + 1,new Color(0, 0, 0, (int) (fPart(pixY) * 255)));
                }
                pixY += gradient;
            }
            pd.drawPixel(x2, y2, Color.BLACK);
        } else {
            gradient =  dx / dy;
            double pixX = x1 + gradient;
            pd.drawPixel(x1, y1, Color.BLACK);
            for (int y = y1; y < y2; ++y) {
                if (fPart(pixX) * 255 > 255) {
                    pd.drawPixel((int)pixX, y, new Color(0, 0, 0, 0));
                    pd.drawPixel((int)pixX, y + 1,new Color(0, 0, 0, 0));
                } else if (fPart(pixX) * 255 < 0) {
                    pd.drawPixel((int)pixX, y, new Color(0, 0, 0, 255));
                    pd.drawPixel((int)pixX, y + 1,new Color(0, 0, 0, 255));
                } else {
                    pd.drawPixel((int) pixX, y, new Color(0, 0, 0, (int) (255 - fPart(pixX) * 255)));
                    pd.drawPixel((int) pixX, y + 1, new Color(0, 0, 0, (int) (fPart(pixX) * 255)));
                }
                pixX += gradient;
            }
            pd.drawPixel(x2, y2, Color.BLACK);
        }

        if(y2 < y1){
            int t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }

        dx = x2 - x1;
        dy = y2 - y1;

        if (dx == 0) {
            for (int i = 0; i <= dy; i++) {
                pd.drawPixel(x2, y1 + i, Color.BLACK);
            }
        }

        if (dx > dy) {
//            gradient =  dy / dx;
//            double intery = y1 + gradient;
//            pd.drawPixel(x1, y1, Color.BLACK);
//            for (int x = x1; x < x2; ++x) {
//                if (fractionalPart(intery) * 255 > 255) {
//                    pd.drawPixel(x, (int)intery, new Color(0, 0, 0, 0));
//                    pd.drawPixel(x, (int)intery + 1,new Color(0, 0, 0, 0));
//                } else if (fractionalPart(intery) * 255 < 0) {
//                    pd.drawPixel(x, (int)intery, new Color(0, 0, 0, 255));
//                    pd.drawPixel(x, (int)intery + 1,new Color(0, 0, 0, 255));
//                } else {
//                    pd.drawPixel(x, (int) intery, new Color(0, 0, 0, (int) (255 - fractionalPart(intery) * 255)));
//                    pd.drawPixel(x, (int) intery + 1, new Color(0, 0, 0, (int) (fractionalPart(intery) * 255)));
//                }
//                intery += gradient;
//            }
//            pd.drawPixel(x2, y2, Color.BLACK);
        } else {
            gradient =  dx / dy;
            double interx = x1 + gradient;
            pd.drawPixel(x1, y1, Color.BLACK);
            for (int y = y1; y < y2; ++y) {
                if (fPart(interx) * 255 > 255) {
                    pd.drawPixel((int)interx, y, new Color(0, 0, 0, 0));
                    pd.drawPixel((int)interx, y + 1,new Color(0, 0, 0, 0));
                }
                if (fPart(interx) * 255 < 0) {
                    pd.drawPixel((int)interx, y, new Color(0, 0, 0, 255));
                    pd.drawPixel((int)interx, y + 1,new Color(0, 0, 0, 255));
                } else {
                    pd.drawPixel((int) interx, y, new Color(0, 0, 0, (int) (255 - fPart(interx) * 255)));
                    pd.drawPixel((int) interx, y + 1, new Color(0, 0, 0, (int) (fPart(interx) * 255)));
                }
                interx += gradient;
            }
            pd.drawPixel(x2, y2, Color.BLACK);
        }
    }
}