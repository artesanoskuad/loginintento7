package com.artesanoskuad.loginintento7.authentication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.artesanoskuad.loginintento7.authentication.data.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Usuario::class], version = 3)
abstract class AuthenticationDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object {

        @Volatile
        private var INSTANCE: AuthenticationDatabase? = null

        fun getDatabase(context: Context): AuthenticationDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    AuthenticationDatabase::class.java,
                    "authentication_database")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}