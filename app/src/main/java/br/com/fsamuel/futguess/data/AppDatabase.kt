package br.com.fsamuel.futguess.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Usuario::class, Partida::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun partidaDao(): PartidaDao
    companion object { 
        @Volatile
        private var INSTANCIA: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCIA ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "futguess_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCIA = instance
                instance
            }
        }
    }
}