package com.example.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.list.remote.AuthApi;
import com.example.list.remote.AuthResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.list.BudgetFragment.REQUEST_CODE;

public class MainActivity extends AppCompatActivity {

    private static final String userId = "thgewe";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager pages = findViewById(R.id.pages);
        FloatingActionButton floatingActionButton = findViewById(R.id.floating_button_add);
        floatingActionButton.setOnClickListener(v -> {
            final int activeFragmentIndex = pages.getCurrentItem();
            Fragment activeFragment = getSupportFragmentManager().getFragments().get(activeFragmentIndex);
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            if (tabs.getTabAt(0).isSelected()) {
                intent.putExtra("color", 0);
            } else {
                intent.putExtra("color", 1);
            }
            activeFragment.startActivityForResult(intent, REQUEST_CODE);
        });

        pages.setAdapter(new BudgetPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        makeLogin(((LoftApp) getApplication()).authApi);
        tabs.setupWithViewPager(pages);
        tabs.getTabAt(0).setText("Expense");
        tabs.getTabAt(1).setText("Income");
    }

    @Override
    protected void onResume() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof BudgetFragment) {
                ((BudgetFragment)fragment).loadItems(getSharedPreferences(getString(R.string.app_name), 0));
            }
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    static class BudgetPagerAdapter extends FragmentPagerAdapter {
        public BudgetPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return BudgetFragment.newInstance("expense");
                case 1:
                    return BudgetFragment.newInstance("income");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void makeLogin(AuthApi authApi) {
        final String token = PreferenceManager.getDefaultSharedPreferences(this).getString(LoftApp.TOKEN, "");
        if (TextUtils.isEmpty(token)) {
            compositeDisposable.add(authApi.makeLogin(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(authResponse -> {
                        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), 0);
                        sharedPreferences.edit().putString(LoftApp.TOKEN, authResponse.getAuthToken()).apply();
                    }, throwable -> {
                        Toast.makeText(getApplicationContext(),throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }));
        }
    }

}