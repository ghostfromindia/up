package science.ghost.www.fantasy_news.api;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import science.ghost.www.fantasy_news.model.News;
import science.ghost.www.fantasy_news.model.Player;
import science.ghost.www.fantasy_news.model.Schedule;

/**
 * Created by Development_PC on 4/2/2018.
 */

public interface ApiInterface {
    @GET("/fantasy/api/schedule/daily")
    Call<Schedule> getScheduleDaily();

    @GET("/fantasy/api/schedule/upcoming")
    Call<Schedule> getScheduleUpcoming();

    @GET("/fantasy/api/schedule/results")
    Call<Schedule> getScheduleResults();

    @GET("/fantasy/api/players/{id}")
    Call<Player> getPlayersData(@Path("id") int id);

    @GET("/fantasy/api/{type}/{id}")
    Call<News> getNews(@Path("type") String type,@Path("id") int id);
}


