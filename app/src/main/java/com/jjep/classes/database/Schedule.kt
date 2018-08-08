package com.jjep.classes.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "schedules")
data class Schedule(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "student_name") var studentName: String?,
    @ColumnInfo(name = "student_obs") var studentObs: String?,
    @ColumnInfo(name = "date") var date: String?,
    @ColumnInfo(name = "time") var time: String?,
    @ColumnInfo(name = "_student_pos") var _student_pos: Int?
) {
    constructor() : this(null, null, null, null, null, null)
}