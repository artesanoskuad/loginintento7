package com.artesanoskuad.loginintento7.authentication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.artesanoskuad.loginintento7.R
import com.artesanoskuad.loginintento7.authentication.presentation.LoginViewModel
import com.artesanoskuad.loginintento7.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var rawBinding: FragmentLoginBinding? = null
    private val binding get() = rawBinding!!
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rawBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
        setupViewModel()
    }

    private fun setupViewModel() {
        loginViewModel.state().observe(this) {
            it?.let { safeState ->
                renderUI(safeState)
            }
        }
    }

    private fun renderUI(safeState: String) {
        Toast.makeText(requireContext(), safeState, Toast.LENGTH_LONG).show()
    }

    private fun setupClickListener() {
        binding.btnLogin.setOnClickListener {
            loginViewModel.login(getUser(), getPassword())
        }

        binding.btnRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }
    }

    private fun getUser() = binding.etUsuario.text.toString()
    private fun getPassword() = binding.etPassword.text.toString()

}
