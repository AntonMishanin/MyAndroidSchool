package com.my.profile.favorite.di

import android.content.Context
import com.my.domain.usecase.FetchAllFavoriteUseCase
import com.my.movie.favorite.di.FavoriteFactory as FavoriteDataFactory

class FavoriteFactory {
    fun provideViewModelFactory(context: Context): FavoriteViewModelFactory {
        val favoriteRepository = FavoriteDataFactory().provideRepository(context)
        val fetchAllFavoriteUseCase = FetchAllFavoriteUseCase(favoriteRepository)
        return FavoriteViewModelFactory(fetchAllFavoriteUseCase)
    }
}