package com.example.roomsdb.DB.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(var notes:String):Serializable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}