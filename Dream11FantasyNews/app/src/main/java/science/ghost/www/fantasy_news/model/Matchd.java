
package science.ghost.www.fantasy_news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Matchd {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("series")
    @Expose
    private String series;
    @SerializedName("home")
    @Expose
    private String home;
    @SerializedName("away")
    @Expose
    private String away;
    @SerializedName("himg")
    @Expose
    private String himg;
    @SerializedName("dimg")
    @Expose
    private String dimg;
    @SerializedName("aimg")
    @Expose
    private String aimg;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }


    public String getDimg() {
        return dimg;
    }

    public void setDimg(String away) {
        this.dimg = dimg;
    }

    public String getHimg() {
        return himg;
    }

    public void setHimg(String himg) {
        this.himg = himg;
    }

    public String getAimg() {
        return aimg;
    }

    public void setAimg(String aimg) {
        this.aimg = aimg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
