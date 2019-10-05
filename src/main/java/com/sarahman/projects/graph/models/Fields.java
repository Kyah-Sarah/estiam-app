package com.sarahman.projects.graph.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("nbfreeedock")
    @Expose
    private int nbfreeedock;
    @SerializedName("station_state")
    @Expose
    private String stationState;
    @SerializedName("maxbikeoverflow")
    @Expose
    private int maxbikeoverflow;
    @SerializedName("creditcard")
    @Expose
    private String creditcard;
    @SerializedName("station_type")
    @Expose
    private String stationType;
    @SerializedName("overflowactivation")
    @Expose
    private String overflowactivation;
    @SerializedName("station_code")
    @Expose
    private String stationCode;
    @SerializedName("overflow")
    @Expose
    private String overflow;
    @SerializedName("nbbikeoverflow")
    @Expose
    private int nbbikeoverflow;
    @SerializedName("duedate")
    @Expose
    private String duedate;
    @SerializedName("densitylevel")
    @Expose
    private String densitylevel;
    @SerializedName("nbedock")
    @Expose
    private int nbedock;
    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("nbfreedock")
    @Expose
    private int nbfreedock;
    @SerializedName("nbbike")
    @Expose
    private int nbbike;
    @SerializedName("station_name")
    @Expose
    private String stationName;
    @SerializedName("nbdock")
    @Expose
    private int nbdock;
    @SerializedName("geo")
    @Expose
    private List<Double> geo = null;
    @SerializedName("nbebike")
    @Expose
    private int nbebike;
    @SerializedName("kioskstate")
    @Expose
    private String kioskstate;
    @SerializedName("nbebikeoverflow")
    @Expose
    private int nbebikeoverflow;

    public int getNbfreeedock() {
        return nbfreeedock;
    }

    public void setNbfreeedock(int nbfreeedock) {
        this.nbfreeedock = nbfreeedock;
    }

    public String getStationState() {
        return stationState;
    }

    public void setStationState(String stationState) {
        this.stationState = stationState;
    }

    public int getMaxbikeoverflow() {
        return maxbikeoverflow;
    }

    public void setMaxbikeoverflow(int maxbikeoverflow) {
        this.maxbikeoverflow = maxbikeoverflow;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public String getOverflowactivation() {
        return overflowactivation;
    }

    public void setOverflowactivation(String overflowactivation) {
        this.overflowactivation = overflowactivation;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getOverflow() {
        return overflow;
    }

    public void setOverflow(String overflow) {
        this.overflow = overflow;
    }

    public int getNbbikeoverflow() {
        return nbbikeoverflow;
    }

    public void setNbbikeoverflow(int nbbikeoverflow) {
        this.nbbikeoverflow = nbbikeoverflow;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getDensitylevel() {
        return densitylevel;
    }

    public void setDensitylevel(String densitylevel) {
        this.densitylevel = densitylevel;
    }

    public int getNbedock() {
        return nbedock;
    }

    public void setNbedock(int nbedock) {
        this.nbedock = nbedock;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getNbfreedock() {
        return nbfreedock;
    }

    public void setNbfreedock(int nbfreedock) {
        this.nbfreedock = nbfreedock;
    }

    public int getNbbike() {
        return nbbike;
    }

    public void setNbbike(int nbbike) {
        this.nbbike = nbbike;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getNbdock() {
        return nbdock;
    }

    public void setNbdock(int nbdock) {
        this.nbdock = nbdock;
    }

    public List<Double> getGeo() {
        return geo;
    }

    public void setGeo(List<Double> geo) {
        this.geo = geo;
    }

    public int getNbebike() {
        return nbebike;
    }

    public void setNbebike(int nbebike) {
        this.nbebike = nbebike;
    }

    public String getKioskstate() {
        return kioskstate;
    }

    public void setKioskstate(String kioskstate) {
        this.kioskstate = kioskstate;
    }

    public int getNbebikeoverflow() {
        return nbebikeoverflow;
    }

    public void setNbebikeoverflow(int nbebikeoverflow) {
        this.nbebikeoverflow = nbebikeoverflow;
    }

}
