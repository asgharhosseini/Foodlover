package ir.ah.app.foodlover.ui.fragment.auth

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import com.google.android.material.snackbar.*
import com.google.firebase.auth.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.other.*
import ir.ah.app.foodlover.other.checkpermissions.*
import kotlinx.android.synthetic.main.fragment_auth_email.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.*
import javax.inject.*

@AndroidEntryPoint
class AuthEmailFragment : Fragment(R.layout.fragment_auth_email) {

    @Inject
    lateinit var userInfoManager: UserInfoManager

    private var isRegistered = false

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        onClick()


    }

    private fun onClick() {
        txt_login.setOnClickListener {
            if (!isRegistered) {
                isRegistered = true
                btn_authEmail.text = "ورود"
            } else {
                isRegistered = false
                btn_authEmail.text = "ثبت نام"
            }


        }
        btn_authEmail.setOnClickListener {
            val email = edt_EmailText.text.toString()
            val password = edt_password.text.toString()
            authUser(email, password)
        }


    }

    private fun authUser(email: String, password: String) {
        if (!isRegistered) {
            registerUser(email, password)
        } else {
            loginUser(email, password)

        }

    }

    private fun registerUser(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        isRegistered = true
                        userInfoManager.clear()
                        userInfoManager.saveUser(email = auth.currentUser.email, token = auth.currentUser.uid)
                        checkLoggedInstance(email, password)
                    }


                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Snackbar.make(requireView(), e.message.toString(), Snackbar.LENGTH_LONG)
                                .show()
                    }
                }
            }
        }

    }

    private fun loginUser(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        userInfoManager.clear()
                        userInfoManager.saveUser(auth.currentUser.email, auth.currentUser.uid)
                        checkPermissionAndNavigate()
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Snackbar.make(requireView(), e.message.toString(), Snackbar.LENGTH_LONG)
                                .show()
                    }
                }
            }
        }

    }

    private fun checkLoggedInstance(email: String, password: String) {
        if (auth.currentUser == null) {
            btn_authEmail.text = "ثبت نام"

        } else {
            btn_authEmail.text = "ورود"
            checkPermissionAndNavigate()

        }
    }

    private fun checkPermissionAndNavigate() {
        if (PermissionCheck.checkPermissions(
                        requireActivity(), intArrayOf(
                        ConstantsPermissions.ACCESS_FINE_LOCATION,
                        ConstantsPermissions.ACCESS_COARSE_LOCATION
                )
                )
        ) {
            findNavController().navigate(AuthEmailFragmentDirections.actionAuthPhoneNumberFragmentToHomeFragment())

        } else {
            findNavController().navigate(AuthEmailFragmentDirections.actionAuthPhoneNumberFragmentToPermissionFragment())
        }
    }


}