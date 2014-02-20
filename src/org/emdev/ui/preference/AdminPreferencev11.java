package org.emdev.ui.preference;

import org.ebookdroid.ui.settings.SettingsActivity;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.AttributeSet;

import org.emdev.common.receivers.DeviceAdminRcvr;

public final class AdminPreferencev11 extends CheckBoxPreference implements OnPreferenceChangeListener {

    private DevicePolicyManager mDPM;
    private ComponentName mDeviceAdminRcvr;
    private Activity mActivity;
    
    public AdminPreferencev11(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        
        mActivity = (Activity) context;
        
        mDPM = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDeviceAdminRcvr = new ComponentName(context, DeviceAdminRcvr.class);
        
        setChecked(mDPM.isAdminActive(mDeviceAdminRcvr));
        setOnPreferenceChangeListener(this);
    }
    
    public void update() {
        boolean current = mDPM.isAdminActive(mDeviceAdminRcvr);
        this.setChecked(current);
    }
    
    @Override
    protected void onSetInitialValue(final boolean restoreValue, final Object defaultValue) {
        
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (newValue == null)
            return false;
        
        boolean current = mDPM.isAdminActive(mDeviceAdminRcvr);
        boolean now = (Boolean) newValue;
        if (current == now)
            return true;
        else if (now) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminRcvr);
            mActivity.startActivityForResult(intent, SettingsActivity.ADMIN_RESULT);
        }
        else
            mDPM.removeActiveAdmin(mDeviceAdminRcvr);
        
        return true;
    }
}
