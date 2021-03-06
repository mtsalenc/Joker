package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.jokedisplayer.DisplayActivity;



public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.EndpointCallback{

    Context context;
    Button btnTellJoke;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        btnTellJoke = (Button) findViewById(R.id.btnTellJoke);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view) {
        btnTellJoke.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

        new EndpointsAsyncTask(this).execute();
    }


    @Override
    public void callback(String joke) {
        btnTellJoke.setEnabled(true);
        progressBar.setVisibility(View.GONE);

        Intent i = new Intent(this, DisplayActivity.class);
        i.putExtra(DisplayActivity.JOKE_EXTRA, joke);
        startActivity(i);
    }
}
