##介绍
>自己封装的工具类，为了以后方便工程使用

## 引入

* Gradle 
   
   ```
  compile 'com.zm.commontool:commontool:1.0.6'
   ```
* Maven
	
	```
	<dependency>
      <groupId>com.zm.commontool</groupId>
      <artifactId>commontool</artifactId>
      <version>1.0.6</version>
      <type>pom</type>
    </dependency>
	
	```




##common 具备功能
        2.下载安装apk兼容7.0
        3.rxpermissions2动态权限管理(0.9.4)
        4.自己定义的Logger日志打印
        5.jaskjson json解析
        6.glide网络图片请求（3.6.1）
        7.LrecyclerView列表控件(1.4.3)
        8.引用了rxandroid2.0.1 
        9.rxjava2.0.5
        10.日期管理类
        11.空字符判断工具类
        12.获得MD5工具类 
        
## 需要权限
      <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /><!--写存储卡-->
      <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" /><!--读存储卡-->
      <uses-permission android:name="android.permission.CAMERA" /><!--打开摄像头-->
        
## 具体用法请查看DEMO
>[demo](https://github.com/scalling/commontool/tree/master/app)
        