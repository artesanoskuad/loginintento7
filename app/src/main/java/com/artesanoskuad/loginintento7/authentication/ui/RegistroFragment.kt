package com.artesanoskuad.loginintento7.authentication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.artesanoskuad.loginintento7.authentication.presentation.LoginViewModel
import com.artesanoskuad.loginintento7.databinding.FragmentLoginBinding
import com.artesanoskuad.loginintento7.databinding.FragmentRegistroBinding


class RegistroFragment : Fragment() {

    private var rawBinding: FragmentRegistroBinding? = null
    private val binding get() = rawBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rawBinding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getUser() = binding.etUsuario.text.toString()
    private fun getPassword() = binding.etPassword.text.toString()

}
