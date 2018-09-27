package science.ghost.www.fantasy_news.fragmentmatch;

import android.content.Context;
import android.net.Uri;
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
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import science.ghost.www.fantasy_news.R;
import science.ghost.www.fantasy_news.adapter.newsAdapter;
import science.ghost.www.fantasy_news.api.ApiClient;
import science.ghost.www.fantasy_news.api.ApiInterface;
import science.ghost.www.fantasy_news.model.News;
import science.ghost.www.fantasy_news.model.News_;


public class matchdetails extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeLayout;
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    private Bundle bundle;
    int myInt;
    TextView load;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_matchdetails,container,false);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        load = (TextView) view.findViewById(R.id.loading);


        recyclerView = (RecyclerView) view.findViewById(R.id.newsview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

        bundle = this.getArguments();
        if (bundle != null) {
            myInt = bundle.getInt("mid", 1);
        }else{
            myInt = 2;
            Log.i("Bundle null","Hai");
        }
  Log.i("Bundle value",""+myInt);
        Call<News> newsdata = apiInterface.getNews("fact",myInt);
        newsdata.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.body() != null) {
                    List<News_> news = (List<News_>) response.body().getNews();
                    load.setVisibility(View.GONE);
                    recyclerView.setAdapter(new newsAdapter((List<News_>) news,R.layout.list_news,getContext()));
                }else{
                    Toast.makeText(getContext(), "No data recieved",
                            Toast.LENGTH_LONG).show();
                    load.setVisibility(View.VISIBLE);
                    load.setText("Data not received, Try refreshing");
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onRefresh() {
        Log.i("Fragment" , "refreshing");
        load.setVisibility(View.VISIBLE);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}
