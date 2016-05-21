package net.kibotu.berlinplaces.misc;

import android.support.annotation.Nullable;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.text.TextUtils.isEmpty;
import static com.common.android.utils.ContextHelper.getContext;

/**
 * Created by jan.rabe on 20/05/16.
 */
public class SweetDialogExtensions {

    public static void showSuccessDialog(@Nullable final String message) {
        if (!isEmpty(message))
            new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText(message)
                    .show();
    }

    public static void showInfoDialog(@Nullable final String message) {
        if (!isEmpty(message))
            new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText(message)
                    .show();
    }

    public static void showWarningDialog(@Nullable final String message) {
        if (!isEmpty(message))
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(message)
                    .show();
    }

    public static void showDangerDialog(@Nullable final String message) {
        if (!isEmpty(message))
            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(message)
                    .show();
    }
}
