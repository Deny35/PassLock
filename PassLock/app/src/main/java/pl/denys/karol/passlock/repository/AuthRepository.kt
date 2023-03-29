package pl.denys.karol.passlock.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthenticationRepository(private val application: Application) {
    private val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth
    fun getFirebaseUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return firebaseUserMutableLiveData
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }

    init {
        firebaseUserMutableLiveData = MutableLiveData<FirebaseUser>()
        userLoggedMutableLiveData = MutableLiveData()
        auth = FirebaseAuth.getInstance()
        if (auth.getCurrentUser() != null) {
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser())
        }
    }

    fun register(email: String, pass: String) {

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(object :
            OnCompleteListener<AuthResult?> {
               override fun onComplete(task: Task<AuthResult?>) {
                    if (task.isSuccessful()){

                        firebaseUserMutableLiveData.postValue(auth.getCurrentUser())
                    } else {

                        Toast.makeText(
                            application,
                           "K",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

    }

    fun login(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful()) {
                firebaseUserMutableLiveData.postValue(auth.getCurrentUser())
            } else {
                Toast.makeText(
                    application,
                    "Logowanie nie powodło się!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    fun forgotPassword(email: String) {
        auth.sendPasswordResetEmail(email)
    }
    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }


}
