package com.neirasphere.ecosphere.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "edu_history_table")
data class EduHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val image: Int,
    )