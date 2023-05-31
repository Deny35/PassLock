package pl.denys.karol.passlock.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.User
import pl.denys.karol.passlock.repository.AuthRepository
import pl.denys.karol.passlock.repository.FirebaseRepository
import pl.denys.karol.passlock.util.UiState
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    val repository: FirebaseRepository
): ViewModel() {

    private val _addAccount = MutableLiveData<UiState<Pair<Account,String>>>()
    val addAccount: LiveData<UiState<Pair<Account,String>>>
        get() = _addAccount

    private val _account = MutableLiveData<UiState<List<Account>>>()
    val account: LiveData<UiState<List<Account>>>
        get() = _account

    private val _deleteAccount = MutableLiveData<UiState<String>>()
    val deleteAccount: LiveData<UiState<String>>
        get() = _deleteAccount

    private val _updateAccount = MutableLiveData<UiState<Pair<Account,String>>>()
    val updateAccount: LiveData<UiState<Pair<Account,String>>>
        get() = _updateAccount

    fun addAccount(accounr :Account){
        _addAccount.value = UiState.Loading
        repository.addPassword(accounr) { _addAccount.value = it }
    }

    fun getNotes(userId: String) {
        _account.value = UiState.Loading
        repository.getAcount(userId) { _account.value = it }
    }

    fun deleteAccount(note: Account){
        _deleteAccount.value = UiState.Loading
        repository.deleteAccount(note) { _deleteAccount.value = it }
    }

    fun updateAccount(accounr :Account){
        _updateAccount.value = UiState.Loading
        repository.updateAccount(accounr) { _updateAccount.value = it }
    }
}



