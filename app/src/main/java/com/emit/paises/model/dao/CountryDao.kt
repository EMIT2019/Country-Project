package com.emit.paises.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import com.emit.paises.model.entities.Country

@Dao
interface CountryDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(country: Country)
}