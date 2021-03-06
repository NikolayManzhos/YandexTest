package com.defaultapps.translator.ui.translate;

import com.defaultapps.translator.data.model.realm.RealmTranslate;
import com.defaultapps.translator.ui.base.MvpPresenter;

public interface TranslateViewPresenter extends MvpPresenter<TranslateView> {
    void setCurrentText(String text);
    void requestTranslation(boolean forceUpdate);
    String getCurrentText();
    void requestLangNames();
    void checkFirstTimeUser();
    void swapLanguages();
    void addToFavorites(RealmTranslate realmTranslate);
    void deleteFromFavorites(RealmTranslate realmTranslate);
}
