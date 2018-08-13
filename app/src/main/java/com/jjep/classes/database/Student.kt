package com.jjep.classes.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "students")
class Student constructor(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "name") var name: String?
)