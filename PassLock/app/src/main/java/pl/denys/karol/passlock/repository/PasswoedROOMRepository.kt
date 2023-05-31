package pl.denys.karol.passlock.repository

import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.util.UiState

interface PasswoedROOMRepository {

    fun addPassRoom(account: CardModel, result: (UiState<Pair<Account, String>>) -> Unit)
    fun getAllPassRoom(result: (UiState<List<Account>>) -> Unit)
}