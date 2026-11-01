package top.niunaijun.blackboxa.bridge;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.util.List;

import top.niunaijun.blackbox.BlackBoxCore;

public class AppListProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        MatrixCursor c = new MatrixCursor(new String[]{"pkg", "label", "user"});
        PackageManager pm = BlackBoxCore.getPackageManager();
        for (var u : BlackBoxCore.get().getUsers()) {
            List<ApplicationInfo> apps = BlackBoxCore.get().getInstalledApplications(0, u.id);
            for (ApplicationInfo ai : apps) {
                c.addRow(new Object[]{ai.packageName, ai.loadLabel(pm).toString(), u.id});
            }
        }
        return c;
    }

    @Override public String getType(Uri uri) { return null; }
    @Override public Uri insert(Uri uri, ContentValues values) { return null; }
    @Override public int delete(Uri uri, String s, String[] a) { return 0; }
    @Override public int update(Uri uri, ContentValues v, String s, String[] a) { return 0; }
}
