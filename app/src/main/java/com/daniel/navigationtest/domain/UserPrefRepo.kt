package com.daniel.navigationtest.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Created by AP-Jake
 * on 16/01/2024
 */

// JUST FOR TESTING PURPOSE
object UserPrefRepo {

    private val _shouldUseNgpFeature = MutableStateFlow(false)
    val shouldUseNgpFeature = _shouldUseNgpFeature.asStateFlow()

    var enableNgpFeature: Boolean
        get() = _shouldUseNgpFeature.value
        set(value) = _shouldUseNgpFeature.update { value }

}
