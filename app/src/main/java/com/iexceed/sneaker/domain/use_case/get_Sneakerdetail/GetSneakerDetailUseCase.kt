package com.iexceed.sneaker.domain.use_case.get_Sneakerdetail

import com.iexceed.sneaker.common.Resource
import com.iexceed.sneaker.data.remote.dto.toSneakerDetail
import com.iexceed.sneaker.domain.model.SneakerDetail
import com.iexceed.sneaker.domain.repository.SneakerRepositoryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSneakerDetailUseCase @Inject constructor(
    private val repository: SneakerRepositoryApi
) {
    operator fun invoke(SneakerId: String): Flow<Resource<SneakerDetail>> = flow {
        try {
            emit(Resource.Loading<SneakerDetail>())
            val Sneaker = repository.getSneakerDetail(SneakerId).toSneakerDetail()
            emit(Resource.Success<SneakerDetail>(Sneaker))
        } catch (e: HttpException) {
            emit(Resource.Error<SneakerDetail>(e.localizedMessage ?: "Exception occured"))
        } catch (e: IOException) {
            emit(Resource.Error<SneakerDetail>(e.localizedMessage ?: "No network "))
        }
    }
}
