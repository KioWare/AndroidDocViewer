package org.emdev.ui.preference;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.AttributeSet;
import android.util.Log;

import com.adsi.kioware.client.mobile.pdf.app.R;

public final class AboutPreference extends Preference implements OnPreferenceClickListener {
    
    public AboutPreference(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        
        try {
            Context context = getContext();
            Intent i = new Intent(context, org.ebookdroid.ui.about.AboutActivity.class);
            context.startActivity(i);
        }
        catch(Exception e) {
            Log.e("KioWare", "", e);
            preference.setSummary(R.string.error_dlg_title);
        }
        
        return false;
    }
}
