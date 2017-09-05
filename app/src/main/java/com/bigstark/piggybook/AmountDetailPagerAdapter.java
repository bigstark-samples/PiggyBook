package com.bigstark.piggybook;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bigstark.piggybook.db.Amount;

import java.util.List;

/**
 * Created by bigstark on 2017. 9. 5..
 */

public class AmountDetailPagerAdapter extends FragmentStatePagerAdapter {

    private List<Amount> amounts;

    public AmountDetailPagerAdapter(FragmentManager fm, List<Amount> amounts) {
        super(fm);
        this.amounts = amounts;
    }

    @Override
    public Fragment getItem(int position) {
        return AmountDetailFragment.newInstance(amounts.get(position).getId());
    }

    @Override
    public int getCount() {
        return amounts.size();
    }
}
