package com.claire.serviceImpl;

import com.claire.service.EvaluationService;
import com.claire.util.SilhouetteCoefficient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by claire on 4/18/16.
 */
public class EvaluationServiceImpl implements EvaluationService {

    @Override
    public Double clusteringEvaluation() {
        SilhouetteCoefficient sc = new SilhouetteCoefficient();
        try {
            return sc.calcFromFile("result.txt", 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }



}
