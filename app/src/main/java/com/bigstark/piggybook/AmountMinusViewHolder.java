package com.bigstark.piggybook;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigstark.piggybook.db.Amount;
import com.bigstark.piggybook.db.AmountCategory;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by bigstark on 2017. 8. 31..
 */

public class AmountMinusViewHolder extends RecyclerView.ViewHolder {

    private TextView tvContent;
    private TextView tvDate;
    private TextView tvAmount;

    private Amount data;

    public AmountMinusViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data == null) {
                    return;
                }

                Intent intent = new Intent(view.getContext(), AmountDetailActivity.class);
                intent.putExtra("amountId", data.getId());
                view.getContext().startActivity(intent);
            }
        });


        tvContent = itemView.findViewById(R.id.tvContent);
        tvDate = itemView.findViewById(R.id.tvDate);
        tvAmount = itemView.findViewById(R.id.tvAmount);
    }

    public void bindItem(Amount data) {
        this.data = data;

        tvContent.setText(data.getContent());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        tvDate.setText(sdf.format(data.getDate()));

        int amount = data.getAmount();
        DecimalFormat decimalFormat = new DecimalFormat();
        tvAmount.setText("₩" + decimalFormat.format(amount));
    }
}
