//package com.avanade.santander.data.local.db;
//
//
//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.content.Context;
//
//import com.avanade.santander.contato.domain.model.Cell;
//import com.avanade.santander.data.local.dao.ContatoDao;
//import com.avanade.santander.data.local.dao.FundosDao;
//import com.avanade.santander.fundos.domain.model.Screen;
//
///**
// * The Room Database that contains the Fundos table and Cells Table.
// */
//@Database(entities = {Screen.class, Cell.class}, version = 1)
//public abstract class SantanderDatabase extends RoomDatabase {
//
//    private static SantanderDatabase INSTANCE;
//
//    public abstract FundosDao fundosDao();
//
//    public abstract ContatoDao contatoDao();
//
//    private static final Object sLock = new Object();
//
//    public static SantanderDatabase getInstance(Context context) {
//        synchronized (sLock) {
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(
//                        context.getApplicationContext(),
//                        SantanderDatabase.class,
//                        "Santander.db"
//                ).build();
//            }
//            return INSTANCE;
//        }
//    }
//
//}
