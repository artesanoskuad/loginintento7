package com.artesanoskuad.loginintento7.authentication.presentation

import android.app.Application
import androidx.lifecycle.*
import com.artesanoskuad.loginintento7.authentication.data.AuthenticationDatabase
import com.artesanoskuad.loginintento7.authentication.data.UsuarioDao
import com.artesanoskuad.loginintento7.authentication.data.model.Usuario
import kotlinx.coroutines.launch

class RegistroViewModel(application: Application) : AndroidViewModel(application) {

    private val mutableState = MutableLiveData<String>()
    private val usuarioDao: UsuarioDao

    fun state() : LiveData<String> = mutableState

    init {
        val dataBase = AuthenticationDatabase.getDatabase(application)
        usuarioDao = dataBase.usuarioDao()
    }

    fun registraUsuario(nombre: String, password: String, nombreCompleto: String){
        viewModelScope.launch {
            usuarioDao.insertAll(Usuario(nombre, nombreCompleto, password))
            val findUserByPassword = usuarioDao.findUserByPassword(nombre, password)
            handleState(findUserByPassword)
        }
    }

    private fun handleState(usuario: Usuario?) {
        if(usuario == null){
            mutableState.postValue("No se registro usuario")
        } else {
            mutableState.postValue("Usuario: ${usuario.nombreCompleto}")
        }
    }


}