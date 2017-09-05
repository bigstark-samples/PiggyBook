package com.bigstark.piggybook;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bigstark.piggybook.db.Amount;
import com.bigstark.piggybook.db.PiggyDatabase;

import java.util.List;

public class AmountDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int amountId = getIntent().getIntExtra(Defines.KEY_AMOUNT_ID, 0);
        List<Amount> amounts = PiggyDatabase.getAmountDao().getAllAmounts();

        int position = 0;
        for (int i = 0; i < amounts.size(); i++) {
            if (amounts.get(i).getId() == amountId) {
                position = i;
                break;
            }
        }

        ViewPager vpAmount = findViewById(R.id.vpAmount);

        AmountDetailPagerAdapter adapter = new AmountDetailPagerAdapter(getSupportFragmentManager(), amounts);
        vpAmount.setAdapter(adapter);

        vpAmount.setCurrentItem(position);
    }
}
