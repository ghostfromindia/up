package science.ghost.www.fantasy_news.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import science.ghost.www.fantasy_news.R;
import science.ghost.www.fantasy_news.model.Schedule_;
import science.ghost.www.fantasy_news.singlematch;

public class schedule extends RecyclerView.Adapter<schedule.MovieViewHolder> {

    private List<Schedule_> schedule;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout playinLayout;
        LinearLayout cricket;
        LinearLayout football;
        LinearLayout main;

        TextView series;
        TextView title;
        TextView time;
        TextView venue;
        ImageView home;
        ImageView away;


        public MovieViewHolder(View v) {
            super(v);
            playinLayout = (LinearLayout) v.findViewById(R.id.scheduleLayout);
            main = (LinearLayout) v.findViewById(R.id.main);
            cricket = (LinearLayout) v.findViewById(R.id.cricket);
            football = (LinearLayout) v.findViewById(R.id.football);

            series = (TextView) v.findViewById(R.id.series);
            title = (TextView) v.findViewById(R.id.title);
            home = (ImageView) v.findViewById(R.id.homeimg);
            away = (ImageView) v.findViewById(R.id.awayimg);
            time =(TextView) v.findViewById(R.id.time);
            venue =(TextView) v.findViewById(R.id.venue);

        }
    }

    public schedule(List<Schedule_> schedule, int rowLayout, Context context) {
        this.schedule = schedule;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public schedule.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        String home = schedule.get(position).getHome();
        String away = schedule.get(position).getAway();
        String title = home + " vs " + away;

        holder.title.setText(title);
        holder.series.setText(schedule.get(position).getSeries());
        holder.time.setText(schedule.get(position).getDate());
        holder.venue.setText(schedule.get(position).getVenue());
        Picasso.get().load(schedule.get(position).getHimg()).into(holder.home);
        Picasso.get().load(schedule.get(position).getAimg()).into(holder.away);
        if(schedule.get(position).getType().equals("cricket")){
            holder.cricket.setVisibility(View.VISIBLE);
            holder.main.setBackgroundResource(R.drawable.cricketbg);
        }else{
            holder.football.setVisibility(View.VISIBLE);
            holder.main.setBackgroundResource(R.drawable.footballbg);
        }


        String date=schedule.get(position).getDate();
        SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd");
        Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("MMM, dd yyyy");
        date = spf.format(newDate);

        holder.time.setText(date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,singlematch.class);
                i.putExtra("id",schedule.get(position).getId());
                i.putExtra("type",0);
                context.startActivity(i);
                Log.i("positionEEEEE",""+schedule.get(position).getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

}
