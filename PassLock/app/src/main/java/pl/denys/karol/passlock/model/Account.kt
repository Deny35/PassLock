package pl.denys.karol.passlock.model


import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(
    @PrimaryKey(autoGenerate = true)
    var id: String = "",
    var user_id: String = "",
    val title: String = "",
    val login: String = "",
    val password: String = "",
    val website: String = "",
    val description: String = "",
    val type: String = ""
): Parcelable