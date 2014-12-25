package ua.nure.controller;

/**
 * Created by vlad on 12/25/14.
 */
import ua.nure.dao.DBConnection;
import ua.nure.logic.DistanceCalculator;
import ua.nure.logic.DistanceCalculatorFactory;
import ua.nure.logic.Utitlity;
import ua.nure.model.Accident;
import ua.nure.model.Coord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//Path: http://localhost/<appln-folder-name>/login
@Path("/notify")
public class Notify {
    private static final Double RANGE = 1d;

    @POST
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/create")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    public String doNotify(@QueryParam("username") String uname, @QueryParam("lat") String lat, @QueryParam("lng") String lng) {
        String response = "";
        if (checkCredentials(uname,lat,lng)) {
            Double latD = Double.parseDouble(lat);
            Double lngD = Double.parseDouble(lng);
            if (createNotify(uname, latD, lngD)) {
                response = Utitlity.constructJSON("status", true);
            }
        }
        if (response != "")
            response = Utitlity.constructJSON("status", false);
        return null;
    }

    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/get")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    public String doGet(@QueryParam("username") String uname, @QueryParam("lat") String lat, @QueryParam("lng") String lng) {
        String response = "";
        if (checkCredentials(uname,lat,lng)) {
            Double latD = Double.parseDouble(lat);
            Double lngD = Double.parseDouble(lng);
            List<Accident> list = getNotifications(uname, latD, lngD);
            if (list!=null&&list.size()!=0) {
                response = Utitlity.constructJSON("status", true,"list",list);
            }
        }
        if (response != "")
            response = Utitlity.constructJSON("status", false);
        return null;
    }

    private List<Accident> getNotifications(String uname, Double latD, Double lngD) {
        List<Accident> accidents = null;
        try {
            accidents = DBConnection.findAllAccidents();
            DistanceCalculator distanceCalculator = DistanceCalculatorFactory.getInstance().getDistanceCalculator("Native");
            accidents.stream()
                    .filter(a -> distanceCalculator.calculate(new Coord(latD, lngD), new Coord(a.getLat(), a.getLng())) < RANGE)
                    .collect(Collectors.toList());

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException n){
            n.printStackTrace();
        }
        return accidents;
    }


    private boolean createNotify(String uname, Double lat, Double lng) {
        try {
            return DBConnection.createNotify(uname,lat,lng);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkCredentials(String uname, String lat, String lng){
        boolean f = false;
        if (Utitlity.isNotNull(uname) && Utitlity.isNotNull(lat)&&Utitlity.isNotNull(lng)){
            try {
                double latD = Double.parseDouble(lat);
                double lngD = Double.parseDouble(lng);
                f = true;
            }catch (NumberFormatException e){

            }
        }
        return f;
    }
}
