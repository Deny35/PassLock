package pl.denys.karol.passlock.repository

import androidx.lifecycle.LiveData
import pl.denys.karol.passlock.Database.CaedDao
import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.util.UiState

class CardRepositoryImplementation(private val itemDao: CaedDao) {

    fun getAllCards(): LiveData<List<CardModel>>{
        return itemDao.getCards()
    }

    fun addCard(item: CardModel) {
        itemDao.addCard(item)
    }

    fun getUpdateCard(id: Int): LiveData<CardModel>{
       return itemDao.getUpdateCard(id)
    }

    suspend fun updateCard(item: CardModel){
        itemDao.updateCard(item)
    }

    suspend fun delCard(item: CardModel){
        itemDao.delCard(item)
    }
}