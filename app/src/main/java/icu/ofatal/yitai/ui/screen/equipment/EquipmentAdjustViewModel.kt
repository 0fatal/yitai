package icu.ofatal.yitai.ui.screen.equipment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import icu.ofatal.yitai.data.api.Bemfa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class EquipmentAdjustViewModel @Inject constructor(
    private val bemfa: Bemfa
): ViewModel(){
    var pressValue = MutableStateFlow(listOf(1.0,1.0,1.0,1.0,1.0,1.0,1.0, 1.0, 1.0))
    var error = MutableStateFlow<IOException?>(null)

    fun fetchData() {
        viewModelScope.launch {
            bemfa.getData { it, e ->
                if (e != null) {
                    error.value = e
                } else {
                    if(it != null) {
                        pressValue.value = it
                    }
                }
                //                Log.d("press value", pressValue.value.toString())
            }
        }
    }

    fun frontEquipment(completion: ((IOException?) -> Unit)? = null) {
        viewModelScope.launch {
            bemfa.front(completion)
        }
    }

    fun twinkle() {
        viewModelScope.launch {
            bemfa.twinkle()
        }
    }

    fun backEquipment(completion: ((IOException?) -> Unit)? = null) {
        viewModelScope.launch {
            bemfa.back(completion)
        }
    }

    fun qianEquipment(completion: ((IOException?) -> Unit)? = null) {
        viewModelScope.launch {
            bemfa.qian(completion)
        }
    }

    fun houEquipment(completion: ((IOException?) -> Unit)? = null) {
        viewModelScope.launch {
            bemfa.hou(completion)
        }
    }

    fun stopEquipment(completion: ((IOException?) -> Unit)? = null) {
        viewModelScope.launch {
            bemfa.stop(completion)
        }
    }

    fun tingEquipment(completion: ((IOException?) -> Unit)? = null) {
        viewModelScope.launch {
            bemfa.ting(completion)
        }
    }
}


