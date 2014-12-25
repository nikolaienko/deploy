package ua.nure.logic;

import ua.nure.model.Coord;

/**
 * Created by vlad on 12/22/14.
 */
public interface DistanceCalculator {
    Double calculate(Coord from, Coord to);
}
