package com.igormeira.comics.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.igormeira.comics.R;

/**
 * Classe responsável por invocar dialogs
 */
public class Dialogs {

    /**
     * Invoca dialog de loading
     *
     * @param context
     * @return ProgressDialog
     */
    public ProgressDialog showLoadingDialog(Context context) {
        return ProgressDialog.show(context, "",
                context.getString(R.string.loading), true);
    }

}
