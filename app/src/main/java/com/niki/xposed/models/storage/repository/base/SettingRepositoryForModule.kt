package com.niki.xposed.models.storage.repository.base

import android.content.Context
import com.niki.common.Key
import com.niki.common.logD
import com.niki.common.repository.interfaces.ISettingsRepository

abstract class SettingRepositoryForModule(context: Context) : ContentProviderRepository(context),
    ISettingsRepository {
    protected fun getStringOrDefault(key: Key): String {
        return getString(key.keyId) ?: defaultAs<String>(key)
    }

    protected fun getIntOrDefault(key: Key): Int {
        return getInt(key.keyId) ?: defaultAs<Int>(key)
    }

    protected fun getLongOrDefault(key: Key): Long {
        return getLong(key.keyId) ?: defaultAs<Long>(key)
    }

    protected fun getFloatOrDefault(key: Key): Float {
        return getFloat(key.keyId) ?: defaultAs<Float>(key)
    }

    protected fun getBooleanOrDefault(key: Key): Boolean {
        return getBoolean(key.keyId) ?: defaultAs<Boolean>(key)
    }

    private inline fun <reified T> defaultAs(key: Key): T {
        return (key.default as T).also {
            logD("$key 使用了默认值: $it")
        }
    }
}