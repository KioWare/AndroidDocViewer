package org.emdev.common.receivers;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class DeviceAdminRcvr extends DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        DevicePolicyManager mDPM = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName mDeviceAdminRcvr = new ComponentName(context, DeviceAdminRcvr.class);
        mDPM.setPasswordQuality(mDeviceAdminRcvr, DevicePolicyManager.PASSWORD_QUALITY_SOMETHING);
    }
}
