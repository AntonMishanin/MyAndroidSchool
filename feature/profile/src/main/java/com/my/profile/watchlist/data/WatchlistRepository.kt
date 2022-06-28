package com.my.profile.watchlist.data

import com.my.favorite.domain.usecase.FavoriteEntity
import io.reactivex.Single

class WatchlistRepository {

    fun fetchWatchlist() = Single.just<List<FavoriteEntity>>(emptyList())
}