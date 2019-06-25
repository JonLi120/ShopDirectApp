package com.twobytwoshop.ShopDirect.core;

import com.twobytwoshop.ShopDirect.R;
import com.twobytwoshop.ShopDirect.models.ShowLoadingBean;
import com.twobytwoshop.ShopDirect.utils.SharedPreferencesUtil;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposable = new CompositeDisposable();
    protected BaseApplication ba = BaseApplication.getInstance();
    protected SharedPreferencesUtil sp = SharedPreferencesUtil.getInstance();
    protected ShowLoadingBean showLoadingBean;

    public MutableLiveData<ShowLoadingBean> showLoading = new MutableLiveData<>();

    public BaseViewModel() {
        showLoadingBean = new ShowLoadingBean(true);
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }

    protected void setShowLoading(boolean isShow, String content) {
        showLoadingBean.setShow(isShow);
        showLoadingBean.setContent(content.isEmpty()? ba.getString(R.string.lab_loading): content);
        showLoading.postValue(showLoadingBean);
    }
}
