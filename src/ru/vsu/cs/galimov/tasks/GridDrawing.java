package ru.vsu.cs.galimov.tasks;

import ru.vsu.cs.galimov.tasks.drawers.LineDrawer;

import java.awt.*;
import java.text.DecimalFormat;

public class GridDrawing {
    private final ScreenConverter sc;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private final int DEFAULT_FONT_SIZE = 12;
    private Font font = null;
    private final int COEFCUT = 96;


    public GridDrawing(ScreenConverter sc) {
        this.sc = sc;
    }

    public void draw(LineDrawer ld, Graphics2D g2d) {
        drawCoordinateAndGrid(ld, g2d);
    }

    private void drawCoordinateAndGrid(LineDrawer ld, Graphics2D g2d) {
        g2d.setFont(getFont(DEFAULT_FONT_SIZE));
        // Draws a grid to the right of the coordinate line
        for (double x = 0; x < sc.getX() + sc.getWidth(); x += distance(0, sc.getWidth())) {
            drawXGrid(ld, g2d, COEFCUT, x);
        }

        // Draws a grid to the left of the coordinate line
        for (double x = 0; x > sc.getX() - sc.getWidth(); x -= distance(0, sc.getWidth())) {
            drawXGrid(ld, g2d, COEFCUT, x);

        }

        // Draws a grid to the up of the coordinate line
        for (double y = 0; y < sc.getY() + sc.getHeight(); y += distance(0, sc.getHeight())) {
            drawYGrid(ld, g2d, COEFCUT, y);
        }

        // Draws a grid to the down of the coordinate line
        for (double y = 0; y > sc.getY() - sc.getHeight(); y -= distance(0, sc.getHeight())) {
            drawYGrid(ld, g2d, COEFCUT, y);
        }

        // ox oy
        drawOneLine(ld, new Line(new RealPoint(0, sc.getY() - sc.getHeight()), new RealPoint(0, sc.getY())), Color.DARK_GRAY);

        drawOneLine(ld, new Line(new RealPoint(sc.getX() + sc.getWidth(), 0), new RealPoint(sc.getX(), 0)), Color.DARK_GRAY);

    }

    private void drawYGrid(LineDrawer ld, Graphics2D g2d, int ax, double y) {
        drawOneLine(ld, new Line(new RealPoint(-sc.getWidth() / ax, y), new RealPoint(sc.getWidth() / ax, y)), Color.BLACK);
        ScreenPoint point = sc.r2s(new RealPoint(-sc.getWidth() / ax, y));
        g2d.drawString(decimalFormat.format(y), point.getX() + 20, point.getY() - 8);
    }

    private void drawXGrid(LineDrawer ld, Graphics2D g2d, int ax, double x) {
        drawOneLine(ld, new Line(new RealPoint(x, -sc.getHeight() / ax), new RealPoint(x, sc.getHeight() / ax)), Color.BLACK);
        ScreenPoint point = sc.r2s(new RealPoint(x, -sc.getHeight() / ax));
        g2d.drawString(decimalFormat.format(x), point.getX() - 8, point.getY() + 20);
    }


    private double distance(double a1, double a2) {
        double result = 1;// единичный отрезок


        while (Math.abs(a2 - a1) / result < 1.0)
            result /= 10.0;
        while (Math.abs(a2 - a1) / result >= 20.0)
            result *= 10.0;

        if (Math.abs(a2 - a1) / result < 2.0)
            result /= 5.0;
        if (Math.abs(a2 - a1) / result < 4.0)
            result /= 2.0;

        return result;
    }

    private void drawOneLine(LineDrawer ld, Line l, Color c) {
        ScreenPoint p1 = sc.r2s(l.getP1());
        ScreenPoint p2 = sc.r2s(l.getP2());
        ld.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private Font getFont(int size) {
        if (font == null || font.getSize() != size) {
            font = new Font("Times New Roman", Font.PLAIN, size);
        }
        return font;
    }

}
