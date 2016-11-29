package com.asiatravel.attoolbarstudy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private MaterialMenuDrawable mMenuDrawable;
    private boolean isDrawerOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE,
                MaterialMenuDrawable.Stroke.THIN);
        initToolbar();
        initDrawerLayout();
    }

    private void initToolbar() {
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(mMenuDrawable);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean drawerOpen = mDrawerLayout.isDrawerOpen(GravityCompat.START);
                if (drawerOpen) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    return;
                }
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initDrawerLayout() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mMenuDrawable.setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW, isDrawerOpen ?
                                2 - slideOffset : slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isDrawerOpen = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawerOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }
}
