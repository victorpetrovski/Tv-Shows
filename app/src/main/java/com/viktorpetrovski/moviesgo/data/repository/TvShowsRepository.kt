package com.viktorpetrovski.moviesgo.data.repository

import com.viktorpetrovski.moviesgo.data.remote.api.TvShowService
import javax.inject.Inject

/**
 * Created by Victor on 3/13/18.
 */
open class TvShowsRepository @Inject constructor(private val tvShowService: TvShowService){

    fun loadPopularTvShows(page : Int) = tvShowService.listPopularTvShows(page = page)

    fun loadTvShowDetails(tvShowId : Long) = tvShowService.getTvShowDetails(tvShowId)

    fun loadSimilarTvShows(tvShowId : Long, page : Int) = tvShowService.getSimilarTvShows(tvShowId,page = page)

}