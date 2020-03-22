package org.moengage.news.ui.articlelist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import org.moengage.news.AppController;
import org.moengage.news.BaseActivity;
import org.moengage.news.BuildConfig;
import org.moengage.news.Config;
import org.moengage.news.R;
import org.moengage.news.SyncDataBroadcastReceiver;
import org.moengage.news.databinding.ActivityArticleListBinding;
import org.moengage.news.ui.articlelist.filter.ArticleFilterBottomSheetFragment;

public class ArticleListActivity extends BaseActivity {

    ActivityArticleListBinding binding;
    ArticleListViewModel viewModel;

    @Override
    protected void onNetworkChange(boolean isConnected) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);
        viewModel = new ArticleListViewModel(getApplication(), publishers -> {
            ArticleFilterBottomSheetFragment filterBottomDialogFragment =
                    ArticleFilterBottomSheetFragment.newInstance(publishers, viewModel);
            filterBottomDialogFragment.show(getSupportFragmentManager(),
                    ArticleFilterBottomSheetFragment.TAG);
        });
        binding.setVm(viewModel);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setSyncRepeatingAlarm();
    }

    /**
     * sets repeating alarm for syncing
     */
    public void setSyncRepeatingAlarm() {
        Intent intent = new Intent(this, SyncDataBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Config.SYNC_ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE);
        AppController.getInstance().getAlarmManager().setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + BuildConfig.CACHE_TIMEOUT,
                BuildConfig.CACHE_TIMEOUT,
                pendingIntent);
    }
}
