
package science.ghost.www.fantasy_news.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("players")
    @Expose
    private List<Player_> players = null;
    @SerializedName("matchd")
    @Expose
    private Matchd matchd;

    public List<Player_> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player_> players) {
        this.players = players;
    }

    public Matchd getMatchd() {
        return matchd;
    }

    public void setMatchd(Matchd matchd) {
        this.matchd = matchd;
    }

}
