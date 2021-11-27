package com.artesanoskuad.loginintento7.authentication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.artesanoskuad.loginintento7.authentication.data.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    fun insertAll(vararg usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE nombre = :nombre AND password = :password")
    fun findUserByPassword(nombre: String, password: String): Usuario?

    @Query("SELECT * FROM usuario")
    fun getAllUsers(): List<Usuario>
}