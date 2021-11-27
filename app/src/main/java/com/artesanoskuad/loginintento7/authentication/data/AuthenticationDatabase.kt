package com.artesanoskuad.loginintento7.authentication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.artesanoskuad.loginintento7.authentication.data.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Usuario::class], version = 2)
abstract class AuthenticationDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    private class AuthenticationDatabaseRoomCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {  database ->
                scope.launch {
                    val loginDao = database.usuarioDao()
                    prePopulateDatabase(loginDao)
                }
            }
        }

        private fun prePopulateDatabase(usuarioDao: UsuarioDao) {
            val goku = Usuario("goku", "Goku Super Sayajin", "goku")
            val vegeta = Usuario("vegeta", "Vegeta Principe Sayajin", "vegeta")
            val homero = Usuario("homero", "Homero Jay Sipmsons", "homero")
            usuarioDao.insertAll(goku, vegeta, homero)
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: AuthenticationDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope): AuthenticationDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    AuthenticationDatabase::class.java,
                    "authentication_database")
                    .addCallback(AuthenticationDatabaseRoomCallback(coroutineScope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}