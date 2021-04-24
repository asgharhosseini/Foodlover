package ir.ah.app.foodlover.ui.fragment.auth

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.other.checkpermissions.*
import ir.ah.app.foodlover.other.checkpermissions.ConstantsPermissions.ACCESS_COARSE_LOCATION
import ir.ah.app.foodlover.other.checkpermissions.ConstantsPermissions.ACCESS_FINE_LOCATION
import kotlinx.android.synthetic.main.fragment_confirmation_code.*

class ConfirmationCode:Fragment(R.layout.fragment_confirmation_code) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        btn_Confirmation.setOnClickListener {
            if (PermissionCheck.checkPermissions(requireActivity(), intArrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))) {
                findNavController().navigate(ConfirmationCodeDirections.actionConfirmationCodeToHomeFragment())
            } else {
                findNavController().navigate(ConfirmationCodeDirections.actionConfirmationCodeToPermissionFragment())
            }

        }

    }
}