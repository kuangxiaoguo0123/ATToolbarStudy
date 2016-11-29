package com.asiatravel.attoolbarstudy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.balysv.materialmenu.MaterialMenuDrawable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private MaterialMenuDrawable mMenuDrawable;
    private boolean isDrawerOpen;
    private MaterialMenuDrawable.AnimationState mCurrentState;
    private View mStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mStateView = findViewById(R.id.state_view);
        mMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE,
                MaterialMenuDrawable.Stroke.THIN);
        mCurrentState = MaterialMenuDrawable.AnimationState.BURGER_ARROW;
        initToolbar();
        initDrawerLayout();
        initButton();
    }

    private void initButton() {
        Button mBurgerArrowButton = (Button) mStateView.findViewById(R.id.burger_arrow_button);
        Button mBurgerXButton = (Button) mStateView.findViewById(R.id.burger_x_button);
        Button mBurgerCheckButton = (Button) mStateView.findViewById(R.id.burger_check_button);
        mBurgerArrowButton.setOnClickListener(this);
        mBurgerXButton.setOnClickListener(this);
        mBurgerCheckButton.setOnClickListener(this);
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
                        mCurrentState, isDrawerOpen ?
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.burger_arrow_button:
                mCurrentState = MaterialMenuDrawable.AnimationState.BURGER_ARROW;
                break;
            case R.id.burger_x_button:
                mCurrentState = MaterialMenuDrawable.AnimationState.BURGER_X;
                break;
            case R.id.burger_check_button:
                mCurrentState = MaterialMenuDrawable.AnimationState.BURGER_CHECK;
                break;
        }
    }
}
