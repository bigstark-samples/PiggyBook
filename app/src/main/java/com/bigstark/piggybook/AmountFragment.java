package com.bigstark.piggybook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bigstark on 2017. 7. 25..
 */

public class AmountFragment extends Fragment implements View.OnClickListener {

    public static final int REQUEST_ADD = 1000;

    private AmountAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_amount, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(this);

        RecyclerView rvAmount = view.findViewById(R.id.rvAmount);
        rvAmount.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new AmountAdapter();
        rvAmount.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.floatingActionButton:
                Intent intent = new Intent(getContext(), AddAmountActivity.class);
                startActivityForResult(intent, REQUEST_ADD);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD && resultCode == Activity.RESULT_OK) {
            adapter.update();
        }


    }
}
