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

        <include
            android:id="@+id/state_view"
            layout="@layout/state_layout" />

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
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    /**
     * 用来给Toolbar设置NavigationIcon，实现icon的动画切换
     */
    private MaterialMenuDrawable mMenuDrawable;
    private boolean isDrawerOpen;
    /**
     * icon的不同切换状态
     */
    private MaterialMenuDrawable.AnimationState mCurrentState;
    private View mStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        /**
         * 给当前窗口设置ActionBar为Toolbar
         */
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mStateView = findViewById(R.id.state_view);
        /**
         * 初始化mMenuDrawable
         */
        mMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE,
                MaterialMenuDrawable.Stroke.THIN);
        /**
         * 默认切换动画是从菜单icon到返回icon
         */
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

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(mMenuDrawable);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 判断点击icon时是关闭还是打开drawerLayout
                 */
                boolean drawerOpen = mDrawerLayout.isDrawerOpen(GravityCompat.START);
                if (drawerOpen) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    return;
                }
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    /**
     * 初始化DrawerLayout
     */
    private void initDrawerLayout() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                /**
                 * 实现drawerLayout滑动过程中icon的动画变化
                 */
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
        /**
         * 通过按钮改变icon的切换动画
         */
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

````
