package com.test.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.lang.ref.WeakReference;

@Database(entities = {LendersDetail.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {

    public abstract LendersDetailDao dao();
    public static AppDatabase dbInstance;

    static AppDatabase getInstance(final Context context){

        if(dbInstance == null){
            synchronized (AppDatabase.class){
                if(dbInstance == null){
                    dbInstance = Room.databaseBuilder(context, AppDatabase.class, "LendersDetailDB")
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback(){
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    new populateDB(context).execute();
                                }
                            })
                            .build();
                }
            }
        }
        return dbInstance;
    }

    private static class populateDB extends AsyncTask<Void, Void, Void>{

        WeakReference<Context> context;

        public populateDB(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... v) {
            String[] name = {"George","Janet","Emma","Eve","Charles","Tracey"};
            String[] amount = {"1234","2345","3456","4567","5678","6789"};
            String[] history = {"1/5/2021","1/5/2021","1/5/2021","1/5/2021","1/5/2021","1/5/2021"};
            Boolean[] status = {true,true,true,true,true,true};

            LendersDetailDao dao = getInstance(context.get()).dao();
            for(int i = 0; i < 6; i++){
                dao.AddPerson(new LendersDetail(name[i],amount[i],history[i],status[i]));
            }
            return null;
        }
    }

}
