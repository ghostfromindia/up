package science.ghost.www.fantasy_news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import science.ghost.www.fantasy_news.R;
import science.ghost.www.fantasy_news.model.News_;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.MovieViewHolder> {

    private List<News_> players;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout playinLayout;
        TextView content;
        TextView title;


        public MovieViewHolder(View v) {
            super(v);
            playinLayout = (LinearLayout) v.findViewById(R.id.newsHome);
            title = (TextView) v.findViewById(R.id.head);
            content = (TextView) v.findViewById(R.id.content);
        }
    }

    public newsAdapter(List<News_> players, int rowLayout, Context context) {
        this.players = players;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public newsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {



        holder.title.setText(players.get(position).getTitle());
        holder.content.setText(players.get(position).getDetails());

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

}
