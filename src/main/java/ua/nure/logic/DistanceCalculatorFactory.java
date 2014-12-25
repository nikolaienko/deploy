package ua.nure.logic;

import ua.nure.logic.DistanceCalculator;
import ua.nure.logic.GoogleDistanceCalculator;
import ua.nure.logic.NativeDistanceCalculator;

/**
 * Created by vlad on 12/22/14.
 */
public final class DistanceCalculatorFactory {

    private DistanceCalculatorFactory() {
    }

    public static final DistanceCalculatorFactory getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public final DistanceCalculator getDistanceCalculator(String impl){
        switch (impl){
            case "Google": return new GoogleDistanceCalculator();

            case "Native": return new NativeDistanceCalculator();

            default: return new NativeDistanceCalculator();

        }
    }

    private static class InstanceHolder{
        private static final DistanceCalculatorFactory INSTANCE = new DistanceCalculatorFactory();
    }
}
