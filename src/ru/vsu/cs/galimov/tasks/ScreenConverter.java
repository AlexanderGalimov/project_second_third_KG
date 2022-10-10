package ru.vsu.cs.galimov.tasks;

public class ScreenConverter {
    private int sWidth, sHeight;
    private double x, y, width, height;

    public ScreenConverter(int sWidth, int sHeight, double x, double y, double width, double height) {
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public ScreenPoint r2s(RealPoint p){
        int x = (int)(sWidth * (p.getX() - this.x) / width);
        int y = (int)(sHeight * (this.y - p.getY()) / height);
        return new ScreenPoint(x,y);
    }

    public RealPoint s2r(ScreenPoint p){
        double x = this.x + p.getX() * width / sWidth;
        double y = this.y - p.getY() * height / sHeight;
        return new RealPoint(x,y);
    }

    public int getsWidth() {
        return sWidth;
    }

    public void setsWidth(int sWidth) {
        this.sWidth = sWidth;
    }

    public int getsHeight() {
        return sHeight;
    }

    public void setsHeight(int sHeight) {
        this.sHeight = sHeight;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
