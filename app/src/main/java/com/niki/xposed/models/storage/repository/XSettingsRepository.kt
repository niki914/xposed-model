//package com.niki.breeno.models.storage.repository
//
//import android.annotation.SuppressLint
//import android.app.Application
//import android.content.Context
//import com.niki.breeno.AppApplication
//import com.niki.breeno.models.storage.repository.base.SettingRepositoryForModule
//import com.niki.common.Key
//import com.niki.common.logD
//import com.niki.common.parseToProxyPair
//import com.niki.common.repository.interfaces.ISettingsRepository
//
///**
// * 模块专用
// *
// * 设置项的本地化存储单例 Repository。
// * 这个Repository负责管理应用中大量影响执行逻辑的设置项的读写，
// * 确保这些设置项在整个应用生命周期中只有一个实例，并提供统一的访问接口。
// */
//class XSettingsRepository private constructor(context: Context) :
//    SettingRepositoryForModule(context) {
//
//    companion object {
//        @SuppressLint("StaticFieldLeak")
//        @Volatile
//        private var INSTANCE: ISettingsRepository? = null
//
//        /**
//         * 获取 SettingsRepository 的单例实例。
//         * 采用双重检查锁定（Double-Checked Locking）模式，确保线程安全且高效。
//         */
//        fun getInstance(context: Context): ISettingsRepository =
//            INSTANCE ?: synchronized(this) {
//                val app = context.applicationContext as? Application
//                app?.let {
//                    // 只允许模块访问，如果用自己的 application 就异常
//                    if (app::class.java.name == AppApplication::class.java.name) {
//                        throw IllegalAccessError("这是给模块用的，在主 app 中请用 SettingsRepository")
//                    }
//                }
//                INSTANCE ?: XSettingsRepository(context).also {
//                    INSTANCE = it
//                    logD("XSettingsRepository 已经实例化")
//                }
//            }
//
//        fun getInstance(): ISettingsRepository =
//            INSTANCE ?: throw IllegalArgumentException("XSettingsRepository 未初始化")
//    }
//
//    override fun getAPIKey(): String {
//        return getStringOrDefault(Key.ApiKey)
//    }
//
//    override fun getBaseUrl(): String {
//        return getStringOrDefault(Key.BaseUrl)
//    }
//
//    override fun getModelName(): String {
//        return getStringOrDefault(Key.ModelName)
//    }
//
//    override fun getSystemPrompt(): String {
//        return getStringOrDefault(Key.SystemPrompt)
//    }
//
//    override fun getTimeout(): Long {
//        return getLongOrDefault(Key.Timeout)
//    }
//
//    override fun getProxy(): Pair<String?, Int?> {
//        val proxyString = getStringOrDefault(Key.Proxy) // 如 ${string}:$int
//        return proxyString.parseToProxyPair()
//    }
//
//    override fun getEnableApp(): Boolean {
//        return getBooleanOrDefault(Key.EnableLaunchApp)
//    }
//
//    override fun getEnableUri(): Boolean {
//        return getBooleanOrDefault(Key.EnableLaunchURI)
//    }
//
//    override fun getEnableGetDeviceInfo(): Boolean {
//        return getBooleanOrDefault(Key.EnableGetDeviceInfo)
//    }
//
//    override fun getFallbackToBreeno(): String {
//        return getStringOrDefault(Key.FallbackToBreeno)
//    }
//}