package com.tutdeputraw.todolist.database.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tutdeputraw.todolist.database.model.Task;

@Database(
        entities = {Task.class},
        version = 1,
        exportSchema = false
)
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    @VisibleForTesting
    public static final String DATABASE_NAME = "task_database";

    public abstract TaskDao taskDao();

    private void setDatabaseCreated() {//
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context) {//
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    public static TaskDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, TaskDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        TaskDatabase database = TaskDatabase.getInstance(context);
                        database.setDatabaseCreated();
                    }
                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static TaskDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (TaskDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
}
