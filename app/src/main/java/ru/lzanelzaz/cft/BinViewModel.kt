package ru.lzanelzaz.cft

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.lzanelzaz.cft.model.BinInfo

@HiltViewModel
class BinViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.InputData)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _data = MutableStateFlow<List<BinInfo>>(listOf())
    val data: StateFlow<List<BinInfo>> = _data.asStateFlow()

    var bin by mutableStateOf("")
        private set


    init {
        viewModelScope.launch {
            _data.value = repository.getAll().map { it.binInfo }
        }
    }

    fun updateBin(bin_: String) {
        bin = bin_
        _state.value = State.InputData
    }

    fun getBinInfo() {
        viewModelScope.launch {
            try {
                val binInfo = repository.getBinInfo(bin.toInt())
                _state.value = State.Valid(binInfo)
                repository.insertBin(binInfo)
                _data.value = repository.getAll().map { it.binInfo }
            } catch (e: retrofit2.HttpException) {
                _state.value = State.ShowError(R.string.noBinInfo)
            } catch (e: java.net.UnknownHostException) {
                _state.value = State.ShowError(R.string.noInternet)
            }
        }
    }

    fun setLengthError() {
        _state.value = State.LengthError
    }
}


sealed interface State {
    object LengthError : State
    object InputData : State
    data class ShowError(val messageResource: Int) : State
    data class Valid(val binInfo: BinInfo) : State
}