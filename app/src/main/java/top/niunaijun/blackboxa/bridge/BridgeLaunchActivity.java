package top.niunaijun.blackboxa.bridge;

import android.app.Activity;
import android.os.Bundle;

import top.niunaijun.blackbox.BlackBoxCore;

public class BridgeLaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String pkg = getIntent().getStringExtra("pkg");
        int userId = getIntent().getIntExtra("user", 0);
        if (pkg != null) {
            BlackBoxCore.get().launchApk(pkg, userId);
        }
        finish();
    }
}
