package com.reactnativenavigation.react

import com.facebook.react.BaseReactPackage
import com.facebook.react.ReactApplication
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import com.facebook.react.uimanager.ViewManager
import com.reactnativenavigation.options.LayoutFactory
import com.reactnativenavigation.react.modal.ModalViewManager

class NavigationPackage() : BaseReactPackage() {

    override fun getModule(name: String, context: ReactApplicationContext): NativeModule? {
        val reactApp = context.applicationContext as ReactApplication
        return when (name) {
            "RNNBridgeModule" -> {
                NavigationModule(context, LayoutFactory(reactApp.reactHost))
            }

            else -> {
                null
            }
        }
    }

    override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
        mapOf("RNNBridgeModule" to ReactModuleInfo(
            name = "RNNBridgeModule",
            className = "RNNBridgeModule",
            canOverrideExistingModule = false,
            needsEagerInit = false,
            isCxxModule = false,
            isTurboModule = false
        ))
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return mutableListOf(ModalViewManager(reactContext))
    }
}
