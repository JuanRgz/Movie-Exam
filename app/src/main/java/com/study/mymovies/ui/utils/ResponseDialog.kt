package com.study.mymovies.ui.utils

import android.app.Activity
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.study.mymovies.R
import com.study.mymovies.core.App
import com.study.mymovies.databinding.DialogResponseBinding

object ResponseDialog {

    fun showDialog(
        context: Activity,
        message: String,
        success: Boolean = false,
        isCancelable: Boolean = true,
    ) {
        val dialogView = context.layoutInflater.inflate(R.layout.dialog_response, null, false)
        val dialog =
            MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme).setView(dialogView)
                .setCancelable(isCancelable)
                .show()

        val bindingDialog = DialogResponseBinding.bind(dialogView!!)

        bindingDialog.apply {
            txtDialogMessage.text = message

            if(success){
                imgDialogWarning.setImageDrawable(ContextCompat.getDrawable(App.instance, R.drawable.ic_success))
            }
            btnDialog.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}