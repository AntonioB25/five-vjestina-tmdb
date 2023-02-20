package com.e.tmdb.module

import androidx.room.Room
import com.e.tmdb.database.AppDatabase
import com.e.tmdb.database.dao.MovieDao
import com.e.tmdb.networking.ApiModule
import com.e.tmdb.networking.MovieApi
import com.e.tmdb.networking.MovieApiImpl
import com.e.tmdb.respository.MovieDetailsRepository
import com.e.tmdb.respository.MovieDetailsRepositoryImpl
import com.e.tmdb.respository.MovieRepository
import com.e.tmdb.respository.MovieRepositoryImpl
import com.e.tmdb.viewModel.HomeViewModel
import com.e.tmdb.viewModel.MovieDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val httpClientModule = module {
    single { ApiModule.httpClient }
}

val movieApiModule = module {
    single { MovieApiImpl(get()) }
    single<MovieApi> { MovieApiImpl(get()) }
}

val repositoryModules = module {
    single { MovieRepositoryImpl(get(), get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }

    single { MovieDetailsRepositoryImpl(get(), get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(get(), get()) }
}

val viewModelsModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel {
        MovieDetailsViewModel(get())
    }
}

//I pass MovieDao to repositories, i tried to pass AppDatabase but I always get some error
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "movie-database"
        ).build()
    }

    single<MovieDao> {
        val database = get<AppDatabase>()
        database.movieDao()
    }
}
