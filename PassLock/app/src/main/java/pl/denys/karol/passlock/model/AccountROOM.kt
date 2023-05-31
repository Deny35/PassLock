package pl.denys.karol.passlock.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["id"], unique = true)])
data class AccountROOM(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var user_id: String = "",
    val title: String = "",
    val login: String = "",
    val password: String = "",
    val website: String = "",
    val description: String = "",
    val timeH: Int = 0,
    val timeM: Int = 0,
    val type: String = ""
)