package com.neirasphere.ecosphere.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classify_history_table")
data class ClassifyHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val image: String,
//    val createdAt: Date?,
)