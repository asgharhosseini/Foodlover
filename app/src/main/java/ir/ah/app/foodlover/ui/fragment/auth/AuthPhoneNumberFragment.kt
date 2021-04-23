package ir.ah.app.foodlover.ui.fragment.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.ui.dialog.ConfirmationPhoneNumberDialog
import kotlinx.android.synthetic.main.fragment_auth_phonenumber.*

class AuthPhoneNumberFragment:Fragment(R.layout.fragment_auth_phonenumber) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        btn_loginPhoneNumber.setOnClickListener {
            val dialog =ConfirmationPhoneNumberDialog(edt_phoneText.text.toString().trim())
            dialog.isCancelable = false
            dialog.setYesListener {
                dialog.dismiss()
                findNavController().navigate(AuthPhoneNumberFragmentDirections.actionAuthPhoneNumberFragmentToConfirmationCode())

            }
            dialog.show(parentFragmentManager,null)
        }
    }
}