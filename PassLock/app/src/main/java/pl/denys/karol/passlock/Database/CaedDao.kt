package pl.denys.karol.passlock.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel

@Dao
interface CaedDao {

    @Query("SELECT * FROM CardModel ORDER BY id ASC")
    fun getCards(): LiveData<List<CardModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCard(language: CardModel)


    @Query("SELECT * FROM AccountROOM ORDER BY id ASC")
    fun getPass(): LiveData<List<AccountROOM>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPass(language: AccountROOM)

    @Query("SELECT * FROM CardModel WHERE id = :id")
    fun getUpdateCard(id: Int): LiveData<CardModel>

    @Query("SELECT * FROM AccountROOM WHERE id = :id")
    fun getUpdateAccount(id: Int): LiveData<AccountROOM>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCard(item: CardModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAccount(item: AccountROOM)

    @Delete
    suspend fun delCard(item: CardModel)

    @Delete
    suspend fun delAccount(item: AccountROOM)
}