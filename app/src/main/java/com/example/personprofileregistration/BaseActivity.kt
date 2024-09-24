package com.example.personprofileregistration

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.annotations.VisibleForTesting


class BaseActivity : AppCompatActivity() {
    @VisibleForTesting
    var mProgressDialog: ProgressDialog? = null

    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setMessage("Loading ...")
            mProgressDialog!!.isIndeterminate = true
        }

        mProgressDialog!!.show()
    }


    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    public override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }
}