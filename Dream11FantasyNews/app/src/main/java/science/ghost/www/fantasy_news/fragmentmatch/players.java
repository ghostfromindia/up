package science.ghost.www.fantasy_news.fragmentmatch;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import science.ghost.www.fantasy_news.R;
import science.ghost.www.fantasy_news.api.ApiClient;
import science.ghost.www.fantasy_news.api.ApiInterface;
import science.ghost.www.fantasy_news.model.Matchd;
import science.ghost.www.fantasy_news.model.Player;
import science.ghost.www.fantasy_news.model.Player_;

public class players extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeLayout;
    ApiInterface apiInterface;
    private Bundle bundle;
    int myInt;
    ImageView img;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_players,container,false);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        img = (ImageView) view.findViewById(R.id.teamimg);



        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

        bundle = this.getArguments();
        if (bundle != null) {
            myInt = bundle.getInt("mid", 1);
        }else{
            myInt = 2;
            Log.i("Bundle null","Hai");
        }
        Call<Player> callmenu = apiInterface.getPlayersData(myInt);
        Log.i("Bundle null",""+myInt);
        callmenu.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.body() != null) {

                    Matchd match = response.body().getMatchd();
                    Log.i("IMG",""+match.getDimg());
                    Picasso.get().load(match.getDimg()).placeholder(R.drawable.progress_animation).into(img);

                }else{
                    Toast.makeText(getContext(), "No data recieved",
                            Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onRefresh() {
        Log.i("Fragment" , "refreshing");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

}
