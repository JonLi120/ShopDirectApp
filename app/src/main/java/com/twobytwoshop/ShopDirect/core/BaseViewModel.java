package com.twobytwoshop.ShopDirect.core;

import com.twobytwoshop.ShopDirect.utils.SharedPreferencesUtil;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposable = new CompositeDisposable();
    protected BaseApplication ba = BaseApplication.getInstance();
    protected SharedPreferencesUtil sp = SharedPreferencesUtil.getInstance();

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
