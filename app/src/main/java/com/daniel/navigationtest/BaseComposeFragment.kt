package com.daniel.navigationtest

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import com.daniel.navigationtest.ui.theme.NavigationTestTheme

abstract class BaseComposeFragment : Fragment() {

    open val providers: ArrayList<ProvidedValue<*>>
        get() {
            return arrayListOf()
        }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        // Dispose of the Composition when the view's LifecycleOwner
        // is destroyed
        setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            ProvideTheme {
                CompositionLocalProvider(
                    *providers.toTypedArray(),
                ) {
                    ScreenContent()
                }
            }
        }
    }

    @Composable
    abstract fun ScreenContent()

    @SuppressLint("DesignSystem")
    @Composable
    open fun ProvideTheme(content: @Composable () -> Unit) {
        // define your default theme here
        NavigationTestTheme(darkTheme = false) {
            content.invoke()
        }
    }

    protected fun adjustSoftInputMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }
}
