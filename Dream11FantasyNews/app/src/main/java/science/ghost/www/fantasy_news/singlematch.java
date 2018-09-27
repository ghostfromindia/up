package science.ghost.www.fantasy_news;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import science.ghost.www.fantasy_news.fragmentmatch.matchdetails;
import science.ghost.www.fantasy_news.fragmentmatch.news;
import science.ghost.www.fantasy_news.fragmentmatch.players;

public class singlematch extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private AdView mAdView;
    private Intent i;
    private Bundle bundle;
    Integer type;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlematch);

//        MobileAds.initialize(this,
//                "ca-app-pub-5595711823084513~5983012129");
        MobileAds.initialize(this,
                "ca-app-pub-5595711823084513~5983012129");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        i = getIntent();
        bundle = new Bundle();
        Log.i("id", ""+i.getExtras().getInt("id"));
        bundle.putInt("mid", i.getExtras().getInt("id"));
        Log.i("AAAAAAAAAAAA",""+bundle.getInt("mid"));
        type = i.getExtras().getInt("type");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5595711823084513/5051095449");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Match data");
        toolbar.setTitleTextColor(Color.BLACK);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        if(type>2 || type <0 || type ==null){
            type = 0;
        }
        TabLayout.Tab tab = tabLayout.getTabAt(type);
        tab.select();

        mInterstitialAd.setAdListener(new AdListener() {


            @Override
            public void onAdClosed() {
                TabLayout.Tab tab = tabLayout.getTabAt(1);
                tab.select();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_singlematch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }




    public class SectionsPagerAdapter extends FragmentPagerAdapter {



        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: players t1 = new players();t1.setArguments(bundle);return t1;
                case 1: news t2 = new news();t2.setArguments(bundle);
                        return t2;
                case 2: if (mInterstitialAd.isLoaded()) { mInterstitialAd.show();
                           matchdetails t3 = new matchdetails();t3.setArguments(bundle);
                           return t3;} else {
                          matchdetails t3 = new matchdetails();t3.setArguments(bundle);
                          return t3;
                }
                default: return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
