package com.scatl.uestcbbs.module.setting.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.Preference;

import com.scatl.uestcbbs.R;
import com.scatl.uestcbbs.base.BasePreferenceFragment;
import com.scatl.uestcbbs.base.BasePresenter;
import com.scatl.uestcbbs.entity.UpdateBean;
import com.scatl.uestcbbs.module.message.view.PrivateChatActivity;
import com.scatl.uestcbbs.module.setting.presenter.SettingsPresenter;
import com.scatl.uestcbbs.module.update.view.UpdateFragment;
import com.scatl.uestcbbs.module.webview.view.WebViewActivity;
import com.scatl.uestcbbs.util.CommonUtil;
import com.scatl.uestcbbs.util.Constant;
import com.scatl.uestcbbs.util.SharePrefUtil;
import com.scatl.uestcbbs.util.TimeUtil;

/**
 * author: sca_tl
 * description:
 * date: 2020/1/27 13:23
 */
public class SettingsFragment extends BasePreferenceFragment implements SettingsView{

    private SettingsPresenter settingsPresenter;

    @Override
    protected BasePresenter initPresenter() {
        return new SettingsPresenter();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getPreferenceManager().setSharedPreferencesName("settings");
        addPreferencesFromResource(R.xml.pref_settings);

        init();
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getKey().equals(getString(R.string.clear_cache))) {
            settingsPresenter.clearCache(mActivity);
        }
//        if (preference.getKey().equals(getString(R.string.super_account))) {
////            mActivity.startActivity(new Intent(mActivity, SuperAccountActivity.class));
//        }

//        if (preference.getKey().equals(getString(R.string.home_style))) {
//            ((ListPreference) preference).setDialogTitle("更换首页样式（重启生效）");
//            preference.setOnPreferenceChangeListener((preference1, newValue) -> {
//                Snackbar.make(mActivity.getWindow().getDecorView(), "更改成功，重启生效", Snackbar.LENGTH_LONG)
////                        .setAction("立即重启", v -> {
////
////                            Intent intent = mActivity.getApplicationContext().getPackageManager().getLaunchIntentForPackage(mActivity.getPackageName());
////                            if (intent != null) intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                            mActivity.getApplicationContext().startActivity(intent);
////                        })
//                        .show();
//                return true;
//            });
//        }

        if (preference.getKey().equals(getString(R.string.app_update))) {
            settingsPresenter.getUpdate();
        }

        if (preference.getKey().equals(getString(R.string.app_suggestion_contact_developer))) {
            Intent intent = new Intent(mActivity, PrivateChatActivity.class);
            intent.putExtra(Constant.IntentKey.USER_ID, 217992);
            intent.putExtra(Constant.IntentKey.USER_NAME, "私信开发者：sca_tl");
            startActivity(intent);
        }

        if (preference.getKey().equals(getString(R.string.app_suggestion_contact_web))) {
            Intent intent = new Intent(mActivity, WebViewActivity.class);
            intent.putExtra(Constant.IntentKey.URL, "https://support.qq.com/product/141698");
            startActivity(intent);
        }

        if (preference.getKey().equals(getString(R.string.app_about))) {
            Intent intent = new Intent(mActivity, AboutActivity.class);
            startActivity(intent);
        }

        if (preference.getKey().equals(getString(R.string.auto_load_more))) {
            SharePrefUtil.setAutoLoadMore(mActivity, SharePrefUtil.isAutoLoadMore(mActivity));
        }

        return super.onPreferenceTreeClick(preference);
    }

    /**
     * author: sca_tl
     * description:
     */
    private void init() {

        settingsPresenter = (SettingsPresenter) presenter;

        //((SwitchPreferenceCompat)findPreference(getString(R.string.auto_load_more))).setChecked(SharePrefUtil.isAutoLoadMore(mActivity));
        Preference k = findPreference(getString(R.string.app_update));
        if (k != null) k.setSummary("当前版本：" + CommonUtil.getVersionName(mActivity));
        //checkUpdate(false);
    }

    @Override
    public void getUpdateSuccess(UpdateBean updateBean) {
        if (updateBean.versionCode > CommonUtil.getVersionCode(mActivity)) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.IntentKey.DATA, updateBean);
            UpdateFragment.getInstance(bundle)
                    .show(getChildFragmentManager(), TimeUtil.getStringMs());
        } else {
            showSnackBar(getView(), "已经是最新版本啦");
        }
    }

    @Override
    public void getUpdateFail(String msg) {

    }

    @Override
    public void onClearCacheSuccess() {
        showSnackBar(getView(), "清理缓存成功");
    }
}
