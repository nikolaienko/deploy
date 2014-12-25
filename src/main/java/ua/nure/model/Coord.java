package ua.nure.model;

/**
 * Created by vlad on 12/25/14.
 */
public class Coord {
    public Coord(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {

        return lat;
    }

    public Double getLng() {
        return lng;
    }

    private Double lat;
    private Double lng;
}
