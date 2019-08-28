package cn.wenet.networkcomponent.debug;

import android.util.Log;

public class WeDebug {

  public static  String TAG = "WeDebug";

  public static void e(String message){
      Log.e(TAG,"WeDebug: "+message+"\n");
  }

}
