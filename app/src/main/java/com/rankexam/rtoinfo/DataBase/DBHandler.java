package com.rankexam.rtoinfo.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rankexam.rtoinfo.Extra.QueConstructor;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db;
    ArrayList<QueConstructor> quesList;

    public DBHandler(Context context) {
        super(context, "RTODATABASE", (SQLiteDatabase.CursorFactory) null, 3);
        this.quesList = new ArrayList<>();
        this.db = getWritableDatabase();
        Log.d("TT", "DB CREATED ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE  TABLE RTODATABASETABLE (ID INTEGER PRIMARY KEY,QUESTION TEXT,ANSWER TEXT)");
        sQLiteDatabase.execSQL("CREATE TABLE QuesImg(id INTEGER PRIMARY KEY , question TEXT, answer TEXT  , option1 TEXT,option2 TEXT,option3 TEXT,photo TEXT )");
        Log.d("TT", "TABLE CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS RTODATABASETABLE");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS QuesImg");
        Log.d("TT", "TABLE DROPPED");
        onCreate(sQLiteDatabase);
    }

    public int rowCount() {
        return this.db.rawQuery("Select * From QuesImg", null).getCount();
    }

    public void addQuestion(int i, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", Integer.valueOf(i));
        contentValues.put("QUESTION", str);
        contentValues.put("ANSWER", str2);
        if (this.db.insert("RTODATABASETABLE", null, contentValues) < 0) {
            Log.d("TT", "INSERT ISSUE");
        } else {
            Log.d("TT", "INSERT SUCCESS");
        }
    }

    public ArrayList getAllQuestions() {
        ArrayList arrayList = new ArrayList();
        Cursor query = this.db.query("RTODATABASETABLE", null, null, null, null, null, null);
        query.moveToFirst();
        if (query.getCount() <= 0) {
            return arrayList;
        }
        do {
            int i = query.getInt(0);
            String string = query.getString(1);
            String string2 = query.getString(2);
            QueConstructor m_rtoQueConstructor = new QueConstructor();
            m_rtoQueConstructor.setId(i);
            m_rtoQueConstructor.setQuestion(string);
            m_rtoQueConstructor.setAnswer(string2);
            arrayList.add(m_rtoQueConstructor);
        } while (query.moveToNext());
        return arrayList;
    }

    public void addQuestion(QueConstructor m_rtoQueConstructor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(m_rtoQueConstructor.getId()));
        contentValues.put("question", m_rtoQueConstructor.getQuestion());
        contentValues.put("answer", m_rtoQueConstructor.getAnswer());
        contentValues.put("option1", m_rtoQueConstructor.getOption1());
        contentValues.put("option2", m_rtoQueConstructor.getOption2());
        contentValues.put("option3", m_rtoQueConstructor.getOption3());
        contentValues.put("photo", m_rtoQueConstructor.getPhoto());
        this.db.insert("QuesImg", null, contentValues);
    }

    public ArrayList<QueConstructor> getAllQuestions2() {
        Cursor query = this.db.query("QuesImg", null, null, null, null, null, null);
        query.moveToFirst();
        if (query.getCount() <= 0) {
            return this.quesList;
        }
        do {
            this.quesList.add(new QueConstructor(query.getInt(0), query.getString(1), query.getString(2), query.getString(3), query.getString(4), query.getString(5), query.getString(6)));
        } while (query.moveToNext());
        return this.quesList;
    }

    public void deleteTable() {
        SQLiteDatabase sQLiteDatabase = this.db;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            this.db = getWritableDatabase();
        }
        this.db.execSQL("DROP TABLE IF EXISTS CARS");
    }

    public DBHandler open() {
        this.db = getWritableDatabase();
        return this;
    }
}
