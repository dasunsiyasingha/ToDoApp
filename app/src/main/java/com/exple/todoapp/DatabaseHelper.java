package com.exple.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databasename = "User_db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table allusers(id integer primary key AUTOINCREMENT," +
                " username TEXT UNIQUE," +
                " nickname TEXT," +
                " birthday TEXT," +
                " password TEXT," +
                " email TEXT)");

        db.execSQL("create table usertask(user_id integer, " +
                "task_id integer, " +
                "FOREIGN KEY(user_id) REFERENCES allusers(id)," +
                "FOREIGN KEY(task_id) REFERENCES task(task_id))");

        db.execSQL("create table task(task_id integer primary key," +
                " title TEXT," +
                " description TEXT," +
                " location TEXT," +
                " status TEXT)" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists allusers");
        db.execSQL("drop table if exists task");
    }

    // REGISTER USAGE

    public boolean insertDatauser(String username, String nickname, String birthday, String password, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("nickname", nickname);
        contentValues.put("birthday", birthday);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long result = db.insert("allusers", null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from allusers where username = ?", new String[]{username});
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    //END REGISTER USAGE


    //LOGIN USAGE
    public boolean checusernamepswd(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from allusers where username = ? and password = ? ", new String[]{username, password} );

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public int getuserid(String username){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select id from allusers where username = ?", new String[] {username});
        int id = 0;
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
        }

        return id;
    }

    // END LOGIN USAGE

    //USER PROFILE USAGE

    protected HashMap<String, String> getuserinfo(int usrid){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select username, nickname, birthday, email from allusers where id = ?", new String[] {String.valueOf(usrid)});

        HashMap<String, String> userinfo = new HashMap<String, String>();
        while (cursor.moveToNext()){
            userinfo.put("username", cursor.getString(0));
            userinfo.put("nickname", cursor.getString(1));
            userinfo.put("birthday",cursor.getString(2));
            userinfo.put("email",cursor.getString(3));
        }

        return userinfo;
//        Cursor cursor = db.rawQuery("select * from allusers where username = ?", new String[] {dbusername});
//
//        ArrayList<ModelUserDetail> userinfo = new ArrayList<>();
//        while(cursor.moveToNext()){
//            ModelUserDetail modelUserDetail = new ModelUserDetail();
//        }

        }

    //END USER PROFILE USAGE

    //TASK ADD USAGE

    String lasttaskid(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT task_id FROM task", null);
        String lastId="";
        while(cursor.moveToNext()){
            lastId = cursor.getString(0);
        }
        if(lastId == ""){
            return "0";
        }else{
            return lastId;
        }

    }

    boolean taskAdd(int taskid, String title, String desc, String location, String status, int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("task_id", taskid);
        contentValues.put("title", title);
        contentValues.put("description", desc);
        contentValues.put("location", location);
        contentValues.put("status", status);
        long result = db.insert("task", null, contentValues);

        ContentValues contentValuesusertask = new ContentValues();
        contentValuesusertask.put("user_id", userid);
        contentValuesusertask.put("task_id", taskid);
        long result2 = db.insert("usertask", null,contentValuesusertask);

        if(result == -1 || result2 == -1){
            return false;
        }else{
            return true;
        }
    }

    //END TASK ADD USAGE



    //TASK GET USAGE

    public List<Work> gettasks(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT task.task_id, title, description, location, status FROM task JOIN usertask ON task.task_id = usertask.task_id JOIN allusers ON usertask.user_id = allusers.id WHERE id = ?", new String[]{String.valueOf(userid)});
        List<Work> workarraylist = new ArrayList<>();
        while (cursor.moveToNext()){
            Work work1 = new Work();
            work1.taskid = cursor.getString(0);
            work1.title = cursor.getString(1);
            work1.description = cursor.getString(2);
            work1.location = cursor.getString(3);
            work1.status = cursor.getString(4);
            workarraylist.add(work1);
        }
        return workarraylist;
    }

    //END TASK GET USAGE

    //UPDATE STATUS
    String updateStatus(String status, String userid, String taskid){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuary = "SELECT task.task_id FROM task JOIN usertask ON task.task_id = usertask.task_id JOIN allusers ON usertask.user_id = allusers.id WHERE id = ? AND task.task_id = ?";
        Cursor cursor = db.rawQuery(selectQuary, new String[]{userid, taskid});
        cursor.moveToLast();
        String verifyed_taskid = cursor.getString(0);
        String updateQuery = "UPDATE task " +
                "SET status = ?" +
                "WHERE task_id = ?";
        db.execSQL(updateQuery, new String[] {status, verifyed_taskid});

        return status;
    }
    //END UPDATE STATUS

    String deleteTask(String taskid){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM task WHERE task_id = ?";
        String deleteusertask = "DELETE FROM usertask WHERE task_id = ?";
        db.execSQL(deleteQuery, new String[] {taskid});
        db.execSQL(deleteusertask, new String[]{taskid});
        return "Deleted";
    }

    float taskCompletePrecentage(String userid){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectcomplete = "SELECT task.task_id FROM task JOIN usertask ON task.task_id = usertask.task_id JOIN allusers ON usertask.user_id = allusers.id WHERE id = ? AND task.status = ?";
        Cursor compltd_tasks = db.rawQuery(selectcomplete, new String[]{userid, "Completed"});
        float compltdtask_count = compltd_tasks.getCount();

        String selectall = "SELECT task.task_id FROM task JOIN usertask ON task.task_id = usertask.task_id JOIN allusers ON usertask.user_id = allusers.id WHERE id = ?";
        Cursor all_tasks = db.rawQuery(selectall, new String[]{userid});
        float alltask_count = all_tasks.getCount();

        float precentage = (compltdtask_count/alltask_count)*100;
        return precentage;

    }



    //UPDATE USER INFO
    String updateuserinfo(String id, String nickname, String bd, String email){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nickname",nickname);
        cv.put("birthday",bd);
        cv.put("email",email);

        int result = db.update("allusers", cv, "id = ?", new String[]{id} );
        if (result > 0){
            return "Your details Updated";
        }else{
            return "Not updated. Try again..";
        }

    }
    //END USER INFO






}

