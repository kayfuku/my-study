//**************************************************************
// Author    : Kei Fukutani
// File Name : Circle.java
// Date      : 3/24/14
// Objective : This file contains the information of a circle to
//             be drawn.
//**************************************************************

package com.example.graphicsdemo;

public class Circle
{
    private int x, y, r, col;

    // x accessor.
    public int getX()
    {
        return x;
    }

    // y accessor.
    public int getY()
    {
        return y;
    }

    // r accessor.
    public int getR()
    {
        return r;
    }

    // col accessor.
    public int getColor()
    {
        return col;
    }

    // x mutator.
    public void setX(int x)
    {
        this.x = x;
    }

    // y mutator.
    public void setY(int y)
    {
        this.y = y;
    }

    // r mutator.
    public void setR(int r)
    {
        this.r = r;
    }

    // col mutator.
    public void setColor(int col)
    {
        this.col = col;
    }
}
