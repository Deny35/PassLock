package pl.denys.karol.passlock.repository

import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.CardModel
import pl.denys.karol.passlock.util.UiState

interface CardRepository {

    fun addCard(account: CardModel, result: (UiState<Pair<Account, String>>) -> Unit)
    fun getAllCards(result: (UiState<List<Account>>) -> Unit)
}