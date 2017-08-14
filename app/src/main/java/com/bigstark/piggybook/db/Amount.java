package com.bigstark.piggybook.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by bigstark on 2017. 7. 25..
 */
@Entity(tableName = "amount")
public class Amount {

    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "amount")
    private int amount;


    @ColumnInfo(name = "content")
    private String content;


    @ColumnInfo(name = "date")
    private Date date;


    @ColumnInfo(name = "category")
    private AmountCategory category;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AmountCategory getCategory() {
        return category;
    }

    public void setCategory(AmountCategory category) {
        this.category = category;
    }
}
