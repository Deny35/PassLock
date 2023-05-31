package pl.denys.karol.passlock.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel

@Database(entities = [CardModel::class,
                     AccountROOM::class], version = 1, exportSchema = false)
abstract class CardDatabase: RoomDatabase() {

    abstract fun cardDao(): CaedDao

    companion object {
        @Volatile
        private var INSTANCE: CardDatabase? = null

        fun getDatabase(context: Context): CardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    "saa"
                ).build().also { INSTANCE = it }
                instance
            }
        }
    }
}