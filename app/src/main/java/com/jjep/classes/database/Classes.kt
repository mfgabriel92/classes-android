package com.jjep.classes.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "classes")
data class Classes(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "student_name") var studentName: String?,
    @ColumnInfo(name = "student_obs") var studentObs: String?,
    @ColumnInfo(name = "class_time") var classTime: String?,
    @ColumnInfo(name = "date") var date: Date?
) {
    constructor() : this(null, null, null, null, null)
}