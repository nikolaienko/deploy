package ua.nure.logic;

import ua.nure.model.Coord;

/**
 * Created by vlad on 12/22/14.
 */
public class NativeDistanceCalculator implements DistanceCalculator {
    @Override
    public Double calculate(Coord from, Coord to) {
        int R = 6371; // Radius of the earth in km
        Double dLat = deg2rad(to.getLat()-from.getLat());  // deg2rad below
        Double dLon = deg2rad(to.getLng()-from.getLng());
        Double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(from.getLat())) * Math.cos(deg2rad(to.getLat())) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double d = R * c; // Distance in km
        return d;
    }
    private Double deg2rad(Double deg) {
        return deg * (Math.PI/180);
    }
}
