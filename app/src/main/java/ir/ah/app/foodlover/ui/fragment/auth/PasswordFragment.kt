package ir.ah.app.foodlover.ui.fragment.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.other.checkpermissions.ConstantsPermissions
import ir.ah.app.foodlover.other.checkpermissions.ConstantsPermissions.ACCESS_COARSE_LOCATION
import ir.ah.app.foodlover.other.checkpermissions.ConstantsPermissions.ACCESS_FINE_LOCATION
import ir.ah.app.foodlover.other.checkpermissions.PermissionCheck
import ir.ah.app.foodlover.ui.fragment.permission.PermissionFragmentDirections
import kotlinx.android.synthetic.main.fragment_password.*

class PasswordFragment:Fragment(R.layout.fragment_password) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        btn_loginPhoneNumber.setOnClickListener {
            if (PermissionCheck.checkPermissions(requireActivity(), intArrayOf(ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION))) {
                findNavController().navigate(PasswordFragmentDirections.actionPasswordFragmentToHomeFragment())
            }else{
                findNavController().navigate(PasswordFragmentDirections.actionPasswordFragmentToPermissionFragment())
            }
        }
    }
}