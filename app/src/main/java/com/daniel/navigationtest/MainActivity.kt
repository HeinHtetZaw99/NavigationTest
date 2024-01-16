package com.daniel.navigationtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.daniel.navigationtest.databinding.ActivityMainBinding
import com.daniel.navigationtest.delegate.viewBinding
import com.daniel.navigationtest.home.ShopFrontFragmentDirections
import com.daniel.navigationtest.utils.NavTracker
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    var deepLink: String? = ""
    var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        deepLink = intent.extras?.get("deepLink")?.toString() ?: ""
        Timber.tag("DeepLinkFromMainActivity").i("deepLink : $deepLink")
        setNavigation()
    }

    private fun setNavigation() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        navController?.let { navCtrler ->
            val navTracker = createNavTracker()
            navCtrler.addOnDestinationChangedListener(navTracker)
            val navGraph =
                navCtrler.navInflater.inflate(R.navigation.main_nav_graph) // todo create a nav graph

            navCtrler.setGraph(navGraph, null)
        }

        deepLink?.let {
            if (it == DeepLinkMode.Deals.deepLinkUrl) {
                navController?.navigate(
                    ShopFrontFragmentDirections.actionGlobalToDealsFragmentBackToCarrides(
                        true,
                    ),
                )
            }
        }
    }

    private fun createNavTracker(): NavTracker {
        return NavTracker(this) { _, destination, _ ->
        }
    }
}

sealed class DeepLinkMode(val deepLinkUrl: String) {
    data object Deals : DeepLinkMode("deeplink://deals")
}
