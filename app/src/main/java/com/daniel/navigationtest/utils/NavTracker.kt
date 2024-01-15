package com.daniel.navigationtest.utils

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import org.json.JSONObject
import timber.log.Timber

class NavTracker(context: Context, private val additionalTasks: ((controller: NavController, destination: NavDestination, arguments: Bundle?) -> Unit)? = null) :
    NavController.OnDestinationChangedListener {
    val res: Resources = context.resources
    var logTag: String = "NavigationLog"
    var shouldAdaptForCompose: Boolean = false

    fun setTag(logTag: String) {
        this.logTag = logTag
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        if (shouldAdaptForCompose) {
            Timber.tag(logTag).i(
                JSONObject()
                    .put("destination", controller.currentDestination?.route)
                    .put("startDestination", controller.previousBackStackEntry?.destination?.route ?: "root")
                    .put("bundleJsonData", bundleToJson(arguments ?: Bundle()))
                    .put("backQueue", "${controller.backQueue.map { it.destination.route }}").toString(),
            )
        } else {
            Timber.tag(logTag).i(
                JSONObject()
                    .put(
                        "destination",
                        res.getResourceEntryNameOrJustID(destination.id),
                    )
                    .put(
                        "startDestination",
                        controller.previousBackStackEntry?.destination?.id?.let { res.getResourceEntryNameOrJustID(it) }
                            ?: res.getResourceEntryNameOrJustID(controller.graph.startDestinationId),
                    )
                    .put("bundleJsonData", bundleToJson(arguments ?: Bundle()))
                    .put("backQueue", "${controller.backQueue.map { it.destination.displayName }}").toString(),
            )
        }
        additionalTasks?.invoke(controller, destination, arguments)
    }

    private fun bundleToJson(bundle: Bundle) = JSONObject()
        .apply {
            bundle.keySet().forEach { key ->
                put(key, JSONObject.wrap(bundle.get(key)))
            }
        }

    fun Resources.getResourceEntryNameOrJustID(id: Int): String {
        return try {
            getResourceEntryName(id)
        } catch (e: Exception) {
            id.toString()
        }
    }
}
