package icu.ofatal.yitai.ui.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RouterViewModel @Inject constructor(
    application: Application
):AndroidViewModel(application){

}