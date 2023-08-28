package com.iexceed.sneaker.presentation.ui.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iexceed.sneaker.common.Resource
import com.iexceed.sneaker.domain.use_case.get_Sneakers.GetSneakerUseCaase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SneakerListViewModel @Inject constructor(
    private val getSneakerUseCaase: GetSneakerUseCaase
) : ViewModel() {

     var _state = mutableStateOf(SneakerListState())
    val state: State<SneakerListState> = _state
    val textState =  mutableStateOf(TextFieldValue(""))



    val searchedText = textState.value.text

    init {
        getSneakers()
    }

    private fun getSneakers() {
        getSneakerUseCaase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SneakerListState(Sneakers = result.data ?: emptyList())
                    if(textState.value.text.isNotBlank()) {
                        _state.value = result.data?.filter { it.name.equals(searchedText) }
                            ?.let { SneakerListState(Sneakers = it) }!!
                    }
                }
                is Resource.Error -> {
                    _state.value = SneakerListState(error = result.message ?: "Error occured")
                }
                is Resource.Loading -> {
                    _state.value = SneakerListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
