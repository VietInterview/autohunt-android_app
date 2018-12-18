package com.vietinterview.getbee.activities;

import android.graphics.Color;
import android.graphics.Typeface;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.account.LstMenuAuthority;
import com.vietinterview.getbee.callback.OnFillBackgroundListener;
import com.vietinterview.getbee.callback.OnSetMenuListener;
import com.vietinterview.getbee.callback.OnSetTextGreetingListener;
import com.vietinterview.getbee.callback.OnShowLogoListener;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.constant.ApiConstantTest;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.customview.CircularTextView;
import com.vietinterview.getbee.customview.CustomTypefaceSpan;
import com.vietinterview.getbee.fragments.CustomerProfileFragment;
import com.vietinterview.getbee.fragments.HomeFragment;
import com.vietinterview.getbee.fragments.IntroFragment;
import com.vietinterview.getbee.fragments.JobsEmployerFragment;
import com.vietinterview.getbee.fragments.LoginFragment;
import com.vietinterview.getbee.fragments.MyResumeFragment;
import com.vietinterview.getbee.fragments.MyJobFragment;
import com.vietinterview.getbee.fragments.CollaboratorProfileFragment;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;

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
    TextView tvGreeting;
    ImageView imgVi;
    ImageView imgEn;
    public ActionBarDrawerToggle toggle;
    CircularTextView slideshow;
    CircularTextView gallery;

    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        if (AccountManager.getApiConstantTest() == null) {
            ApiConstantTest apiConstantInit = new ApiConstantTest();
            apiConstantInit.setBASE_URL(ApiConstant.REAL_URL);
            apiConstantInit.setIMG_URL(ApiConstant.IMG_URL_REAL);
            AccountManager.setApiConstantTest(apiConstantInit);
        }
        setLanguage(SharedPrefUtils.getString(AppConstant.LANGUAGE, "vi"));
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
        imgVi = headerView.findViewById(R.id.imgVi);
        imgEn = headerView.findViewById(R.id.imgEn);
        if (SharedPrefUtils.getString(AppConstant.LANGUAGE, "vi").equalsIgnoreCase("en")) {
            imgVi.setEnabled(true);
            imgEn.setEnabled(false);
            imgEn.setAlpha(50);
            imgVi.setAlpha(200);
        } else {
            imgVi.setEnabled(false);
            imgEn.setEnabled(true);
            imgEn.setAlpha(200);
            imgVi.setAlpha(50);
        }
        imgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.setCheckedItem(R.id.nav_home);
                FragmentUtil.replaceFragment(MainActivity.this, new HomeFragment(), null);
                SharedPrefUtils.putString(AppConstant.LANGUAGE, "vi");
                setLanguage(SharedPrefUtils.getString(AppConstant.LANGUAGE, "vi"));
            }
        });
        imgEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.setCheckedItem(R.id.nav_home);
                FragmentUtil.replaceFragment(MainActivity.this, new HomeFragment(), null);
                SharedPrefUtils.putString(AppConstant.LANGUAGE, "en");
                setLanguage(SharedPrefUtils.getString(AppConstant.LANGUAGE, "vi"));
            }
        });
        if (AccountManager.getUserInfoBean() != null)
            if (AccountManager.getUserInfoBean().getName() != null) {
                if (AccountManager.getUserInfoBean().getName().length() <= 12) {
                    tvGreeting.setText(getResources().getString(R.string.greeting) + " " + AccountManager.getUserInfoBean().getName() + "!");
                } else {
                    tvGreeting.setText(getResources().getString(R.string.greeting) + "\n" + AccountManager.getUserInfoBean().getName() + "!");
                }
            }
        getEventBaseActivity().setOnSetTextGreetingListener(new OnSetTextGreetingListener() {
            @Override
            public void onSetTextGreeting(String name) {
                if (name.length() <= 12) {
                    tvGreeting.setText(getResources().getString(R.string.greeting) + " " + name + "!");
                } else {
                    tvGreeting.setText(getResources().getString(R.string.greeting) + "\n" + name + "!");
                }
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
//        initializeCountDrawer();
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        final Menu menu = navigationView.getMenu();
        navigationView.getMenu().clear();
        getEventBaseActivity().setOnSetMenuListener(new OnSetMenuListener() {
            @Override
            public void onSetMenu() {
                navigationView.getMenu().clear();
                for (LstMenuAuthority lstMenuAuthority : AccountManager.getUserInfoBean().getLstMenuAuthority()) {
                    menu.add(0, lstMenuAuthority.getId(), 0, lstMenuAuthority.getName());

                }
//                for (int i = 0; i < AccountManager.getUserInfoBean().getLstMenuAuthority().size(); i++) {
//                    menu.add(0, AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getId(), 0, AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getName());
//                }
                for (int i = 0; i < menu.size(); i++) {
                    MenuItem mi = menu.getItem(i);
                    setIcon(mi);
                    SubMenu subMenu = mi.getSubMenu();
                    if (subMenu != null && subMenu.size() > 0) {
                        for (int j = 0; j < subMenu.size(); j++) {
                            MenuItem subMenuItem = subMenu.getItem(j);
                            applyFontToMenuItem(subMenuItem);
                        }
                    }
                    applyFontToMenuItem(mi);
                }
            }
        });
        if (AccountManager.getUserInfoBean() != null) {
            for (LstMenuAuthority lstMenuAuthority : AccountManager.getUserInfoBean().getLstMenuAuthority()) {
                menu.add(0, lstMenuAuthority.getId(), 0, lstMenuAuthority.getName());

            }
//            for (int i = 0; i < AccountManager.getUserInfoBean().getLstMenuAuthority().size(); i++) {
//                menu.add(0, AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getId(), 0, AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getName());
//            }
        } else
            navigationView.inflateMenu(R.menu.menu_left_ctv_drawer);
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            if (AccountManager.getUserInfoBean() != null) setIcon(mi);
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
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                FragmentUtil.replaceFragment(MainActivity.this, new LoginFragment(), null);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan((int) getResources().getDimension(R.dimen.font_menu_left), "", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isFirst = SharedPrefUtils.getBoolean(AppConstant.FIRST, true);
        if (isFirst) {
            FragmentUtil.replaceFragment(MainActivity.this, new IntroFragment(), null);
        } else {
            if (AccountManager.getUserInfoBean() != null) {
                if (GlobalDefine.currentFragment == null || GlobalDefine.currentFragment instanceof HomeFragment) {
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    navigationView.setCheckedItem(R.id.nav_home);
                    if (AccountManager.getUserInfoBean().getType() == 7)
                        FragmentUtil.replaceFragment(this, new HomeFragment(), null);
                    else FragmentUtil.replaceFragment(this, new JobsEmployerFragment(), null);
                } else {
                    if (GlobalDefine.currentFragment instanceof MyJobFragment) {
                        navigationView.setCheckedItem(R.id.nav_job);
                    } else if (GlobalDefine.currentFragment instanceof MyResumeFragment) {
                        navigationView.setCheckedItem(R.id.nav_cv);
                    } else if (GlobalDefine.currentFragment instanceof CollaboratorProfileFragment) {
                        navigationView.setCheckedItem(R.id.nav_profile);
                    }
                    FragmentUtil.replaceFragment(this, GlobalDefine.currentFragment, null);
                }
            } else {
                FragmentUtil.replaceFragment(MainActivity.this, new LoginFragment(), null);
            }
        }
    }

    public TextView getTvGreeting() {
        return tvGreeting;
    }

//    private void initializeCountDrawer() {
//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
//        GradientDrawable gd = new GradientDrawable();
//        gd.setCornerRadius(5);
//        gd.setSize(10, 10);
//        gallery.setGravity(Gravity.CENTER);
//        gallery.setTypeface(font, Typeface.BOLD);
//        gallery.setTextColor(getResources().getColor(R.color.black));
//        gallery.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
//        gallery.setSolidColor("#FFD215");
//        gallery.setTextSize(12);
//        gallery.setBackground(gd);
//        gallery.setStrokeColor("#00FFFFFF");
//        gallery.setStrokeWidth(10);
//        gallery.setText("99+");
//        slideshow.setGravity(Gravity.CENTER);
//        slideshow.setTypeface(font, Typeface.BOLD);
//        slideshow.setTextColor(getResources().getColor(R.color.black));
//        slideshow.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
//        slideshow.setSolidColor("#FFD215");
//        slideshow.setStrokeWidth(10);
//        slideshow.setStrokeColor("#00FFFFFF");
//        slideshow.setBackground(gd);
//        slideshow.setTextSize(12);
//        slideshow.setText("7");
//    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setIcon(MenuItem item) {
        for (LstMenuAuthority lstMenuAuthority : AccountManager.getUserInfoBean().getLstMenuAuthority()) {
            if (item.getItemId() == lstMenuAuthority.getId()) {
                switch (lstMenuAuthority.getCode()) {
                    case "CTV_HOME_PAGE":
                        item.setIcon(R.drawable.ic_home);
                        break;
                    case "CTV_JOB_SAVE":
                        item.setIcon(R.drawable.ic_vali);
                        break;
                    case "CTV_JOB_SENT":
                        item.setIcon(R.drawable.ic_vali);
                        break;
                    case "CTV_CV_SAVE":
                        item.setIcon(R.drawable.ic_mycv_menuleft);
                        break;
                    case "CTV_CV_SEND":
                        item.setIcon(R.drawable.ic_mycv_menuleft);
                        break;
                    case "CMS_JOB_AND_CV":
                        break;
                    case "CMS_CTV":
                        break;
                    case "CMS_JOB":
                        break;
                    case "CMS_CUSTOMER":
                        break;
                    case "CMS_CV":
                        break;
                    case "CUSTOMER_HOME_PAGE":
                        item.setIcon(R.drawable.ic_home);
                        break;
                    default:
                        item.setIcon(R.drawable.ic_user_menuleft);
                        break;
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        }
//        for (int i = 0; i < AccountManager.getUserInfoBean().getLstMenuAuthority().size(); i++) {
//            if (item.getItemId() == AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getId()) {
//                switch (AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getCode()) {
//                    case "CTV_HOME_PAGE":
//                        item.setIcon(R.drawable.ic_home);
//                        break;
//                    case "CTV_JOB_SAVE":
//                        item.setIcon(R.drawable.ic_vali);
//                        break;
//                    case "CTV_JOB_SENT":
//                        item.setIcon(R.drawable.ic_vali);
//                        break;
//                    case "CTV_CV_SAVE":
//                        item.setIcon(R.drawable.ic_mycv_menuleft);
//                        break;
//                    case "CTV_CV_SEND":
//                        item.setIcon(R.drawable.ic_mycv_menuleft);
//                        break;
//                    case "CMS_JOB_AND_CV":
//                        break;
//                    case "CMS_CTV":
//                        break;
//                    case "CMS_JOB":
//                        break;
//                    case "CMS_CUSTOMER":
//                        break;
//                    case "CMS_CV":
//                        break;
//                    case "CUSTOMER_HOME_PAGE":
//                        item.setIcon(R.drawable.ic_home);
//                        break;
//                    default:
//                        item.setIcon(R.drawable.ic_user_menuleft);
//                        break;
//                }
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                drawer.closeDrawer(GravityCompat.START);
//            }
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int accType = AccountManager.getUserInfoBean().getType();
        for (LstMenuAuthority lstMenuAuthority : AccountManager.getUserInfoBean().getLstMenuAuthority()) {
            if (item.getItemId() == lstMenuAuthority.getId()) {
                switch (lstMenuAuthority.getCode()) {
                    case "CTV_HOME_PAGE":
                        FragmentUtil.replaceFragment(this, new HomeFragment(), null);
                        break;
                    case "CTV_JOB_SAVE":
                        SharedPrefUtils.putString("menu", lstMenuAuthority.getCode());
                        FragmentUtil.replaceFragment(this, new MyJobFragment(), null);
                        break;
                    case "CTV_JOB_SENT":
                        SharedPrefUtils.putString("menu", lstMenuAuthority.getCode());
                        FragmentUtil.replaceFragment(this, new MyJobFragment(), null);
                        break;
                    case "CTV_CV_SAVE":
                        SharedPrefUtils.putString("menu", lstMenuAuthority.getCode());
                        FragmentUtil.replaceFragment(this, new MyResumeFragment(), null);
                        break;
                    case "CTV_CV_SEND":
                        SharedPrefUtils.putString("menu", lstMenuAuthority.getCode());
                        FragmentUtil.replaceFragment(this, new MyResumeFragment(), null);
                        break;
                    case "CMS_JOB_AND_CV":
                        break;
                    case "CMS_CTV":
                        break;
                    case "CMS_JOB":
                        break;
                    case "CMS_CUSTOMER":
                        break;
                    case "CMS_CV":
                        break;
                    case "CUSTOMER_HOME_PAGE":
                        if (accType == 2) {
                            FragmentUtil.replaceFragment(this, new JobsEmployerFragment(), null);
                        } else if (accType == 7) {
                            FragmentUtil.replaceFragment(this, new HomeFragment(), null);
                        } else {
                            FragmentUtil.replaceFragment(this, new JobsEmployerFragment(), null);
                        }
                        break;
                    default:
                        if (AccountManager.getUserInfoBean().getType() == 7)
                            FragmentUtil.replaceFragment(MainActivity.this, new CollaboratorProfileFragment(), null);
                        else if (AccountManager.getUserInfoBean().getType() == 2)
                            FragmentUtil.replaceFragment(MainActivity.this, new CustomerProfileFragment(), null);
                        else FragmentUtil.replaceFragment(MainActivity.this, new CollaboratorProfileFragment(), null);
                        break;
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        }
//        for (int i = 0; i < AccountManager.getUserInfoBean().getLstMenuAuthority().size(); i++) {
//            if (item.getItemId() == AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getId()) {
//                switch (AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getCode()) {
//                    case "CTV_HOME_PAGE":
//                        FragmentUtil.replaceFragment(this, new HomeFragment(), null);
//                        break;
//                    case "CTV_JOB_SAVE":
//                        SharedPrefUtils.putString("menu", AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getCode());
//                        FragmentUtil.replaceFragment(this, new MyJobFragment(), null);
//                        break;
//                    case "CTV_JOB_SENT":
//                        SharedPrefUtils.putString("menu", AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getCode());
//                        FragmentUtil.replaceFragment(this, new MyJobFragment(), null);
//                        break;
//                    case "CTV_CV_SAVE":
//                        SharedPrefUtils.putString("menu", AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getCode());
//                        FragmentUtil.replaceFragment(this, new MyResumeFragment(), null);
//                        break;
//                    case "CTV_CV_SEND":
//                        SharedPrefUtils.putString("menu", AccountManager.getUserInfoBean().getLstMenuAuthority().get(i).getCode());
//                        FragmentUtil.replaceFragment(this, new MyResumeFragment(), null);
//                        break;
//                    case "CMS_JOB_AND_CV":
//                        break;
//                    case "CMS_CTV":
//                        break;
//                    case "CMS_JOB":
//                        break;
//                    case "CMS_CUSTOMER":
//                        break;
//                    case "CMS_CV":
//                        break;
//                    case "CUSTOMER_HOME_PAGE":
//                        if (accType == 2) {
//                            FragmentUtil.replaceFragment(this, new JobsEmployerFragment(), null);
//                        } else if (accType == 7) {
//                            FragmentUtil.replaceFragment(this, new HomeFragment(), null);
//                        } else {
//                            FragmentUtil.replaceFragment(this, new JobsEmployerFragment(), null);
//                        }
//                        break;
//                    default:
//                        if (AccountManager.getUserInfoBean().getType() == 7)
//                            FragmentUtil.replaceFragment(MainActivity.this, new CollaboratorProfileFragment(), null);
//                        else if (AccountManager.getUserInfoBean().getType() == 2)
//                            FragmentUtil.replaceFragment(MainActivity.this, new CustomerProfileFragment(), null);
//                        else FragmentUtil.replaceFragment(MainActivity.this, new CollaboratorProfileFragment(), null);
//                        break;
//                }
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                drawer.closeDrawer(GravityCompat.START);
//            }
//        }
        return true;
    }
}
