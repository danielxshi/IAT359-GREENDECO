package li.xiaoxu.greendeco;

public class LocationModel {
    private int id;
    private double latLoc;
    private double lngLoc;

    //constructor
    public LocationModel(int id, double latLoc, double lngLoc){
        this.id = id;
        this.latLoc = latLoc;
        this.lngLoc = lngLoc;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatLoc() {
        return latLoc;
    }

    public void setLatLoc(double latLoc) {
        this.latLoc = latLoc;
    }

    public double getLngLoc() {
        return lngLoc;
    }

    public void setLngLoc(double lngLoc) {
        this.lngLoc = lngLoc;
    }
}
