package com.bigstark.piggybook;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigstark.piggybook.db.Amount;
import com.bigstark.piggybook.db.AmountCategory;
import com.bigstark.piggybook.db.PiggyDatabase;

import java.util.List;

/**
 * Created by bigstark on 2017. 8. 25..
 */

public class AmountAdapter extends RecyclerView.Adapter {

    private final int TYPE_PLUS = 0;
    private final int TYPE_MINUS = 1;

    private List<Amount> amounts;


    public AmountAdapter() {
        amounts = PiggyDatabase.getAmountDao().getAllAmounts();
    }


    public void update() {
        amounts = PiggyDatabase.getAmountDao().getAllAmounts();
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        if (viewType == TYPE_PLUS) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_amount_plus, parent, false);
            return new AmountPlusViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_amount_minus, parent, false);
            return new AmountMinusViewHolder(itemView);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_PLUS) {
            ((AmountPlusViewHolder) holder).bindItem(amounts.get(position));
        } else {
            ((AmountMinusViewHolder) holder).bindItem(amounts.get(position));
        }
    }



    @Override
    public int getItemCount() {
        return amounts.size();
    }

    @Override
    public int getItemViewType(int position) {
        Amount amount = amounts.get(position);
        if (amount.getCategory() == AmountCategory.INCOME) {
            return TYPE_PLUS;
        } else {
            return TYPE_MINUS;
        }
    }
}
