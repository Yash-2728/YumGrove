package com.example.yumgrove

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class Recipe (var img:String,
                var tittle:String,
                var des: String,
                var ing:String,
                var category :String,
              @PrimaryKey(autoGenerate = true)
              val uid: Int
                )
