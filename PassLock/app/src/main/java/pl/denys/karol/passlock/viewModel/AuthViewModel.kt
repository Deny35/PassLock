package com.codingstuff.loginsignupmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.denys.karol.passlock.model.User
import pl.denys.karol.passlock.repository.AuthRepository
import pl.denys.karol.passlock.util.UiState
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val repository: AuthRepository
): ViewModel() {
    private val _register = MutableLiveData<UiState<String>>()
    val register: LiveData<UiState<String>>
    get() = _register

    private val _signIn = MutableLiveData<UiState<String>>()
    val signIn: LiveData<UiState<String>>
    get() = _signIn

    private val _forgotPassword = MutableLiveData<UiState<String>>()
    val forgotPassword: LiveData<UiState<String>>
    get() = _forgotPassword

    private val _signOut = MutableLiveData<UiState<String>>()
    val signOut: LiveData<UiState<String>>
    get() = _signOut

    fun register(
        email: String,
        password: String,
        user: User
    ) {
        _register.value = UiState.Loading
        repository.registerUser(
            email = email,
            password = password,
            user = user
        ) { _register.value = it }
    }

    fun signIn(
        email: String,
        password: String
    ) {
        _signIn.value = UiState.Loading
        repository.signInUser(
            email,
            password
        ){
            _signIn.value = it
        }
    }

    fun forgotPassword(email: String) {
        _forgotPassword.value = UiState.Loading
        repository.forgotPassword(email){
            _forgotPassword.value = it
        }
    }

    fun signOut() {
        _signOut.value = UiState.Loading
        repository.SignOut() {
            _signOut.value = it
        }
    }

    fun getSession(result: (User?) -> Unit){
        repository.getSession(result)
    }
}