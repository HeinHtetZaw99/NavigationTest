package com.daniel.navigationtest.home

import androidx.lifecycle.ViewModel
import com.daniel.navigationtest.domain.UserPrefRepo

class HomeViewModel : ViewModel() {

    val shouldUseNgpFeature = UserPrefRepo.shouldUseNgpFeature

    fun onToggleNgpFeature(enable: Boolean) {
        UserPrefRepo.enableNgpFeature = enable
    }

}
