package com.vietinterview.getbee.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.callback.OnFillBackgroundListener;
import com.vietinterview.getbee.fragments.FirstFragment;
import com.vietinterview.getbee.fragments.RegitsFragment;
import com.vietinterview.getbee.utils.FragmentUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvAppName)
    TextView tvAppName;
    @BindView(R.id.nav_view)
    public NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;

    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(null);
        getEventBaseActivity().setOnFillBackgroundListener(new OnFillBackgroundListener() {
            @Override
            public void onFillBackground(String nameTable) {
                tvAppName.setText(nameTable);
            }
        });
        if (AccountManager.getUserInfoBean() != null) {
            FragmentUtil.replaceFragment(this, new FirstFragment().newInstance("FirstFragment"), null);
        } else {
            FragmentUtil.replaceFragment(MainActivity.this, new RegitsFragment(), null);
        }
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            FragmentUtil.replaceFragment(this, new FirstFragment().newInstance("FirstFragment"), null);
        } else if (id == R.id.nav_gallery) {
//            FragmentUtil.replaceFragment(this, new ThirdFragment().newInstance("ThirdFragment"), null);
        } else if (id == R.id.nav_slideshow) {
//            DebugLog.showLogCat("nav_slideshow");
        } else if (id == R.id.nav_manage) {
//            DebugLog.showLogCat("nav_manage");
        } else if (id == R.id.nav_share) {
//            DebugLog.showLogCat("nav_share");
        } else if (id == R.id.nav_send) {
//            DebugLog.showLogCat("nav_send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
