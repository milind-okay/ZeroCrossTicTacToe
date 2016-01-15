package com.wordpress.milindkrohit.zerocrosstictactoe;

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

public class MActivity extends AppCompatActivity implements layout.Home.OnFragmentInteractionListener,layout.Mplayground.OnFragmentInteractionListener,mfragment{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_selector(1);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
        if(fragmnet_id == 1){
            new_fragment = new layout.Home();
        }else{
            new_fragment = new layout.Mplayground();
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= manager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_home, new_fragment, "okay");
        fragmentTransaction.commit();
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
