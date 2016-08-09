package com.example.cass.wohand;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;


import com.example.cass.wohand.base.DraggableFloatingButton;
import com.example.cass.wohand.base.MyFragmentFactory;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.lang.reflect.Method;

import butterknife.Bind;

@ContentView(R.layout.app_activity_main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }




    private void initView() {
        x.view().inject(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.select_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        //默认第一个为选中
        mRadioGroup.check(R.id.btn_home);

        //悬浮按钮
        DraggableFloatingButton fab = (DraggableFloatingButton) findViewById(R.id.fab);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

        //布局文件
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //侧边栏
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
        //右上角菜单初始化
        //显示图标
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (NoSuchMethodException e) {
                } catch (Exception e) {
                }
            }
        }

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

    //侧边栏子按钮
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        Fragment fragment = MyFragmentFactory.createFragment(checkedId);
        //使用指定的fragment切换当前页面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();

        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.btn_home:
                getSupportActionBar().setTitle(R.string.select_home);
                break;
            case R.id.btn_contacts:
                getSupportActionBar().setTitle(R.string.select_contacts);
                break;
            case R.id.btn_sns:
                getSupportActionBar().setTitle(R.string.select_sns);
                break;
        }
    }
}
