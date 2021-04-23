package ir.ah.app.foodlover.ui.fragment.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.ah.app.foodlover.R
import kotlinx.android.synthetic.main.fragment_authentication.*

class AuthenticationFragment:Fragment(R.layout.fragment_authentication) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        btn_loginPhoneNumber.setOnClickListener {
            findNavController().navigate(AuthenticationFragmentDirections.actionAuthenticationFragmentToAuthPhoneNumberFragment())
        }
    }
}