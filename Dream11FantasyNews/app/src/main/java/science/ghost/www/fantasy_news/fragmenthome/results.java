package science.ghost.www.fantasy_news.fragmenthome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import science.ghost.www.fantasy_news.R;
import science.ghost.www.fantasy_news.adapter.schedule;
import science.ghost.www.fantasy_news.api.ApiClient;
import science.ghost.www.fantasy_news.api.ApiInterface;
import science.ghost.www.fantasy_news.model.Schedule;
import science.ghost.www.fantasy_news.model.Schedule_;


public class results extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    SwipeRefreshLayout swipeLayout;
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    TextView loading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_results,container,false);
        loading = (TextView) view.findViewById(R.id.loading);
        recyclerView = (RecyclerView) view.findViewById(R.id.liveSchedule);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        play();
        return view;
    }

    public void play(){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        final Call<Schedule> schedule = apiInterface.getScheduleResults();
        schedule.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if (response.body() != null) {
                    List<Schedule_> live = (List<Schedule_>) response.body().getSchedule();
                    loading.setVisibility(View.GONE);
                    recyclerView.setAdapter(new schedule((List<Schedule_>) live,R.layout.list_schedule,getContext()));

                }

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                Log.e("ERROR",""+t.toString());
                loading.setVisibility(View.VISIBLE);
                loading.setText("Database connection error. Try later");
            }
        });
        // Inflate the layout for this fragment
    }

    @Override
    public void onRefresh() {
        Log.i("Fragment" , "refreshing");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

}
