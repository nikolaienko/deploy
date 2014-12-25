package ua.nure.logic;

import ua.nure.model.Coord;

/**
 * Created by vlad on 12/22/14.
 */
public class GoogleDistanceCalculator implements DistanceCalculator {

    private static final String GOOGLE_MAP_API = "http://maps.googleapis.com/maps/api/distancematrix/";

    @Override
    public Double calculate(Coord from, Coord to) {
        return null;
    }
}
