package com.nentu.lab3.lab3.start.utils;

import java.util.ArrayList;
import java.util.List;

public class ValidationDataStorage {

    public float getYMax(){
        return 5;
    }
    public float getYMin(){
        return -3;
    }
    public List<Integer> getXValues() {
        var res = new ArrayList<Integer>();
        for (int i = -3; i <= 5; i++) {
            res.add(i);
        }
        return res;
    }


    public List<Float> getRValues() {
        var res = new ArrayList<Float>();
        for (float i = 1; i <= 3; i += 0.5) {
            res.add(i);
        }
        return res;
    }
}
