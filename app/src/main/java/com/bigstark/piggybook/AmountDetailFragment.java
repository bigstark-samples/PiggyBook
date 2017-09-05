package com.bigstark.piggybook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigstark.piggybook.db.Amount;
import com.bigstark.piggybook.db.AmountCategory;
import com.bigstark.piggybook.db.PiggyDatabase;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by bigstark on 2017. 9. 5..
 */

public class AmountDetailFragment extends Fragment {

    private static final String KEY_AMOUNT_ID = "amountId";


    public static AmountDetailFragment newInstance(int amountId) {
        AmountDetailFragment fragment = new AmountDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_AMOUNT_ID, amountId);

        fragment.setArguments(bundle);
        return fragment;
    }

    private static final long MONTH = 1000L * 60 * 60 * 24 * 30;


    private TextView tvDate;
    private TextView tvContent;
    private TextView tvAmount;
    private TextView tvComment;

    private int colorIncome;
    private int colorOutcome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_amount_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDate = view.findViewById(R.id.tvDate);
        tvContent = view.findViewById(R.id.tvContent);
        tvAmount = view.findViewById(R.id.tvAmount);
        tvComment = view.findViewById(R.id.tvComment);

        colorIncome = getResources().getColor(R.color.colorAccent);
        colorOutcome = getResources().getColor(R.color.colorPrimary);


        int amountId = getArguments().getInt(KEY_AMOUNT_ID);

        Amount amount = PiggyDatabase.getAmountDao().getAmount(amountId);

        setInfo(amount);
        setComment(amount);
    }


    private void setInfo(Amount amount) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        tvDate.setText(sdf.format(amount.getDate()));

        tvContent.setText(amount.getContent());

        DecimalFormat df = new DecimalFormat();
        tvAmount.setText("₩" + df.format(amount.getAmount()));
        tvAmount.setTextColor(amount.getCategory() == AmountCategory.INCOME ? colorIncome : colorOutcome);
    }

    private void setComment(Amount amount) {
        Date from = new Date(amount.getDate().getTime() - MONTH);
        List<Amount> recentAmounts = PiggyDatabase.getAmountDao().getAmounts(from, amount.getDate());

        int recentSumAmount = 0;
        for (Amount recentAmount : recentAmounts) {
            recentSumAmount += amount.getCategory() == recentAmount.getCategory() ? recentAmount.getAmount() : 0;
        }

        if (recentSumAmount == 0) {
            tvComment.setText("");
            return;
        }

        int percent = Math.round((float) amount.getAmount() / recentSumAmount * 100);
        tvComment.setText(String.format("(%d%% of %s recent 1 month)", percent, amount.getCategory().toString().toLowerCase()));
    }
}
