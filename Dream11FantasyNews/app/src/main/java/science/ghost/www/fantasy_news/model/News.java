
package science.ghost.www.fantasy_news.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("news")
    @Expose
    private List<News_> news = null;

    public List<News_> getNews() {
        return news;
    }

    public void setNews(List<News_> news) {
        this.news = news;
    }

}
