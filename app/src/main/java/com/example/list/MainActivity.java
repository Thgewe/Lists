package com.example.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager pages = findViewById(R.id.pages);

        pages.setAdapter(new BudgetPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        tabs.setupWithViewPager(pages);
        tabs.getTabAt(0).setText("Expense");
        tabs.getTabAt(1).setText("Income");
    }

    @Override
    protected void onResume() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof BudgetFragment) {
                ((BudgetFragment)fragment).loadItems();
            }
        }
        super.onResume();
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
}