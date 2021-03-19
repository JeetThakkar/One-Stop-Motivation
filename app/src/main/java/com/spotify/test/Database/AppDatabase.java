package com.spotify.test.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {PersonDetails.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {

    public abstract PersonDetailsDao dao();
    public static AppDatabase dbInstance;

    static AppDatabase getInstance(final Context context){

        if(dbInstance == null){
            synchronized (AppDatabase.class){
                if(dbInstance == null){
                    dbInstance = Room.databaseBuilder(context, AppDatabase.class, "PersonInfoDB")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback(){
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    new populateDB( context).execute();
                                }
                            })
                            .build();
                }
            }
        }
        return dbInstance;
    }

    private static class populateDB extends AsyncTask<Void, Void, Void>{

        Context context;

        public populateDB(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... v) {
            String fname[] = {"George","Janet","Emma","Eve","Charles","Tracey"};
            String lname[] = {"Bluth","Weaver","Wong","Holt","Morris","Ramos"};
            String email[] = {"george.bluth@reqres.in","janet.weaver@reqres.in","emma.wong@reqres.in","eve.holt@reqres.in","charles.morris@reqres.in","tracey.ramos@reqres.in"};
            PersonDetailsDao dao = getInstance(context).dao();
            for(int i = 0; i < 6; i++){
                dao.AddPerson(new PersonDetails(fname[i],lname[i],email[i]));
            }
            return null;
        }
    }

}
