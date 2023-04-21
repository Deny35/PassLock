package pl.denys.karol.passlock.repository


import android.content.SharedPreferences
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import pl.denys.karol.passlock.model.User
import pl.denys.karol.passlock.util.UiState



class AuthRepositoryImplementation(
    val auth: FirebaseAuth,
    val database: FirebaseFirestore,
    val appPreferences: SharedPreferences,
    val gson: Gson
) : AuthRepository {

    override fun registerUser(
        email: String,
        password: String,
        user: User,
        result: (UiState<String>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if(it.isSuccessful) {
                    user.id = it.result.user?.uid ?: ""
                    updateUserInfo(user) { state ->
                        if (state is UiState.Success) {
                            storeSession(id = it.result.user?.uid ?: "") {
                                if(it == null) {
                                    result.invoke(UiState.Failure("Failed to store session."))
                                } else {
                                    result.invoke(UiState.Success("Registration Successful!"))
                                }
                            }
                        }
                        else if (state is UiState.Failure) {
                            result.invoke(UiState.Failure(state.error))
                        }
                    }
                } else {
                    try {
                        throw it.exception ?: java.lang.Exception("Invalid authentication")
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        result.invoke(UiState.Failure("Please use a stronger password"))
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        result.invoke(UiState.Failure("Email invalid"))
                    } catch (e: FirebaseAuthUserCollisionException) {
                        result.invoke(UiState.Failure("Email already exists"))
                    } catch (e: Exception) {
                        result.invoke(UiState.Failure(e.message))
                    }
                }
            } .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun signInUser(email: String, password: String, result: (UiState<String>) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { signIn ->
            if (signIn.isSuccessful) {
                val user = auth.currentUser
                storeSession(id = user?.uid ?: "") {
                    if (it != null) {
                        result.invoke(UiState.Success("Login Success"))
                    } else {
                        result.invoke(UiState.Failure("Failed to store session locally"))
                    }
                }
            } else {
                result.invoke(UiState.Failure("Authentication failed"))
            }
        }.addOnFailureListener {
            result.invoke(UiState.Failure("Authentication failed"))
        }
    }

    override fun forgotPassword(email: String, result: (UiState<String>) -> Unit) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                result.invoke(UiState.Success("Password reset email has been sent"))
            } else {
                result.invoke(UiState.Failure(task.exception?.message))
            }
        }.addOnFailureListener {
            result.invoke(UiState.Failure("Failed, email does not exist"))
        }
    }

    override fun SignOut( result: (UiState<String>) -> Unit) {
        auth.signOut()
        appPreferences.edit().putString("user_session",null).apply()
        val userSession = appPreferences.getString("user_session",null)
        if (userSession == null){
            result.invoke(UiState.Success("Success"))
        }
        else{
            result.invoke(UiState.Failure("Failed"))
        }

    }

    override fun getSession(result: (User?) -> Unit) {
        val userSession = appPreferences.getString("user_session",null)
        if (userSession == null){
            result.invoke(null)
        }else{
            val user = gson.fromJson(userSession,User::class.java)
            result.invoke(user)
        }
    }

    override fun storeSession(id: String, result: (User?) -> Unit) {
        database.collection("user").document(id).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = it.result.toObject(User::class.java)
                    appPreferences.edit().putString("user_session",gson.toJson(user)).apply()
                    result.invoke(user)
                } else {
                    result.invoke(null)
                }
            }.addOnFailureListener {
                result.invoke(null)
            }
    }

    override fun updateUserInfo(user: User, result: (UiState<String>) -> Unit) {
        val document = database.collection("user").document(user.id)
//        val pass = database.collection("user").document(user.id).collection("pass")
        document.set(user)
            .addOnSuccessListener {
                result.invoke(UiState.Success("User has been update successfully"))
//                pass.add("")

            }.addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }
    }

}
