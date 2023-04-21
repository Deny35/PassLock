package pl.denys.karol.passlock.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.StorageReference
import pl.denys.karol.passlock.model.Account
import pl.denys.karol.passlock.model.User
import pl.denys.karol.passlock.util.UiState

class FirebaseRepositoryImplementation(
    val database: FirebaseFirestore,
    val storageReference: StorageReference
) : FirebaseRepository{
    override fun addPassword(account: Account, result: (UiState<Pair<Account, String>>) -> Unit) {
        val document = database.collection("user").document(account.user_id).collection("pass").document()
        account.id = document.id
        document
            .set(account)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success(Pair(account,"Password has been created successfully"))
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }
    override fun getAcount(userId: String, result: (UiState<List<Account>>) -> Unit) {
        database.collection("user").document(userId).collection("pass")
            .get()
            .addOnSuccessListener {
                val notes = arrayListOf<Account>()
                for (document in it) {
                    val note = document.toObject(Account::class.java)
                    notes.add(note)
                }
                result.invoke(
                    UiState.Success(notes)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun deleteAccount(account: Account, result: (UiState<String>) -> Unit) {
        database.collection("user").document(account.user_id).collection("pass").document(account.id)
            .delete()
            .addOnSuccessListener {
                result.invoke(UiState.Success("Note successfully deleted!"))
            }
            .addOnFailureListener { e ->
                result.invoke(UiState.Failure(e.message))
            }
    }
}