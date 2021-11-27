package com.artesanoskuad.loginintento7.authentication.presentation

import android.app.Application
import androidx.lifecycle.*
import com.artesanoskuad.loginintento7.authentication.data.AuthenticationDatabase
import com.artesanoskuad.loginintento7.authentication.data.model.Usuario
import com.artesanoskuad.loginintento7.authentication.data.UsuarioDao
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioDao: UsuarioDao

    init {
        val dataBase = AuthenticationDatabase.getDatabase(application, viewModelScope)
        usuarioDao = dataBase.usuarioDao()
    }

    private val mutableLiveData = MutableLiveData<String>()

    fun state() : LiveData<String> = mutableLiveData

    fun login(usuario: String, password: String){
        viewModelScope.launch{
            val findUser = usuarioDao.findUserByPassword(usuario, password).value
            handleResponse(findUser)
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