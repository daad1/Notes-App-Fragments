package com.example.notesappfragments

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Note")
data class Note(
    @PrimaryKey(autoGenerate = true) val pk: Int,
    @ColumnInfo val title: String?,
    @ColumnInfo val subTitle:String,
    @ColumnInfo val noteDescription: String?,
    @ColumnInfo var date:String = ""
): Parcelable