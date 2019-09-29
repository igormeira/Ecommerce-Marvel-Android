package com.igormeira.comics.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.igormeira.comics.R;

public class Dialogs {

    public  Dialogs() {}

    public ProgressDialog showLoadingDialog(Context context) {
        return ProgressDialog.show(context, "",
                context.getString(R.string.loading), true);
    }

}
