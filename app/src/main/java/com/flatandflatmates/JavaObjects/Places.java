package com.flatandflatmates.JavaObjects;

/**
 * Created by applect on 15/3/15.
 */

import java.util.ArrayList;
import java.util.List;

public class Places {

    private List<Prediction> predictions = new ArrayList<Prediction>();

    /**
     *
     * @return
     * The predictions
     */
    public List<Prediction> getPredictions() {
        return predictions;
    }

    /**
     *
     * @param predictions
     * The predictions
     */
    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }
}