package org.ebookdroid.ui.settings.fragments;

import com.adsi.kioware.client.mobile.pdf.app.R;

import android.annotation.TargetApi;

@TargetApi(11)
public class OpdsFragment extends BasePreferenceFragment {

    public OpdsFragment() {
        super(R.xml.fragment_opds);
    }

    @Override
    public void decorate() {
        super.decorate();
        decorator.decorateOpdsSettings();
    }

}
