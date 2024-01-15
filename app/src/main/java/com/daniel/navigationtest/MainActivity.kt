package com.daniel.navigationtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.daniel.navigationtest.databinding.ActivityMainBinding
import com.daniel.navigationtest.delegate.viewBinding
import com.daniel.navigationtest.utils.NavTracker
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNavigation()
    }

    private fun setNavigation() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        val navTracker = createNavTracker()
        navController.addOnDestinationChangedListener(navTracker)
        val navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph) // todo create a nav graph

        navController.setGraph(navGraph, null)
    }

    private fun createNavTracker(): NavTracker {
        return NavTracker(this) { _, destination, _ ->
        }
    }
}
