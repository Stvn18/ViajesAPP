package gt.umg.viajes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import gt.umg.viajes.bd.ConfigurationDb;
import gt.umg.viajes.common.Common;
import gt.umg.viajes.common.Utils;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView username;
    private TextView userEmail;

    private ConfigurationDb configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        configuration = new ConfigurationDb(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeader = navigationView.inflateHeaderView(R.layout.nav_header_menu);

        username = (TextView) navHeader.findViewById(R.id.nav_username);
        userEmail = (TextView) navHeader.findViewById(R.id.nav_user_email);

        username.setText(Common.getSession().getName());
        userEmail.setText(Common.getSession().getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try {

            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if(id == R.id.nav_vuelo){
                Intent intent = new Intent(MenuActivity.this, VueloActivity.class);
                startActivity(intent);
            }

            if(id == R.id.nav_barco){
                Intent intent = new Intent(MenuActivity.this, CruceroActivity.class);
                startActivity(intent);
            }

            if(id == R.id.nav_hotel){
                Intent intent = new Intent(MenuActivity.this, HotelActivity.class);
                startActivity(intent);
            }

            if(id == R.id.nav_carro){
                Intent intent = new Intent(MenuActivity.this, CarroActivity.class);
                startActivity(intent);
            }

            if(id == R.id.nav_logout){
                configuration.deleteSession(Common.getSession().getToken());
                Common.setSession(null);

                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);

                startActivity(intent);

                MenuActivity.this.finish();
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

        } catch (Exception exception) {
            Utils.showCustomMessage(1, exception.getMessage(), MenuActivity.this);
        }
        return true;
    }
}
