package com.iexceed.sneaker.domain.use_case.get_Sneakers

import com.iexceed.sneaker.common.Resource
import com.iexceed.sneaker.data.remote.dto.toSneaker
import com.iexceed.sneaker.domain.model.Sneaker
import com.iexceed.sneaker.domain.repository.SneakerRepositoryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSneakerUseCaase @Inject constructor(
    private val repository: SneakerRepositoryApi
) {
    operator fun invoke(): Flow<Resource<List<Sneaker>>> = flow {
        try {
            emit(Resource.Loading<List<Sneaker>>())
            val Sneakers = repository.getSneakers().map { it.toSneaker() }
            emit(Resource.Success<List<Sneaker>>(Sneakers))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Sneaker>>(e.localizedMessage ?: "Exception occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Sneaker>>(e.localizedMessage ?: "No network "))
        }
    }
}
