package com.wordpress.milindkrohit.zerocrosstictactoe;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.purplebrain.adbuddiz.sdk.AdBuddiz;
import com.purplebrain.adbuddiz.sdk.AdBuddizLogLevel;

public class MActivity extends AppCompatActivity implements layout.Home.OnFragmentInteractionListener,layout.Mplayground.OnFragmentInteractionListener,mfragment,
        layout.aboutus.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AdBuddiz.setPublisherKey("TEST_PUBLISHER_KEY");
        AdBuddiz.setLogLevel(AdBuddizLogLevel.Info);
        AdBuddiz.setPublisherKey("5daa68f5-3596-4893-8f20-5a11b054fb2b");
        AdBuddiz.cacheAds(this);
        AdBuddiz.RewardedVideo.fetch(this);
        ImageButton fab = (ImageButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabonclick();
            }
        });
        fragment_home();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_m, menu);
        return true;
    }
    public void fabonclick(){
        if (AdBuddiz.RewardedVideo.isReadyToShow(this)) { // this = current Activity
            AdBuddiz.RewardedVideo.show(this);
        }

        fragment_selector(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            fragment_selector(3);
            return true;
        }else if(id == R.id.help){
            showHelp();
            return true;
        }else if(id  == R.id.rate_us){
            rate_us();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void fragment_home(){
        Fragment new_fragment = new layout.Home();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= manager.beginTransaction();
        fragmentTransaction.add(R.id.layout_home, new_fragment, "okay");
        fragmentTransaction.commit();
    }
    @Override
    public void fragment_selector(int fragmnet_id){
        Fragment new_fragment;
        switch (fragmnet_id){
            case 1:
                new_fragment = new layout.Home();
                break;
            case 2:
                AdBuddiz.showAd(this);
                new_fragment = new layout.Mplayground();

                break;
            case 3:
                new_fragment = new layout.aboutus();
                break;
            default:
                new_fragment = new layout.Home();
        }


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= manager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_home, new_fragment, "okay");
        fragmentTransaction.commit();
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private void showHelp() {
        String str = "https://www.milindkrohit.wordpress.com/zerocross";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    private void rate_us() {
        String str = "https://play.google.com/store/apps/details?id=com.wordpress.milindkrohit.zerocrosstictactoe\"";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }


}
