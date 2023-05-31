package pl.denys.karol.passlock.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.denys.karol.passlock.model.CardModel
//
//@Database(entities = [CardModel::class], version = 1, exportSchema = false)
abstract class PasswordDatabase: RoomDatabase() {

//    abstract fun passDao(): PasswordDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: PasswordDatabase? = null
//
//        fun getDatabase(context: Context): PasswordDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    PasswordDatabase::class.java,
//                    "word_database_kotlin"
//                ).build().also { INSTANCE = it }
//                instance
//            }
//        }
//    }
}