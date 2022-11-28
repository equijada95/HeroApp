package com.equijada95.heroapp.domain.repository

import com.equijada95.heroapp.data.api.model.HeroModel
import com.equijada95.heroapp.data.api.provider.HeroProvider
import com.equijada95.heroapp.data.bbdd.dao.HeroDao
import com.equijada95.heroapp.data.bbdd.models.HeroDbModel
import com.equijada95.heroapp.domain.result.ApiResult
import com.equijada95.heroapp.domain.utils.mapToModel
import com.equijada95.heroapp.domain.utils.setListWithFavorites
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface HeroRepository {
    suspend fun getHeroes(scope: CoroutineScope, refresh: Boolean): Flow<ApiResult<List<HeroModel>>>
    suspend fun insertHero(hero: HeroDbModel)
    suspend fun deleteHero(hero: HeroDbModel)
}

class HeroRepositoryImpl @Inject constructor(
    private val heroProvider : HeroProvider,
    private val dao: HeroDao
) : HeroRepository {

    override suspend fun getHeroes(scope: CoroutineScope, refresh: Boolean): Flow<ApiResult<List<HeroModel>>> = flow {
        val favorites = dao.getAll().stateIn(scope = scope)
        emit(ApiResult.Error(
            error = ApiResult.ApiError.NO_ERROR,
            data = null
        ))
        if (!refresh) emit(ApiResult.Loading())
        try {
            val apiResponse = heroProvider.getAll().body()
            emit(ApiResult.Success(apiResponse))
            favorites.collect {
                apiResponse?.setListWithFavorites(it)
                emit(ApiResult.Success(apiResponse))
            }
        } catch (e: HttpException) {
            emit(ApiResult.Error(
                error = ApiResult.ApiError.SERVER_ERROR,
                data = favorites.value.mapToModel()
            ))
        } catch (e: IOException) {
            emit(ApiResult.Error(
                error = ApiResult.ApiError.NO_CONNECTION_ERROR,
                data = favorites.value.mapToModel()
            ))
        }
    }

    override suspend fun insertHero(hero: HeroDbModel) {
        dao.insert(hero)
    }

    override suspend fun deleteHero(hero: HeroDbModel) {
        dao.delete(hero)
    }

}