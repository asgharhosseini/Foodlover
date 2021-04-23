package ir.ah.app.foodlover.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ir.ah.app.foodlover.R

class ConfirmationPhoneNumberDialog(private val phoneNumberText:String) :DialogFragment() {
    private var yesListener: (() -> Unit)? = null

    fun setYesListener(listener: () -> Unit) {
        yesListener = listener
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_confirmation_phone_number, null, false)
        val phoneNumber = view.findViewById<TextView>(R.id.tv_ConfirmationPhoneNumberDialog_phoneNumber)
        val btnYes = view.findViewById<MaterialButton>(R.id.btnYes)
        val close = view.findViewById<MaterialButton>(R.id.btnCancel)
        phoneNumber.text=phoneNumberText

        close.setOnClickListener {
            dismiss()
        }
        btnYes.setOnClickListener {
            yesListener?.let { yes ->
                yes()
            }
        }
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setView(view)
        return builder.create()
    }

}