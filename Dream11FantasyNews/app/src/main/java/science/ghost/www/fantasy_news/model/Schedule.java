package science.ghost.www.fantasy_news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedule {

    @SerializedName("schedule")
    @Expose
    private List<Schedule_> schedule = null;

    public List<Schedule_> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule_> schedule) {
        this.schedule = schedule;
    }

}