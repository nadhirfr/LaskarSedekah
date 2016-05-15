package com.laskarsedekah.laskarsedekah;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.laskarsedekah.laskarsedekah.Adapter.CaraSedekah_LevelAdapter;
import com.laskarsedekah.laskarsedekah.Adapter.SimpleXmlRequest;
import com.laskarsedekah.laskarsedekah.NavMenu.About;
import com.laskarsedekah.laskarsedekah.NavMenu.CaraSedekah;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private CaraSedekah.SectionsPagerAdapter mSectionsPagerAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://www.w3schools.com/xml/note.xml";
        SimpleXmlRequest<Note> simpleRequest = new SimpleXmlRequest<Note>(Request.Method.GET, url, Note.class,
                new Response.Listener<Note>() {
                    @Override
                    public void onResponse(Note response) {
                        // response Object
                        TextView test = (TextView) findViewById(R.id.test);
                        assert test != null;
                        test.setText(response.getHeading());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error Object
                    }
                }
        );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*//untuk menginclude layout, parentlayout=drawer childlayout=childLayout
        LayoutInflater layoutInflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View childLayout = layoutInflater.inflate(R.layout.activity_tab,
                (ViewGroup) findViewById(R.id.main_content));
        drawer.addView(childLayout);*/


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
        final int id = item.getItemId();

        if (id == R.id.nav_cara_sedekah) {
            nav_cara_sedekah();

        } else if (id == R.id.nav_about) {

            Intent intent = new Intent(this, About.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void nav_cara_sedekah() {

        setTitle("Cara Sedekah");
        //menghapus konten utama lalu menampilkan tab dan container viewpager untuk content
        View tabs = findViewById(R.id.tabs);
        View viewPagerLayout = findViewById(R.id.container);
        tabs.setVisibility(View.VISIBLE);
        viewPagerLayout.setVisibility(View.VISIBLE);


        int ic_bank_mandiri = R.drawable.ic_bank_mandiri;
        int ic_bank_muamalat = R.drawable.ic_bank_muamalat;
        int ic_bank_bca = R.drawable.ic_bank_bca;
        int ic_bank_bni = R.drawable.ic_bank_bni;
        int ic_bank_bri = R.drawable.ic_bank_bri;
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new CaraSedekah.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //set content listview start
        final ListView lv1 = (ListView) findViewById(R.id.lv_bank);
        CaraSedekah.Level data[] = new CaraSedekah.Level[]
                {
                        new CaraSedekah.Level("Bank Mandiri", "No. Rek: 1370010082077", "A/N: Ma’ruf Fahrudin", ic_bank_mandiri),
                        new CaraSedekah.Level("Bank Muamalat", "No. Rek: 53 1001 2834", "A/N: Maruf Fahrudin", ic_bank_muamalat),
                        new CaraSedekah.Level("BCA", "No. Rek: 8020161516", "A/N: Maruf Fahrudin", ic_bank_bca),
                        new CaraSedekah.Level("BNI 46", "No. Rek: 1013031017", "A/N: Maruf Fahrudin", ic_bank_bni),
                        new CaraSedekah.Level("BRI", "No. Rek: 685801003682533", "A/N: Maruf Fahrudin", ic_bank_bri)
                };
        final CaraSedekah_LevelAdapter adp = new CaraSedekah_LevelAdapter(this, R.layout.list_bank, data);
        lv1.setAdapter(adp);

        //refresh scroll ke bawah
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        lv1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (lv1 != null && lv1.getChildCount() > 0) {
                    // check if the first item of the list is visible
                    boolean firstItemVisible = lv1.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = lv1.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                mSwipeRefreshLayout.setEnabled(enable);
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                //handling swipe refresh
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(MainActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
                        lv1.setAdapter(adp);
                    }
                }, 2000);
            }
        });

    }






}
