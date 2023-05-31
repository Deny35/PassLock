package pl.denys.karol.passlock.repository

import pl.denys.karol.passlock.model.User
import pl.denys.karol.passlock.util.UiState

interface CryptRepository {

    fun encrypt(strToEncrypt: String, result: (UiState<String>) -> Unit)
    fun decrypt(strToDecrypt: String, result: (UiState<String>) -> Unit)
}