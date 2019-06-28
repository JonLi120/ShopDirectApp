package com.twobytwoshop.ShopDirect;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.twobytwoshop.ShopDirect.adapter.DrawerAdapter;
import com.twobytwoshop.ShopDirect.core.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_frame_layout)
    FrameLayout mainFrameLayout;
    @BindView(R.id.main_nav_view)
    NavigationView mainNavView;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout mainDrawerLayout;
    @BindView(R.id.main_nav_rcv)
    RecyclerView mainNavRcv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindArray(R.array.nav_arr)
    String[] navArr;

    @BindColor(R.color.colorBlack)
    int black;
    @BindColor(R.color.colorHint)
    int hintColor;
    @BindString(R.string.item_nav_profile)
    String profile;
    @BindString(R.string.lab_search_hint)
    String searchHintLab;
    @BindString(R.string.item_nav_change_personal)
    String personal;
    @BindString(R.string.item_nav_change_proxy)
    String proxy;
    @BindString(R.string.item_nav_category)
    String category;
    @BindString(R.string.item_nav_gift)
    String gift;
    @BindString(R.string.item_nav_logout)
    String logout;
    @BindDrawable(R.drawable.ic_close)
    Drawable close;

    private boolean isBack = false;
    private boolean isSearch = true;
    private ActionBar actionBar;
    private int carCount = 0;
    private boolean isProxy = false;
    private List<Integer> imgArr = new ArrayList<>();
    private DrawerAdapter adapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbar();
        initView();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    private void initView() {
        HomeFragment fragment = HomeFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_frame_layout, fragment, HomeFragment.class.getSimpleName());
        ft.commit();

        mainNavRcv.setHasFixedSize(true);
        mainNavRcv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DrawerAdapter(null);
        mainNavRcv.setAdapter(adapter);
        mainNavRcv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter.replaceData(getNavList());

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            if (mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mainDrawerLayout.closeDrawer(GravityCompat.START);
            }
            String choiceLab = adapter.getItem(position);
            if (logout.equals(choiceLab)) {
                logout();
            } else if (proxy.equals(choiceLab)) {
                isProxy = true;
                adapter.replaceData(getNavList());
            } else if (personal.equals(choiceLab)) {
                isProxy = false;
                adapter.replaceData(getNavList());
            } else if (category.equals(choiceLab)) {
                startCategoryFragment();
            } else if (gift.equals(choiceLab)) {
                startGiftsFragment();
            } else if (profile.equals(choiceLab)) {
                startUserFragment();
            }
        });
    }

    public void logout() {
        sp.setUUID("");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(R.id.search_src_text);
        searchView.setIconified(true);
        searchView.setQueryHint(searchHintLab);
        searchView.clearFocus();
        searchAutoComplete.setTextColor(black);
        searchAutoComplete.setHintTextColor(hintColor);

        ImageView imgView = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        imgView.setImageDrawable(close);

        searchItem.setVisible(false);

        // car menu item
        View actionView = menu.findItem(R.id.car).getActionView();
        ImageButton carIcon = actionView.findViewById(R.id.badge_icon_button);
        TextView badgeView = actionView.findViewById(R.id.badge_textView);
        if (carCount == 0) {
            badgeView.setVisibility(View.GONE);
        } else {
            badgeView.setVisibility(View.VISIBLE);
            badgeView.setText(String.valueOf(carCount));
        }
        carIcon.setOnClickListener(view -> ShopCarActivity.startActivity(this));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (isBack) {
                getSupportFragmentManager().popBackStack();
                if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    changeMenuLayout(false, true);
                }
            } else {
                if (mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mainDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mainDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                changeMenuLayout(false, true);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void changeMenuLayout(boolean isBack, boolean isSearch) {
        assert actionBar != null;
        this.isBack = isBack;
        if (isBack) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        } else {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        this.invalidateOptionsMenu();
    }

    public void addCarCount() {
        carCount++;
        this.invalidateOptionsMenu();
    }

    public void startProductFragment(String pid) {
        String tag = ProductFragment.class.getSimpleName();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            ProductFragment fragment = ProductFragment.newInstance(pid);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_frame_layout, fragment, tag);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public void startCategoryFragment() {
        String tag = CategoryFragment.class.getSimpleName();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            CategoryFragment fragment = CategoryFragment.newInstance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_frame_layout, fragment, tag);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public void startCategoryListFragment(String caid) {
        String tag = ProductListFragment.class.getSimpleName();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            ProductListFragment fragment = ProductListFragment.newInstance(caid);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_frame_layout, fragment, tag);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public void startGiftsFragment() {
        String tag = GiftFragment.class.getSimpleName();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            GiftFragment fragment = GiftFragment.newInstance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_frame_layout, fragment, tag);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public void startUserFragment() {
        String tag = UserFragment.class.getSimpleName();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            UserFragment fragment = UserFragment.newInstance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_frame_layout, fragment, tag);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    private List<String> getNavList() {
        List<String> list = new ArrayList<>();
        List<Integer> imgList = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.nav_img_arr);

        int count = 0;
        for (String item : navArr) {
            if (isProxy) {
                if (count != 3 && count != 5) {
                    list.add(item);
                    imgList.add(imgs.getResourceId(count, 0));
                }
            } else {
                if (count != 2 && count != 6) {
                    list.add(item);
                    imgList.add(imgs.getResourceId(count, 0));
                }
            }
            count++;
        }
        adapter.setImgs(imgList);
        return list;
    }
}
