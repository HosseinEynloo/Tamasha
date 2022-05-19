package com.hossein.tamasha.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.hossein.tamasha.R
import com.hossein.tamasha.databinding.FragmentSplashBinding
import com.hossein.tamasha.utils.Constants
import com.hossein.tamasha.utils.StoreUserInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var storeUserInfo: StoreUserInfo

    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set delay
        lifecycle.coroutineScope.launchWhenCreated {
            delay(Constants.SPLASH_DELAY)
            //check user token
            storeUserInfo.getUserToken().collect {
                if (it.isEmpty()) {
                    findNavController().navigate(R.id.actionSplashTORegister)
                } else {
                    findNavController().navigate(R.id.actionToHome)
                }

            }
        }

    }


}