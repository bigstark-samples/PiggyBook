package com.bigstark.piggybook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigstark.piggybook.db.Amount;
import com.bigstark.piggybook.db.AmountCategory;
import com.bigstark.piggybook.db.PiggyDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class AddAmountActivity extends AppCompatActivity {
    private final String[] categories = {"수입", "지출"};
    private final AmountCategory[] amountCategories = {AmountCategory.INCOME, AmountCategory.OUTCOME};

    private Amount amount = new Amount();

    private TextView tvCategory;
    private TextView tvDate;

    private TextInputLayout tilAmount;
    private TextInputLayout tilMemo;

    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_amount);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvCategory = findViewById(R.id.tvCategory);
        tvDate = findViewById(R.id.tvDate);

        CardView cvCategory = findViewById(R.id.cvCategory);
        cvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCategoryDialog();
            }
        });

        CardView cvDate = findViewById(R.id.cvDate);
        cvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        tilAmount = findViewById(R.id.tilAmount);
        tilMemo = findViewById(R.id.tilMemo);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtAmount = tilAmount.getEditText().getText().toString();
                String txtMemo = tilMemo.getEditText().getText().toString();

                if (TextUtils.isEmpty(txtAmount)) {
                    Toast.makeText(AddAmountActivity.this, "금액을 적어주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int money = Integer.parseInt(txtAmount);
                amount.setAmount(money);
                amount.setContent(txtMemo);

                PiggyDatabase.getInstance().amountDao().insertAll(amount);
                Toast.makeText(AddAmountActivity.this, "저장 완료했습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void showCategoryDialog() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice);
        adapter.addAll(categories);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tvCategory.setText(categories[i]);
                        amount.setCategory(amountCategories[i]);
                    }
                })
                .create();
        dialog.show();
    }


    private int year, month, date, hour, minute;
    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                year = i;
                month = i1;
                date = i2;

                showTimeDialog();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        dialog.show();
    }

    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hour = i;
                minute = i1;

                tvDate.setText(String.format("%02d월 %02d일 %02d시 %02d분", month + 1, date, hour, minute));
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date payTime = format.parse(String.format("%04d-%02d-%02d %02d:%02d", year, month + 1, date, hour, minute));
                    amount.setDate(payTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        dialog.show();

    }
}
