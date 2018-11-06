package com.vietinterview.getbee.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.GetMyProfileRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.myprofile.MyProfileResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnFillBackgroundListener;
import com.vietinterview.getbee.callback.OnSetTextGreetingListener;
import com.vietinterview.getbee.callback.OnShowLogoListener;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.customview.CircularTextView;
import com.vietinterview.getbee.customview.CustomTypefaceSpan;
import com.vietinterview.getbee.fragments.HomeFragment;
import com.vietinterview.getbee.fragments.IntroFragment;
import com.vietinterview.getbee.fragments.LoginFragment;
import com.vietinterview.getbee.fragments.MyCVFragment;
import com.vietinterview.getbee.fragments.MyJobFragment;
import com.vietinterview.getbee.fragments.MyProfileFragment;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;
import com.vietinterview.getbee.utils.UiUtil;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvAppName)
    TextView tvAppName;
    @BindView(R.id.imglogo)
    ImageView imglogo;
    @BindView(R.id.nav_view)
    public NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawer;
    @BindView(R.id.mainview)
    public CoordinatorLayout mainview;
    @BindView(R.id.coverNetworkLoading)
    View coverNetworkLoading;
    TextView tvGreeting;
    public ActionBarDrawerToggle toggle;
    GetMyProfileRequest getMyProfileRequest;
    CircularTextView slideshow;
    CircularTextView gallery;
    MyProfileResponse myProfileResponse;

    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(null);
        getEventBaseActivity().setOnFillBackgroundListener(new OnFillBackgroundListener() {
            @Override
            public void onFillBackground(String nameTable) {
                tvAppName.setVisibility(View.VISIBLE);
                tvAppName.setText(nameTable);
                imglogo.setVisibility(View.GONE);
            }
        });
        View headerView = navigationView.getHeaderView(0);
        tvGreeting = (TextView) headerView.findViewById(R.id.tvGreeting);
        if (AccountManager.getUserInfoBean() != null)
            if (AccountManager.getUserInfoBean().getName() != null)
                tvGreeting.setText(getResources().getString(R.string.greeting) + AccountManager.getUserInfoBean().getName() + "!");
        getEventBaseActivity().setOnSetTextGreetingListener(new OnSetTextGreetingListener() {
            @Override
            public void onSetTextGreeting(String name) {
                tvGreeting.setText(getResources().getString(R.string.greeting) + name + "!");
            }
        });
        getEventBaseActivity().setOnShowLogoListener(new OnShowLogoListener() {
            @Override
            public void onShowLogo(boolean isShowLogo) {
                if (isShowLogo) {
                    imglogo.setVisibility(View.VISIBLE);
                    tvAppName.setVisibility(View.GONE);
                } else {
                    imglogo.setVisibility(View.GONE);
                    tvAppName.setVisibility(View.VISIBLE);
                }
            }
        });
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mainview.setTranslationX(slideOffset * drawerView.getWidth());
                drawer.bringChildToFront(drawerView);
                drawer.setScrimColor(Color.TRANSPARENT);
                drawer.requestLayout();

            }
        };
        gallery = (CircularTextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_job));
        slideshow = (CircularTextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_cv));
        slideshow.setVisibility(View.GONE);
        gallery.setVisibility(View.GONE);
        initializeCountDrawer();
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Menu m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(mi);
        }
        navigationView.setNavigationItemSelectedListener(this);
        View tvLogout = findViewById(R.id.tvLogout);
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountManager.logout();
                myProfileResponse = null;
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                FragmentUtil.replaceFragment(MainActivity.this, new LoginFragment(), null);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Nunito-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    @Override
    public void initData() {
        boolean isFirst = SharedPrefUtils.getBoolean(AppConstant.FIRST, true);
        if (isFirst) {
            FragmentUtil.replaceFragment(MainActivity.this, new IntroFragment(), null);
        } else {
            if (AccountManager.getUserInfoBean() != null) {
                if (GlobalDefine.currentFragment == null || GlobalDefine.currentFragment instanceof HomeFragment) {
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    navigationView.setCheckedItem(R.id.nav_home);
                    FragmentUtil.replaceFragment(this, new HomeFragment(), null);
                } else {
                    DebugLog.showLogCat("other");
                }
            } else {
                FragmentUtil.replaceFragment(MainActivity.this, new LoginFragment(), null);
            }
        }
    }

    public TextView getTvGreeting() {
        return tvGreeting;
    }

    private void initializeCountDrawer() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Nunito-Regular.ttf");
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(5);
        gd.setSize(10, 10);
        gallery.setGravity(Gravity.CENTER);
        gallery.setTypeface(font, Typeface.BOLD);
        gallery.setTextColor(getResources().getColor(R.color.black));
        gallery.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
        gallery.setSolidColor("#FFD215");
        gallery.setTextSize(12);
        gallery.setBackground(gd);
        gallery.setStrokeColor("#00FFFFFF");
        gallery.setStrokeWidth(10);
        gallery.setText("99+");
        slideshow.setGravity(Gravity.CENTER);
        slideshow.setTypeface(font, Typeface.BOLD);
        slideshow.setTextColor(getResources().getColor(R.color.black));
        slideshow.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
        slideshow.setSolidColor("#FFD215");
        slideshow.setStrokeWidth(10);
        slideshow.setStrokeColor("#00FFFFFF");
        slideshow.setBackground(gd);
        slideshow.setTextSize(12);
        slideshow.setText("7");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void showCoverNetworkLoading() {
        UiUtil.showView(coverNetworkLoading);
    }

    public void hideCoverNetworkLoading() {
        UiUtil.hideView(coverNetworkLoading, true);
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
        if (id == R.id.nav_home) {
            FragmentUtil.replaceFragment(this, new HomeFragment(), null);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_job) {
            FragmentUtil.replaceFragment(this, new MyJobFragment(), null);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_cv) {
            FragmentUtil.replaceFragment(this, new MyCVFragment(), null);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_profile) {
//            getMyProfile();
            FragmentUtil.replaceFragment(MainActivity.this, new MyProfileFragment(), null);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    public void getMyProfile() {
        showCoverNetworkLoading();
        getMyProfileRequest = new GetMyProfileRequest();
        getMyProfileRequest.callRequest(MainActivity.this, new ApiObjectCallBack<MyProfileResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, MyProfileResponse dataSuccess, List<MyProfileResponse> listDataSuccess, String message) {
                hideCoverNetworkLoading();
                myProfileResponse = dataSuccess;
                FragmentUtil.replaceFragment(MainActivity.this, new MyProfileFragment().newInstance(myProfileResponse), null);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {

            }
        });
    }
}
