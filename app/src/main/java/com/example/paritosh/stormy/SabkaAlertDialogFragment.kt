package com.example.paritosh.stormy

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.DialogFragment

class SabkaAlertDialogFragment : DialogFragment() {

    private val titleRes: Int by lazy { arguments!!.getInt(ARG_TITLE) }
    @StringRes private var messageRes: Int? = null

    companion object {
        private const val ARG_TITLE = "ARG_TITLE"
        private const val ARG_MESSAGE = "ARG_MESSAGE"

        fun newInstance(title: Int, message: Int): SabkaAlertDialogFragment {

            val args = Bundle()
            args.putInt(ARG_TITLE, title)
            args.putInt(ARG_MESSAGE, message)
            val fragment = SabkaAlertDialogFragment()
            fragment.arguments = args

            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messageRes = arguments!!.getInt(ARG_MESSAGE)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(activity).setTitle(titleRes)
                .setMessage(messageRes!!)
                .setPositiveButton(R.string.error_button_ok_text, null).create()

    }
}
