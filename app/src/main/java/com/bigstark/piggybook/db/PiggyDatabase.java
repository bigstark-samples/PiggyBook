package com.bigstark.piggybook.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.bigstark.piggybook.PiggyApplication;

/**
 * Created by bigstark on 2017. 7. 25..
 */

@Database(entities = {Amount.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class PiggyDatabase extends RoomDatabase {

    private static PiggyDatabase instance;

    public static PiggyDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(PiggyApplication.getInstance(), PiggyDatabase.class, "piggy-db")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }


    public static AmountDao getAmountDao() {
        return getInstance().amountDao();
    }


    public abstract AmountDao amountDao();
}
