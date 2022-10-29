package ru.vsu.cs.galimov.tasks;

public class CandleStick {
    private RealPoint enterValue;
    private RealPoint outValue;
    private RealPoint maxValue;
    private RealPoint minValue;

    public CandleStick(RealPoint enterValue, RealPoint outValue, RealPoint maxValue, RealPoint minValue) {
        this.enterValue = enterValue;
        this.outValue = outValue;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public RealPoint getEnterValue() {
        return enterValue;
    }

    public void setEnterValue(RealPoint enterValue) {
        this.enterValue = enterValue;
    }

    public RealPoint getOutValue() {
        return outValue;
    }

    public void setOutValue(RealPoint outValue) {
        this.outValue = outValue;
    }

    public RealPoint getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(RealPoint maxValue) {
        this.maxValue = maxValue;
    }

    public RealPoint getMinValue() {
        return minValue;
    }

    public void setMinValue(RealPoint minValue) {
        this.minValue = minValue;
    }
}
