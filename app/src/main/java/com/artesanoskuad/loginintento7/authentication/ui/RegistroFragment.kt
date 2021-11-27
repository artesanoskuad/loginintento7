package com.artesanoskuad.loginintento7.authentication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.artesanoskuad.loginintento7.authentication.presentation.RegistroViewModel
import com.artesanoskuad.loginintento7.databinding.FragmentRegistroBinding


class RegistroFragment : Fragment() {

    private var rawBinding: FragmentRegistroBinding? = null
    private val binding get() = rawBinding!!
    private val registroViewModel: RegistroViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rawBinding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
        setupViewModel()
    }

    private fun setupViewModel() {
        registroViewModel.state().observe(this) {
            it?.let { safeState ->
                renderUI(safeState)
            }
        }
    }

    private fun renderUI(safeState: String) {
        Toast.makeText(context, safeState, Toast.LENGTH_SHORT).show()
    }

    private fun setupClickListener() {
        binding.btnVolver.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnRegistro.setOnClickListener {
            registroViewModel.registraUsuario(
                getNombre(),
                getPassword(),
                getNombreCompleto()
            )
        }
    }

    private fun getNombre() = binding.etUsuario.text.toString()
    private fun getPassword() = binding.etPassword.text.toString()
    private fun getNombreCompleto() = binding.etNombreCompleto.text.toString()

}
