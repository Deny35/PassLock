package pl.denys.karol.passlock.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel

@Dao
interface PasswordDao {


//    @Query("SELECT * FROM account_table ORDER BY id ASC")
//    fun getPass(): LiveData<List<AccountROOM>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun addPass(language: AccountROOM)
}