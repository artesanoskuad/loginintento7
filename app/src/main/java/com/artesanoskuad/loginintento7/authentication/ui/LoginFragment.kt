package com.artesanoskuad.loginintento7.authentication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.artesanoskuad.loginintento7.authentication.data.UsuariosBD
import com.artesanoskuad.loginintento7.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var rawBinding: FragmentLoginBinding? = null
    private val binding get() = rawBinding!!

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
    }

    private fun setupClickListener() {
        binding.btnLogin.setOnClickListener {
            validarUsuarioYContrasena(getUser(), getPassword())
        }
    }

    private fun validarUsuarioYContrasena(user: String, password: String) {
        val usuario = UsuariosBD.loginConUsuarioYPassword(user, password)
        if(usuario != null) {
            muestraMensaje("Usuario valido ${usuario.nombreCompleto}")
        } else {
            muestraMensaje("Usuario invalido")
        }
    }

    private fun muestraMensaje(mensaje: String) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun getUser() = binding.etUsuario.text.toString()
    private fun getPassword() = binding.etPassword.text.toString()

}
