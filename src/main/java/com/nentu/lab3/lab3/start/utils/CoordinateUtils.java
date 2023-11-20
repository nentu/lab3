package com.nentu.lab3.lab3.start.utils;

import com.nentu.lab3.lab3.start.main.CoordinateBean;

public class CoordinateUtils {
    public static boolean check(float x, float y, float r) {
        if (x >= 0) {
            if (y < 0)
                return false;
            return (x <= r / 2 && y <= r);
        } else {
            if (y >= 0) {
                return y <= x + r;
            }
            return x * x + y * y < r * r;
        }
    }

    public static boolean equals(CoordinateBean coord1, CoordinateBean coord2) {
        return (
                coord1.getX().equals(coord2.getX()) &&
                coord1.getY().equals(coord2.getY()) &&
                coord1.getR().equals(coord2.getR()) &&
                coord1.isSuccess() == coord2.isSuccess()
        );
    }
}
