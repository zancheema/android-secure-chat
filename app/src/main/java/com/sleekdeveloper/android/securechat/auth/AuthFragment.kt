package com.sleekdeveloper.android.securechat.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sleekdeveloper.android.securechat.EventObserver
import com.sleekdeveloper.android.securechat.auth.AuthFragmentDirections.Companion.actionAuthFragmentToRegisterFragment
import com.sleekdeveloper.android.securechat.auth.AuthFragmentDirections.Companion.actionAuthFragmentToVerifyCodeFragment
import com.sleekdeveloper.android.securechat.databinding.AuthFragmentBinding
import com.sleekdeveloper.android.securechat.util.setUpSnackar
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private val viewModel by viewModels<AuthViewModel>()

    private lateinit var viewDataBinding: AuthFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = AuthFragmentBinding.inflate(inflater, container, false)
            .apply { viewmodel = viewModel }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        setUpSnackbar()
        setUpVerification()
    }

    private fun setUpVerification() {
        viewModel.signInEvent.observe(viewLifecycleOwner, EventObserver { phoneNumber ->
            verifyPhoneNumber(phoneNumber)
        })
    }

    private fun setUpSnackbar() {
        requireView().setUpSnackar(
            viewLifecycleOwner,
            viewModel.invalidCredentialsEvent,
            Snackbar.LENGTH_SHORT
        )
    }

    private fun verifyPhoneNumber(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(30L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onCodeSent(
                    verificationId: String,
                    forceResendingToken: PhoneAuthProvider.ForceResendingToken
                ) {
                    val action = actionAuthFragmentToVerifyCodeFragment(
                        phoneNumber,
                        verificationId
                    )
                    findNavController().navigate(action)
                }

                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    Firebase.auth.signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                findNavController().navigate(actionAuthFragmentToRegisterFragment())
                            }
                        }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    TODO("Not implemented yet")
                }

                override fun onCodeAutoRetrievalTimeOut(p0: String) {
                    super.onCodeAutoRetrievalTimeOut(p0)
                    TODO("Not implemented yet")
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}