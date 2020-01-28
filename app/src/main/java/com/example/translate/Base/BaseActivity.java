package com.example.translate.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.translate.R;
import com.example.translate.Utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements BaseView<P> {

    @Nullable
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;

    Unbinder bind;

    /**
     * 进度对话框
     */
    protected ProgressDialog mProgressDialog;
    /**
     * 泛型确定Presenter
     */
    protected P mPresenter;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(bindLayout());
        // ButterKnife绑定布局
        bind = ButterKnife.bind(this);

        mPresenter = createPresenter();
        if (mPresenter != null) {
            // 调用Presenter初始化方法
            mPresenter.onStart();
        }

        // 准备数据
        prepareData();
        // 初始化标题栏
        initToolbar();
        // 初始化视图
        initView();
        // 初始化数据
        initData(savedInstanceState);
        // 初始化事件监听
        initEvent();
    }


    /**
     * 创建Presenter
     *
     * @return 泛型Presenter
     */
    protected abstract P createPresenter();

    /**
     * 实现BasePresenter接口的setPresenter方法
     *
     * @param presenter createPresenter()创建的Presenter
     */
    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        setSupportActionBar(mtoolbar);
    }



    /**
     * 设置Toolbar标题
     *
     * @param title 标题
     */
    protected void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    /**
     * 设置Toolbar显示返回按钮及标题
     *
     * @param title 标题
     */
    protected void setToolbarBackEnable(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setToolbarTitle(title);
        }

    }

    /**
     * 绑定布局
     *
     * @return 布局文件的资源ID
     */
    protected abstract int bindLayout();

    /**
     * 准备数据（从Intent获取上一个界面传过来的数据或其他需要初始化的数据）
     */
    protected abstract void prepareData();

    /**
     * 初始化视图，findViewById等等
     */
    protected abstract void initView();

    /**
     * 初始化数据，从本地或服务器开始获取数据
     *
     * @param savedInstanceState 界面非正常销毁时保存的数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化事件监听，setOnClickListener等等
     */
    protected abstract void initEvent();

    /**
     * 实现BaseView的showToast(CharSequence msg)
     *
     * @param msg 吐司显示的信息
     */
    @Override
    public void showToast(CharSequence msg) {
        ToastUtils.shortToast(this, msg);
    }

    /**
     * 实现BaseView的showToast(int msgId)
     *
     * @param msgId 吐司显示的字符串资源id
     */
    @Override
    public void showToast(int msgId) {
        ToastUtils.shortToast(this, msgId);
    }

    /**
     * 实现BaseView的showLoadingDialog(CharSequence msg)
     * 显示加载对话框
     *
     * @param msg 对话框的提示内容
     */
    @Override
    public void showLoadingDialog(CharSequence msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle(R.string.title_dialog_tips);
            mProgressDialog.setMessage(msg);
        } else {
            mProgressDialog.setTitle(R.string.title_dialog_tips);
        }
        mProgressDialog.show();
    }

    /**
     * 实现BaseView的hideLoadingDialog()
     * 隐藏加载对话框
     */
    @Override
    public void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * Activity销毁时清理资源
     */
    @Override
    protected void onDestroy() {
        // ButterKnife解除绑定
        bind.unbind();
        // 销毁Presenter
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）
        // 保证 onPageEnd 在onPause之前调用,因为 onPause 中会保存信息。
//        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。)
//        MobclickAgent.onPageStart(this.getClass().getSimpleName());
//        统计时长
//        MobclickAgent.onResume(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}