package com.sleekdeveloper.android.securechat.auth.verify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sleekdeveloper.android.securechat.EventObserver
import com.sleekdeveloper.android.securechat.auth.verify.VerifyCodeFragmentDirections.Companion.actionVerifyCodeFragmentToRegisterFragment
import com.sleekdeveloper.android.securechat.databinding.VerifyCodeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyCodeFragment : Fragment() {

    private val viewModel by viewModels<VerifyCodeViewModel>()
    private lateinit var viewDataBinding: VerifyCodeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = VerifyCodeFragmentBinding.inflate(inflater, container, false)
            .apply { viewmodel = viewModel }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        setUpArgs()
        setUpAuth()
    }

    private fun setUpAuth() {
        viewModel.verificationEvent.observe(viewLifecycleOwner, EventObserver { credentials ->
            Firebase.auth.signInWithCredential(credentials)
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        findNavController().navigate(actionVerifyCodeFragmentToRegisterFragment())
                    }
                }
        })
    }

    private fun setUpArgs() {
        val args = VerifyCodeFragmentArgs.fromBundle(requireArguments())
        viewModel.phoneNumber.value = args.phoneNumber
        viewModel.verificationId.value = args.verificationId
    }
}