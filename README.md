# android 信息收集app

> 最近一个新作业要做一个app，用来收集系统内信息、其他app信息、用户信息等（如机器上运行的程序等）

## 环境配置

- 环境配置一直是老大难，同组的两个同学纷纷挂在配置环境上，然后，由于电脑上本身就有IDEA，就直接下载SDK就可以嘞。

- 在网上找了大佬们分享的帖子：

  -  2018年Android SDK下载安装及配置教程

    https://blog.csdn.net/qq_36577136/article/details/80632674

  - 最新鲜最详细的Android SDK下载安装及配置教程

    https://www.cnblogs.com/summary-2017/p/8073225.html

  - IDEA配置Android SDK

    https://blog.csdn.net/github_26672553/article/details/72821477

  - IntelliJ IDEA配置JDK和Android（安卓）SDK

    https://blog.csdn.net/Brave_insist/article/details/81041004

- **总的来说还是很顺利的，就是SDK一开始下载的时候没有用代理，然后下的巨慢无比，加了代理之后还是很快的。**

- 配置环境变量快速生效的话，就用命令行敲一下下边这个就OK

  ```
  echo %PATH%
  ```

- Failed to find Build Tools revision 27.0.3 错误处理

  https://blog.csdn.net/xudailong_blog/article/details/84756945

- Gradle永远下载“builder-3.1.2.jar”？错误处理，一个是因为网不行，另外可以试试这个链接里边的方法

  https://cloud.tencent.com/developer/ask/177770

- Error:Unable to tunnel through proxy. Proxy returns "HTTP/1.1 400 Bad Request"的解决办法

  https://blog.csdn.net/e_one/article/details/51046313

- Error:Failed to find target with hash string 'android-27'

  https://blog.csdn.net/yushuangping/article/details/81362512

- PANIC: Missing emulator engine program for 'x86' CPU.

  https://blog.csdn.net/tao_sheng_yi_jiu/article/details/80469426

-  解决 PANIC：Missing emulator engine program for 'x86' CPU 错误

  https://blog.csdn.net/SDF_crazy/article/details/85228452
  

-  报错 Error:Unsupported method: BaseConfig.getApplicationIdSuffix().

  https://www.cnblogs.com/wobuyayi/p/8617774.html

- 出现Failed to open zip file问题的解决方法

  https://www.cnblogs.com/Sharon2017/p/7810774.html



- 配置的时候需要下载很多包，加上代理设置还是很慢的话建议本机下载，就是将需要的包直接copy到网址框中下载，会快很多。
- 链接：https://pan.baidu.com/s/15jgUD0Q6yFN02palYqN4Ug 
  提取码：12gi 
- 所有的我下载的东西都在网盘里边了。

## 需求

- 实际上就是简单的获取android的系统信息，app的所有信息、以及进程信息就可以了。
- android上有现成的包可以用，如果不做前端的，直接设置一个textview就全都解决了。

```
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
    }
}
```



## 运行结果截图

![1555750792395](C:\Users\varas\AppData\Roaming\Typora\typora-user-images\1555750792395.png)