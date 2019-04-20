package com.example.varas.sunapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import android.widget.TextView;

import java.io.File;

import java.util.List;

/* group number : 刘益铭、简鲲鹏、安巡、孙晴、韩瑶鹏、陈家赟

 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        int currentVersion = Build.VERSION.SDK_INT;
        TextView sysinfo=(TextView)findViewById(R.id.sysinfo);

        TextView sdk_version=(TextView)findViewById(R.id.sdk_version);
        sdk_version.setText( String.valueOf(currentVersion) );
        TextView release_version=(TextView)findViewById(R.id.release_version);
        release_version.setText( Build.VERSION.RELEASE );
        TextView hardware=(TextView)findViewById(R.id.hardware);
        hardware.setText( Build.HARDWARE );
        TextView manufacturer=(TextView)findViewById(R.id.manufacturer);
        manufacturer.setText( Build.MANUFACTURER );
        TextView CPU_ABI=(TextView)findViewById(R.id.CPU_ABI);
        CPU_ABI.setText( Build.CPU_ABI );
        TextView security_patch=(TextView)findViewById(R.id.security_patch);
        security_patch.setText( Build.VERSION.SECURITY_PATCH );
        TextView os_version=(TextView)findViewById(R.id.os_version);
        os_version.setText( System.getProperty("os.version") );
        TextView opengl_version=(TextView)findViewById(R.id.opengl_version);
        opengl_version.setText( ((ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE)).getDeviceConfigurationInfo().getGlEsVersion() );
        //String appName=(String) this.getResources().getText(R.id.sdk_version);

        //TextView sysinString appName=(String) this.getResources().getText(R.string.app_name); fo=(TextView)findViewById( R.id.all ) ;
//        String s = "-------------------系统信息-------------------\n";
//        s += "SDK版本：";
//        s = s + String.valueOf(currentVersion) + "\n";
//        s = s + "Release版本： " + Build.VERSION.RELEASE + '\n';
//        s = s + "平台信息：" + Build.HARDWARE + "\n";
//        s = s + "硬件制造商：" + Build.MANUFACTURER + "\n";
//        s = s + "指令集：" +  Build.CPU_ABI + "\n";
//        s = s + "安全补丁：" +  Build.VERSION.SECURITY_PATCH  + "\n";
//        s = s + "内核版本：" +  System.getProperty("os.version") + "\n";
//        s = s + "OpenGL版本：" +  ((ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE)).getDeviceConfigurationInfo().getGlEsVersion() + "\n\n";
//        s = s + "SDK info: " + Build.VERSION.SDK + "\n";
//        s = s + "CODENAME: " + Build.VERSION.CODENAME + "\n";

        String s = "";
        PackageManager pm = this.getPackageManager();
        // 查询所有已经安装的应用程序
        List<ApplicationInfo> listAppcations = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (ApplicationInfo app : listAppcations) {
            // 如果该包名存在 则构造一个RunningAppInfo对象
//            if (pgkProcessAppMap.containsKey(app.packageName)) {
            // 获得该packageName的 pid 和 processName
//                int pid = pgkProcessAppMap.get(app.packageName).pid;
            String processName = app.packageName;
            String name = app.loadLabel(getPackageManager()).toString();
            if ( name != processName ){
                s = s + name + ":" + '\n';
                s = s + "DataDir:  " + app.dataDir + '\n';
                s = s + "PName:  " + processName + '\n' ;
                s = s + "应用存放数据目录:  " + app.sourceDir + '\n';
                s = s + "FLAGS: " + app.flags + '\n';
                long length = new File(app.sourceDir).length();
                //转换为 M
                float size = length*1f/1024/1024;
                s = s + "Size:  " +  size + "M" + '\n';

                s = s + '\n';

            }

//                runningAppInfos.add(getAppInfo(app, pid, processName));
//            }
        }

        //s = s + "\n-------------------进程列表-------------------\n";
        String process_info = "";
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcessList) {
            int pid = appProcess.pid; // pid
            String processName = appProcess.processName; // 进程名
            //Log.i(TAG, "processName: " + processName + "  pid: " + pid);
            process_info = process_info + "进程: " + processName + "  pid: " + pid + "\n";
            String[] pkgNameList = appProcess.pkgList; // 获得运行在该进程里的所有应用程序包
        }

        sysinfo.setText(s);
        TextView processinfo=(TextView)findViewById(R.id.processinfo);
        processinfo.setText(process_info);

        System.out.println( s );

//
//        String PATH = "";
//        String NAME = "sun.txt";
//        FileOutputStream fileOutputStream;
//        try {
//            File file = new File(PATH, NAME);
//            if (file == null || !file.exists()) {
//                file.getParentFile().mkdirs();
//                file.createNewFile();
//            }
//            fileOutputStream = new FileOutputStream(file, true);
//
//            String result = s;
//            fileOutputStream.write(result.getBytes());
//            fileOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }




    }

}
