package pl.denys.karol.passlock.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.denys.karol.passlock.Database.CardDatabase
import pl.denys.karol.passlock.Database.PasswordDatabase
import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.AccountROOM
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.repository.PasswoedROOMRepositoryImplementation
import pl.denys.karol.passlock.util.UiState

class PasswordViewModel(application: Application) : AndroidViewModel(application) {

    protected lateinit var repository: PasswoedROOMRepositoryImplementation

    init {
        val itemDuo = CardDatabase.getDatabase(application).cardDao()
        repository = PasswoedROOMRepositoryImplementation(itemDuo)

    }

    fun getAllRoomPass(): LiveData<List<AccountROOM>> {
        return repository.getAllPassRoom()
    }

    fun addRoomPass(AccountModel: AccountROOM){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addPassRoom(AccountModel)
        }
    }

    fun getUpdateAccount(id: Int): LiveData<AccountROOM> {
        return repository.getUpdateAccount(id)

    }

    fun updateAccount(AccountModel: AccountROOM){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateAccount(AccountModel)
        }
    }

    fun delAccount(item: AccountROOM){
        CoroutineScope(Dispatchers.IO).launch {
            repository.delAccount(item)
        }
    }
}