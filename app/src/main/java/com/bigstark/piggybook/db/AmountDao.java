package com.bigstark.piggybook.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

/**
 * Created by bigstark on 2017. 7. 25..
 */
@Dao
public interface AmountDao {

    @Query("SELECT * FROM amount")
    List<Amount> getAllAmounts();


    @Query("SELECT * FROM amount WHERE id = :id")
    Amount getAmount(int id);


    @Query("SELECT * FROM amount WHERE date BETWEEN :from AND :to")
    List<Amount> getAmounts(Date from, Date to);


    @Query("SELECT * FROM amount WHERE amount >= 0 AND date BETWEEN :from AND :to")
    List<Amount> getSavings(Date from, Date to);


    @Query("SELECT * FROM amount WHERE amount < 0 AND date BETWEEN :from AND :to")
    List<Amount> getExpenses(Date from, Date to);


    @Insert
    void insertAll(Amount... amounts);


    @Update
    void updateAll(Amount... amounts);


    @Delete
    void deleteAll(Amount... amounts);
}
