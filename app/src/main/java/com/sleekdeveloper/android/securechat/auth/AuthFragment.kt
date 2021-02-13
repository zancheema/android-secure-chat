package com.sleekdeveloper.android.securechat.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.sleekdeveloper.android.securechat.R
import com.sleekdeveloper.android.securechat.databinding.AuthFragmentBinding
import com.sleekdeveloper.android.securechat.util.setUpSnackar

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
    }

    private fun setUpSnackbar() {
        requireView().setUpSnackar(
            viewLifecycleOwner,
            viewModel.invalidCredentialsEvent,
            Snackbar.LENGTH_SHORT
        )
    }
}