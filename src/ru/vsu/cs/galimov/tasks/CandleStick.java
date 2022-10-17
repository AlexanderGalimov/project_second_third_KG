package ru.vsu.cs.galimov.tasks;

public class CandleStick {
    int enterValue;
    int outValue;
    int maxValue;
    int minValue;

    public CandleStick(int enterX, int outX, int max, int min) {
        this.enterValue = enterX;
        this.outValue = outX;
        this.maxValue = max;
        this.minValue = min;
    }

    public int getEnterValue() {
        return enterValue;
    }

    public void setEnterValue(int enterValue) {
        this.enterValue = enterValue;
    }

    public int getOutValue() {
        return outValue;
    }

    public void setOutValue(int outValue) {
        this.outValue = outValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
}
