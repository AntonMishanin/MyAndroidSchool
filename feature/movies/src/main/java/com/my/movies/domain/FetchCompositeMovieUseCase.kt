package com.my.movies.domain

import com.my.core.extensions.applySchedulers
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

class FetchCompositeMovieUseCase(private val repository: MovieRepository) {

    private val compositeDisposable = CompositeDisposable()

    operator fun invoke(): Flowable<CompositeMovieEntity> {
        fetchFreshNowPlayingAndSaveToStorage()
        fetchFreshPopularAndSaveToStorage()
        fetchFreshUpcomingAndSaveToStorage()

        return Flowable.zip(
            repository.fetchNowPlayingFromStorage(),
            repository.fetchPopularFromStorage(),
            repository.fetchUpcomingFromStorage()
        ) { nowPlaying, popular, upcoming ->
            CompositeMovieEntity(
                nowPlaying = nowPlaying,
                popular = popular,
                upcoming = upcoming
            )
        }
            .applySchedulers()
    }

    private fun fetchFreshNowPlayingAndSaveToStorage() {
        repository.fetchNowPlayingFromNetwork()
            .applySchedulers()
            .subscribe(object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(t: List<Movie>) {
                    val d = repository.saveNowPlayingToStorage(t)
                        .applySchedulers()
                        .subscribe {
                            Timber.e("Successful saving now playing movies to the storage")
                            compositeDisposable.clear()
                        }
                    compositeDisposable.add(d)
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    dispose()
                }
            })
    }

    private fun fetchFreshPopularAndSaveToStorage() {
        repository.fetchPopularFromNetwork()
            .applySchedulers()
            .subscribe(object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(t: List<Movie>) {
                    val d = repository.savePopularToStorage(t)
                        .applySchedulers()
                        .subscribe {
                            Timber.e("Successful saving popular movies to the storage")
                            compositeDisposable.clear()
                        }
                    compositeDisposable.add(d)
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    dispose()
                }
            })
    }

    private fun fetchFreshUpcomingAndSaveToStorage() {
        repository.fetchUpcomingFromNetwork()
            .applySchedulers()
            .subscribe(object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(t: List<Movie>) {
                    val d = repository.saveUpcomingToStorage(t)
                        .applySchedulers()
                        .subscribe {
                            Timber.e("Successful saving upcoming movies to the storage")
                            compositeDisposable.clear()
                        }
                    compositeDisposable.add(d)
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    dispose()
                }
            })
    }
}