package com.emit.paises.model

/*
@Database(entities = [Country::class], version = 1)
abstract class PaisesDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: PaisesDatabase ?= null

        private fun buildInstance(context: Context) = Room.databaseBuilder(
            context,
            PaisesDatabase::class.java,
            "countriesDB"
        ).build()

        fun getInstance(context: Context): PaisesDatabase = INSTANCE ?: synchronized(this){
            buildInstance(context)
        }.also {
            INSTANCE = it
        }
    }
}
*/