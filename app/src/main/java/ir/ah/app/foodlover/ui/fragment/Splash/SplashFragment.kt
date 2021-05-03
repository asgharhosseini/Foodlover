package ir.ah.app.foodlover.ui.fragment.Splash

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.navigation.fragment.*
import com.google.android.material.snackbar.*
import dagger.hilt.android.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.other.*
import javax.inject.*

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_spalsh) {

    @Inject
    lateinit var userInfoManager: UserInfoManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainThreadHandler = Handler(Looper.getMainLooper())
        mainThreadHandler.postDelayed({
            try {
                checkAuth()
            } catch (e: Exception) {
                Snackbar.make(requireView(), e.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        }, 2000)

    }

    private fun checkAuth() {
        val token = userInfoManager.getToken()
        if (!token.isNullOrEmpty()) {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        } else {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToIntroitFragment())
        }
    }
}