package com.iexceed.sneaker.presentation.ui.Sneakerdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iexceed.sneaker.common.Constants
import com.iexceed.sneaker.common.Resource
import com.iexceed.sneaker.domain.use_case.cart_Sneaker.AddToCartUseCase
import com.iexceed.sneaker.domain.use_case.get_Sneakerdetail.GetSneakerDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerDetailViewModel @Inject constructor(
    private val getSneakerDetailUseCase: GetSneakerDetailUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SneakerDetailState())
    val state: State<SneakerDetailState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_Sneaker_ID)?.let { it ->
            getSneaker(it)
        }
    }

    private fun getSneaker(SneakerId: String) {
        getSneakerDetailUseCase(SneakerId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SneakerDetailState(Sneaker = result.data)
                }
                is Resource.Error -> {
                    _state.value = SneakerDetailState(error = result.message ?: "Error occured")
                }
                is Resource.Loading -> {
                    _state.value = SneakerDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun onEvent(event: SneakerAddEvent) {
        when (event) {
            is SneakerAddEvent.InsertSneaker -> {
                viewModelScope.launch {
                    addToCartUseCase(event.sneakerCart)
                    _eventFlow.emit(UiEvent.SaveCart)
                }
            }
        }
    }
    sealed class UiEvent {
        object SaveCart : UiEvent()
    }
}
