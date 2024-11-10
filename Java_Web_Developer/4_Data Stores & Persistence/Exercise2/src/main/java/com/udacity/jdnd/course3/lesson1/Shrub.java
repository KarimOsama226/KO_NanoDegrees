package com.udacity.jdnd.course3.lesson1;

public class Shrub extends Plant{
private int width;
private int height;

    public Shrub() {
    }
    public Shrub(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
