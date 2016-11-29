# ATToolbarStudy
Android中Toolbar的使用

# ScreenShots
![](https://github.com/kuangxiaoguo0123/ATToolbarStudy/blob/master/screenshots/toolbar.gif)

# 添加依赖
````
compile 'com.balysv.materialmenu:material-menu:2.0.0'
````
# xml使用
````
<android.support.v7.widget.Toolbar
        android:id="@+id/tool_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="?attr/colorPrimary" />

    <android.support.v4.widget.DrawerLayout
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="主布局内容"
            android:textSize="18sp" />

        <LinearLayout
            android:layout_width="300dp"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:background="@color/colorOrange"
            android:orientation="vertical">

            <Button
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_horizontal"
                android:layout_marginTop="10dp"
                android:gravity="center"
                android:text="左边的布局" />
        </LinearLayout>
</android.support.v4.widget.DrawerLayout>
````
# MainActivity
````
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
````
