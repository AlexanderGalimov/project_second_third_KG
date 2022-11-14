package ru.vsu.cs.galimov.tasks.drawers;

import java.awt.*;

public class BresenhaimLineDrawer {

    public class BresenhamLineDrawer implements LineDrawer{
        private PixelDrawer pd;

        public BresenhamLineDrawer(PixelDrawer pd) {
            this.pd = pd;
        }

        private int sign(int x) {
            return Integer.compare(x, 0);
        }

        public void drawLine(int x1, int y1, int x2, int y2) {
            int pdx, pdy, xORyWay1, xORyWay2, a;

            int dx = x2 - x1;
            int dy = y2 - y1;

            if (Math.abs(dx) > Math.abs(dy)) {
                pdx = (int) Math.signum(dx);
                pdy = 0;
                xORyWay1 = Math.abs(dy);
                xORyWay2 = Math.abs(dx);
            } else {
                pdx = 0;
                pdy = (int) Math.signum(dy);
                xORyWay1 = Math.abs(dx);
                xORyWay2 = Math.abs(dy);
            }

            a = xORyWay2 / 2;
            pd.drawPixel(x1, y1, Color.BLACK);


            for (int t = 0; t < xORyWay2; t++)
            {
                a -= xORyWay1;
                if (a < 0) {
                    a += xORyWay2;
                    x1 += (int) Math.signum(dx);
                    y1 += (int) Math.signum(dy);
                } else {
                    x1 += pdx;
                    y1 += pdy;
                }

                pd.drawPixel(x1, y1, Color.BLACK);
            }
        }
    }
}
