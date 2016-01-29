package com.relay.app.android;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallReceiver extends BroadcastReceiver {

  private static final String TAG = "IncomingCallReceiver";

  public void onReceive(Context context, Intent intent) {
    String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
    Log.i(TAG, "Call state changed to: " + state);
    if (state != null && state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
      new RingDeviceTask().execute();
    }
  }
}
