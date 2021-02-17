package com.sleekdeveloper.android.securechat.auth.verify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
    }

    private fun setUpArgs() {
        val args = VerifyCodeFragmentArgs.fromBundle(requireArguments())
        viewModel.phoneNumber.value = args.phoneNumber
    }
}