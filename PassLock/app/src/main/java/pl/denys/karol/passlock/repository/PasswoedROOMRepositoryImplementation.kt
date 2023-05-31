package pl.denys.karol.passlock.repository

import androidx.lifecycle.LiveData
import pl.denys.karol.passlock.Database.CaedDao
import pl.denys.karol.passlock.Database.PasswordDao
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel

class PasswoedROOMRepositoryImplementation(private val itemDao: CaedDao) {

    fun getAllPassRoom(): LiveData<List<AccountROOM>> {
        return itemDao.getPass()
    }

    fun addPassRoom(item: AccountROOM) {
        itemDao.addPass(item)
    }

    fun getUpdateAccount(id: Int): LiveData<AccountROOM>{
        return itemDao.getUpdateAccount(id)
    }

    suspend fun updateAccount(item: AccountROOM){
        itemDao.updateAccount(item)
    }

    suspend fun delAccount(item: AccountROOM){
        itemDao.delAccount(item)
    }
}
