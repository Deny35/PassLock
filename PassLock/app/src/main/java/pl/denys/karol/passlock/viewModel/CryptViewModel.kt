package pl.denys.karol.passlock.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.denys.karol.passlock.repository.AuthRepository
import pl.denys.karol.passlock.repository.CryptRepository
import pl.denys.karol.passlock.util.UiState
import javax.inject.Inject


@HiltViewModel
class CryptViewModel @Inject constructor(
    val repository: CryptRepository
): ViewModel() {


    private val _encrypt = MutableLiveData<UiState<String>>()
    val encrypt: LiveData<UiState<String>>
    get() = _encrypt

    private val _decrypt = MutableLiveData<UiState<String>>()
    val decrypt: LiveData<UiState<String>>
    get() = _decrypt

    fun encrypt(strToEncrypt: String){
        _encrypt.value =  UiState.Loading
        repository.encrypt(strToEncrypt){
            _encrypt.value = it
        }
    }

    fun decrypt(strToDecrypt: String){
        _decrypt.value =  UiState.Loading
        repository.decrypt(strToDecrypt){
            _decrypt.value = it
        }
    }
}