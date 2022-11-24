package com.equijada95.heroapp.domain.api.repository

import com.equijada95.heroapp.data.api.model.HeroModel
import com.equijada95.heroapp.data.api.provider.HeroProvider
import com.equijada95.heroapp.domain.result.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface HeroRepository {
    suspend fun getHeroes(): Flow<ApiResult<List<HeroModel>>>
}

class HeroRepositoryImpl @Inject constructor(
    private val heroProvider : HeroProvider
) : HeroRepository {

    override suspend fun getHeroes(): Flow<ApiResult<List<HeroModel>>> = flow {
        emit(ApiResult.Loading())
        try {
            val apiResponse = heroProvider.getAll().body()
            emit(ApiResult.Success(apiResponse))
        } catch (e: HttpException) {
            emit(ApiResult.Error(
                error = ApiResult.ApiError.SERVER_ERROR,
                data = null
            ))
        } catch (e: IOException) {
            emit(ApiResult.Error(
                error = ApiResult.ApiError.NO_CONNECTION_ERROR,
                data = null
            ))
        }
    }
}