package ua.nure.model;

/**
 * Created by vlad on 12/25/14.
 */
public class Accident {
	private int id;
    private String userName;
    private Double lat;
    private Double lng;

    public Accident(int id,String userName, Double lat, Double lng) {
        this.id = id;
        this.userName = userName;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
