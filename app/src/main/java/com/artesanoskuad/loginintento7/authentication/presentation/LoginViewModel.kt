package com.artesanoskuad.loginintento7.authentication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artesanoskuad.loginintento7.authentication.data.Usuario
import com.artesanoskuad.loginintento7.authentication.data.UsuariosBD
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val mutableLiveData = MutableLiveData<String>()

    fun state() : LiveData<String> = mutableLiveData

    fun login(usuario: String, password: String){
        viewModelScope.launch{
            val usuario = UsuariosBD.loginConUsuarioYPassword(usuario, password)
            handleResponse(usuario)
        }
    }

    private fun handleResponse(usuario: Usuario?) {
        if(usuario != null){
            mutableLiveData.postValue(usuario.nombreCompleto)
        } else {
            mutableLiveData.postValue("Usuario no existe")
        }
    }

}