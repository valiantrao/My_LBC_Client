package valiant.mylbcclient;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.navigation.NavigationView;

import valiant.mylbcclient.fragent.AddFunctionsFragment;
import valiant.mylbcclient.fragent.FunctionsFragment;
import valiant.mylbcclient.fragent.HomeFragment;
import valiant.mylbcclient.utils.SessionManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = this.getClass().getSimpleName();
    private final int HOME_FRAGMENT_ID = 1;
    private final int FUNCTIONS_FRAGMENT_ID = 2;
    private final int ADD_FUNCTION_FRAGMENT_ID = 3;
    //private AVLoadingIndicatorView avi;
    //private Python python;
    //private Button run_main_btn, run_background_btn;
    private SessionManager sessionManager;
    private DrawerLayout drawer;
    public static Python python;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sessionManager = new SessionManager(this);


        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        python = Python.getInstance();

        if (sessionManager.getLbcKey() == null){
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changeFragment(HOME_FRAGMENT_ID);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (getSupportFragmentManager().getBackStackEntryCount() > 0){
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_fragment);
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            changeFragment(HOME_FRAGMENT_ID);
        } else if (id == R.id.add_functions) {
            changeFragment(FUNCTIONS_FRAGMENT_ID);
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(int id){
        Fragment fragment = getFragmentById(id);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    private void changeFragmentWithBackStack(int id){
        Fragment fragment = getFragmentById(id);


        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment, "fragment")
                .addToBackStack("fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    private int getFragmentId(Fragment fragment) {

        String fragmentName = fragment.getClass().getName();

        if (fragmentName.equals(HomeFragment.class.getName())) {
            return HOME_FRAGMENT_ID;
        } else {
            return 100;
        }
    }

        private Fragment getFragmentById(int id) {

            switch (id){
                case HOME_FRAGMENT_ID:

                    return new HomeFragment();

                case FUNCTIONS_FRAGMENT_ID:

                    return new FunctionsFragment();

                default:
                    return new HomeFragment();
            }
        }
}