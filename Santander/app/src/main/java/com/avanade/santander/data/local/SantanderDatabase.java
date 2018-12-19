package com.avanade.santander.data.local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.avanade.santander.contato.domain.model.Cell;

/**
 * The Room Database that contains Tables.
 */
@Database(entities = {Cell.class}, version = 1, exportSchema = false)
public abstract class SantanderDatabase extends RoomDatabase {

    private static SantanderDatabase INSTANCE;

    public abstract CellsDao contatoDao();

    private static final Object sLock = new Object();

    public static SantanderDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        SantanderDatabase.class,
                        "Santander.db"
                ).build();
            }
            return INSTANCE;
        }
    }

}
