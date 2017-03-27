package com.defaultapps.translator.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.defaultapps.translator.R;
import com.defaultapps.translator.ui.base.BaseActivity;
import com.defaultapps.translator.ui.fragment.FavoritesViewImpl;
import com.defaultapps.translator.ui.fragment.HistoryViewImpl;
import com.defaultapps.translator.ui.fragment.TranslateViewImpl;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;


import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;

    @State
    int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomNavigationBar();
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            selectFragment(menuItem);
            return true;
        });
        if (savedInstanceState == null) {
            selectFragment(bottomNavigationView.getMenu().getItem(0));
        }
    }

    @Override
    public void onBackPressed() {
        MenuItem menuItem = bottomNavigationView.getMenu().getItem(0);
        if (menuItem.getItemId() != selectedItem) {
            selectFragment(menuItem);
        } else {
            super.onBackPressed();
        }
    }

    private void initBottomNavigationBar() {
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.navTranslate).setIcon(
                new IconDrawable(this, MaterialIcons.md_translate)
                .colorRes(R.color.whitePrimary)
        );
        menu.findItem(R.id.navHistory).setIcon(
                new IconDrawable(this, MaterialIcons.md_history)
                .colorRes(R.color.whitePrimary)
        );
        menu.findItem(R.id.navFavorites).setIcon(
                new IconDrawable(this, MaterialIcons.md_favorite)
                .colorRes(R.color.whitePrimary)
        );
    }

    private void selectFragment(final MenuItem menuItem) {
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.navTranslate:
                fragment = new TranslateViewImpl();
                break;
            case R.id.navHistory:
                fragment = new HistoryViewImpl();
                break;
            case R.id.navFavorites:
                fragment = new FavoritesViewImpl();
                break;
            default:
                fragment = new TranslateViewImpl();
                break;
        }
        selectedItem = menuItem.getItemId();

        for (int i = 0; i< bottomNavigationView.getMenu().size(); i++) {
            MenuItem item = bottomNavigationView.getMenu().getItem(i);
            menuItem.setChecked(item.getItemId() == menuItem.getItemId());
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentFrame, fragment, fragment.getTag());
        ft.commit();
    }
}
