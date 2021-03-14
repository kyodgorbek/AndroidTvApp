package com.tvmaze.app.app.di

import androidx.room.Room
import com.tvmaze.app.search.db.TvMazeDatabase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


object DatabaseInjectionModule {

    val module = Kodein.Module(DatabaseInjectionModule.javaClass.name) {

        bind<TvMazeDatabase>() with singleton {
            Room
                .databaseBuilder(
                    instance(),
                    TvMazeDatabase::class.java,
                    "tvmaze_database"
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}
