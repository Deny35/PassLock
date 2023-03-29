package com.codingstuff.loginsignupmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import pl.denys.karol.passlock.repository.AuthenticationRepository

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AuthenticationRepository
    val userData: MutableLiveData<FirebaseUser>
    val loggedStatus: MutableLiveData<Boolean>

//    fun getFirebaseUserMutableLiveData(): MutableLiveData<FirebaseUser> {
//        return repository.getFirebaseUserMutableLiveData()
//    }

    fun getCurrentUser(): FirebaseUser? {
        return repository.getCurrentUser()
    }
    init {
        repository = AuthenticationRepository(application)
        userData = repository.getFirebaseUserMutableLiveData()
        loggedStatus = repository.userLoggedMutableLiveData
    }

    fun register(email: String, pass: String) {
        repository.register(email, pass)
    }

    fun signIn(email: String, pass: String) {
        repository.login(email, pass)

    }

    fun forgotPassword(email: String){
        repository.forgotPassword(email)
    }

    fun signOut() {
        repository.signOut()
    }
    
}