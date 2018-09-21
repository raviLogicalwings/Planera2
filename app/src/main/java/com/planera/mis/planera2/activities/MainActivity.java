package com.planera.mis.planera2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.planera.mis.planera2.R;
import com.planera.mis.planera2.activities.fragments.HomeFragment;
import com.planera.mis.planera2.activities.fragments.ProfileFragment;
import com.planera.mis.planera2.activities.utils.AppConstants;
import com.planera.mis.planera2.activities.utils.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity implements
        View.OnClickListener {

    private TextView mTextMessage;
    private  BottomNavigationView navigation;
    public FragmentManager manager;
    private Fragment fragment;
    private AppBarLayout appBar;
    private Toolbar toolbarProduct;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                      loadFragment(AppConstants.HOME_FRAGMENT);
                      getSupportActionBar().setTitle("Visit Plan");
                        return true;
                    case R.id.navigation_meeting:
                        return true;
                    case R.id.navigation_profile:
                     loadFragment(AppConstants.PROFILE_FRAGMENT);
                        return true;
                    case R.id.navigation_logout:

                        backToLogin();
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initUi();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        }


    public void loadFragment(int type){
        switch (type){

            case AppConstants.HOME_FRAGMENT:
                fragment = (Fragment) new HomeFragment();
//               getSupportActionBar().setTitle("Home");
                break;

            case AppConstants.PROFILE_FRAGMENT:
                fragment = ProfileFragment.newInstance();
//               getSupportActionBar().setTitle("Profile");
                break;


        }

        manager.beginTransaction().replace(R.id.container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
    }
    @Override
    public void initUi() {
        super.initUi();
        navigation  = findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        mTextMessage = findViewById(R.id.message);


        appBar = findViewById(R.id.appBar);
        toolbarProduct = (Toolbar) findViewById(R.id.toolbarProduct);
        setSupportActionBar(toolbarProduct);
        getSupportActionBar().setTitle("Visit Plan");
        toolbarProduct.setNavigationIcon(R.drawable.back_arrow_whit);

    }

    public void backToLogin(){
        connector.setBoolean(AppConstants.IS_LOGIN, false);
        Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intentLogin);
    }

    @Override
    public void initData() {
        super.initData();
        manager = getSupportFragmentManager();
       loadFragment(AppConstants.HOME_FRAGMENT);
    }

    @Override
    public void onClick(View view) {
    }

}
