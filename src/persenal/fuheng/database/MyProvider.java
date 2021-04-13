package com.example.testh264.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testh264.sqlite.SQLiteUtils;
import com.example.testh264.sqlite.TestSQLiteOpenHelper;

public class MyProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.test_content_provider";

    private static final Uri sUri = Uri.parse(AUTHORITY);
    // UriMatcher类使用:在ContentProvider 中注册URI
    private static final UriMatcher mMatcher;
    public static final int TEST_OBJ = 1;
    public static final int Job_Code = 2;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化
        mMatcher.addURI(AUTHORITY, "user", TEST_OBJ);
        mMatcher.addURI(AUTHORITY, "job", Job_Code);
        // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
        // 若URI资源路径 = content://cn.scu.myprovider/job ，则返回注册码Job_Code
    }

    private TestSQLiteOpenHelper mSQLiteHelper;

    @Override
    public boolean onCreate() {
        mSQLiteHelper = new TestSQLiteOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        String tableName = getTable(uri);
        if (tableName == null)
            return null;
        SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
        db.insert(tableName, null, values);
        db.close();
        return null;
    }

    private String getTable(Uri uri) {

        switch (mMatcher.match(uri)) {
            case TEST_OBJ:
                return SQLiteUtils.getTableName(TestObject.class);
            default:
                return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
