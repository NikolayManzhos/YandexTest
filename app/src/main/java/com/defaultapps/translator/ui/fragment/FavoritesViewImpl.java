package com.defaultapps.translator.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.defaultapps.translator.R;
import com.defaultapps.translator.ui.activity.MainActivity;
import com.defaultapps.translator.ui.base.BaseActivity;
import com.defaultapps.translator.ui.presenter.FavoritesViewPresenterImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavoritesViewImpl extends Fragment implements FavoritesView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    FavoritesViewPresenterImpl favoritesViewPresenter;

    private MainActivity activity;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            activity = (MainActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle("Favorites");
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        favoritesViewPresenter.onAttach(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        favoritesViewPresenter.onDetach();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }
}
