package pl.denys.karol.passlock.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.denys.karol.passlock.Database.CardDatabase
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.repository.CardRepositoryImplementation
import pl.denys.karol.passlock.util.UiState
import javax.inject.Inject


class CardViewModel(application: Application) : AndroidViewModel(application){
    protected lateinit var repository: CardRepositoryImplementation

    init {
        val itemDuo = CardDatabase.getDatabase(application).cardDao()
        repository = CardRepositoryImplementation(itemDuo)

    }
    fun getAllCards(): LiveData<List<CardModel>>{
        return repository.getAllCards()
    }

    fun addCard(cardModel: CardModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addCard(cardModel)
        }
    }

    fun getUpdateCard(id: Int): LiveData<CardModel> {
        return repository.getUpdateCard(id)

    }

    fun updateCard(cardModel: CardModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateCard(cardModel)
        }
    }

    fun delCard(item: CardModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.delCard(item)
        }
    }
}