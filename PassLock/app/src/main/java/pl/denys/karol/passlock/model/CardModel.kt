package pl.denys.karol.passlock.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)])
data class CardModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var user_id: String = "",
    val title: String,
    val number : String,
    val month: String,
    val ye: String,
    val cvv: String,
    val description: String
)
