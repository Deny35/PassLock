package pl.denys.karol.passlock.repository

import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.User
import pl.denys.karol.passlock.util.UiState

interface FirebaseRepository {
    fun addPassword(account: Account, result: (UiState<Pair<Account, String>>) -> Unit)
    fun getAcount(userId: String, result: (UiState<List<Account>>) -> Unit)
    fun deleteAccount(account: Account, result: (UiState<String>) -> Unit)
    fun updateAccount(account: Account, result: (UiState<Pair<Account,String>>) -> Unit)
}