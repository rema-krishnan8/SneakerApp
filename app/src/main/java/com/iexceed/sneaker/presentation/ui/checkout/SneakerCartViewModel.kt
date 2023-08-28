package com.iexceed.sneaker.presentation.ui.checkout

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iexceed.sneaker.domain.use_case.cart_Sneaker.DeleteFromCartUseCase
import com.iexceed.sneaker.domain.use_case.cart_Sneaker.GetCartSneakerUseCaase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerCartViewModel@Inject constructor(
    private val getCartSneakerUseCaase: GetCartSneakerUseCaase,
    private val deleteFromCartUseCase: DeleteFromCartUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SneakerCartState())
    val state: State<SneakerCartState> = _state
    val priceState: MutableState<Int>
        get() = mutableStateOf(_state.value.sneakerCart.sumOf { it.price })

    init {

        getCartSneakerUseCaase().onEach { carts ->

            _state.value = state.value.copy(
                sneakerCart = carts
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SneakerCartEvent) {
        when (event) {
            is SneakerCartEvent.DeleteSneaker -> {
                viewModelScope.launch {
                    deleteFromCartUseCase(event.sneakerCart)
                }
            }
        }
    }
}
