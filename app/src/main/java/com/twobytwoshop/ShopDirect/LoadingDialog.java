package com.twobytwoshop.ShopDirect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.twobytwoshop.ShopDirect.core.BaseDialogFragment;
import com.twobytwoshop.ShopDirect.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoadingDialog extends BaseDialogFragment {
    private static final String KEY_CONTENT = "KEY_CONTENT";

    @BindView(R.id.load_text)
    TextView dialogLoadingContent;

    private String content;
    private Unbinder unbinder;

    public static LoadingDialog newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(KEY_CONTENT, content);

        LoadingDialog fragment = new LoadingDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            content = bundle.getString(KEY_CONTENT);
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Window window = getDialog().getWindow();
//        if (window != null && activity != null) {
//            window.setLayout((int) (DisplayUtil.getPhoneWidth(activity) * 0.65), ViewGroup.LayoutParams.WRAP_CONTENT);
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_loading, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!content.isEmpty() && dialogLoadingContent != null) {
            dialogLoadingContent.setText(content);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeThis(LoadingDialog.class);
        unbinder.unbind();
    }

    public void setContent(String content) {
        if (dialogLoadingContent != null) {
            dialogLoadingContent.setText(content);
        }
    }
}
