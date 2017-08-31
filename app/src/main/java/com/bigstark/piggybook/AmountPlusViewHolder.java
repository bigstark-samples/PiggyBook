package com.bigstark.piggybook;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigstark.piggybook.db.Amount;
import com.bigstark.piggybook.db.AmountCategory;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by bigstark on 2017. 8. 25..
 */

public class AmountPlusViewHolder extends RecyclerView.ViewHolder {

    private TextView tvContent;
    private TextView tvDate;
    private TextView tvAmount;

    public AmountPlusViewHolder(View itemView) {
        super(itemView);

        tvContent = itemView.findViewById(R.id.tvContent);
        tvDate = itemView.findViewById(R.id.tvDate);
        tvAmount = itemView.findViewById(R.id.tvAmount);
    }

    public void bindItem(Amount data) {
        tvContent.setText(data.getContent());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        tvDate.setText(sdf.format(data.getDate()));

        int amount = data.getAmount();
        DecimalFormat decimalFormat = new DecimalFormat();
        tvAmount.setText("₩" + decimalFormat.format(amount));
    }

}
