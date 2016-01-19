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
import android.util.Log;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;
import com.purplebrain.adbuddiz.sdk.AdBuddizLogLevel;

public class MActivity extends AppCompatActivity implements layout.Home.OnFragmentInteractionListener,layout.Mplayground.OnFragmentInteractionListener,mfragment,
        layout.aboutus.OnFragmentInteractionListener{
    private AdView mAdView;
    int fragment_id = 1,backPress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        AdBuddiz.setPublisherKey("TEST_PUBLISHER_KEY");
        AdBuddiz.setLogLevel(AdBuddizLogLevel.Info);
        AdBuddiz.setPublisherKey("5daa68f5-3596-4893-8f20-5a11b054fb2b");
        AdBuddiz.cacheAds(this);
       // AdBuddiz.RewardedVideo.fetch(this);
        ImageButton fab = (ImageButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_selector(1);
            }
        });
        if(savedInstanceState == null)  fragment_home();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_m, menu);
        return true;
    }
   /* public void fabonclick(){
        if (AdBuddiz.RewardedVideo.isReadyToShow(this)) { // this = current Activity
            AdBuddiz.RewardedVideo.show(this);
        }

        fragment_selector(1);
    }*/

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
        }else if(id == R.id.statistics){
            fragment_selector(4);
            return true;
        }else if(id == R.id.action_contactus){
            sendEmail();
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
    public void fragment_selector(int fragment){
        Fragment new_fragment;
        fragment_id = fragment;
        switch (fragment){
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
            case 4:
                new_fragment = new layout.ListScore();
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fragment_id", fragment_id);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private void showHelp() {
        String str = "https://www.milindkrohit.wordpress.com/zerocross";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    private void rate_us() {
        String str = "https://play.google.com/store/apps/details?id=com.wordpress.milindkrohit.zerocrosstictactoe";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    @Override
    public void onBackPressed() {

        if(fragment_id == 1){
            backPress++;
            if(backPress == 1)  Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();
            else super.onBackPressed();
        }else{
            fragment_selector(1);
        }
    }

    private void sendEmail(){

        String info = "okay ",emailAdd;
        emailAdd = "milind0359@gmail.com";
        Log.i("Send email", "");
        String emailaddress[] = {emailAdd};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("plane/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,emailaddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SudokuSolverAdvanced : state your subject here");
        emailIntent.putExtra(Intent.EXTRA_TEXT, info);


        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            Log.i("Finished sending email", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();

    }
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
