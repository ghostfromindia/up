
package science.ghost.www.fantasy_news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mid")
    @Expose
    private Integer mid;
    @SerializedName("player")
    @Expose
    private String player;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("captain")
    @Expose
    private Integer captain;
    @SerializedName("vicecaptain")
    @Expose
    private Integer vicecaptain;
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

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getCaptain() {
        return captain;
    }

    public void setCaptain(Integer captain) {
        this.captain = captain;
    }

    public Integer getVicecaptain() {
        return vicecaptain;
    }

    public void setVicecaptain(Integer vicecaptain) {
        this.vicecaptain = vicecaptain;
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
