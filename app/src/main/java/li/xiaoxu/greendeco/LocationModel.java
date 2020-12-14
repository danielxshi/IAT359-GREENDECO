package li.xiaoxu.greendeco;

public class LocationModel {
    private int id;
    private double latLoc;
    private double lngLoc;
    private String topology;

    //constructor
    public LocationModel(int id, double latLoc, double lngLoc, String topology){
        this.id = id;
        this.latLoc = latLoc;
        this.lngLoc = lngLoc;
        this.topology = topology;
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

    public String getTopology() {
        return topology;
    }

    public void setTopology(String topology) {
        this.topology = topology;
    }

}
