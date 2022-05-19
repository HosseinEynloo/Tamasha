package com.hossein.tamasha.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.hossein.tamasha.R
import com.hossein.tamasha.databinding.FragmentRegisterBinding
import com.hossein.tamasha.models.register.BodyRegister
import com.hossein.tamasha.utils.StoreUserInfo
import com.hossein.tamasha.utils.showInvisible
import com.hossein.tamasha.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    @Inject
    lateinit var userInfo: StoreUserInfo

     val viewModel: RegisterViewModel by viewModels()

    private lateinit var binding: FragmentRegisterBinding

    @Inject
     lateinit var body: BodyRegister

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSubmit.setOnClickListener { view ->
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                // validation
                if (name.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty()) {

                    body.name = name
                    body.email = email
                    body.password = password

                    viewModel.sendRegisterInfo(body)

                    viewModel.loading.observe(viewLifecycleOwner) { isShown ->
                        if (isShown) {
                            btnSubmit.showInvisible(false)
                            submitLoading.showInvisible(true)
                        } else {
                            btnSubmit.showInvisible(true)
                            submitLoading.showInvisible(false)
                        }
                    }


                    viewModel.registerUser.observe(viewLifecycleOwner) { response ->
                        lifecycleScope.launchWhenCreated {
                            userInfo.saveUserToken(response.name.toString())
                        }
                    }


                } else {
                    Snackbar.make(view, getString(R.string.fillAllFields), Snackbar.LENGTH_SHORT)
                        .show()
                }

            }

        }
    }


}