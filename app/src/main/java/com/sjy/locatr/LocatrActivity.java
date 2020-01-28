package com.sjy.locatr;

import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class LocatrActivity extends SingleFragmentActivity {
    private static final int REQUEST_ERROR = 0;

    @Override
    protected Fragment createFragment() {
        return LocatrFragment.newInstance();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        int errorCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if (errorCode != ConnectionResult.SUCCESS) {
            Dialog errorDialog = GoogleApiAvailability
                    .getInstance()
                    .getErrorDialog(this, errorCode, REQUEST_ERROR,
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            // Leave if services are unavailable
                            finish();
                        }
                    });
            errorDialog.show();
        }
    }
}
