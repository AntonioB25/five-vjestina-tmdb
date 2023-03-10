package com.e.tmdb.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crew_member")
data class DbCrewMember(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "crew_id")
    val crewId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "job")
    val job: String,
    @ColumnInfo(name = "department")
    val department: String,
)
