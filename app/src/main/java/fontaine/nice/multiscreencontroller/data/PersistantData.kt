package fontaine.nice.multiscreencontroller.data

import android.content.SharedPreferences
import android.content.res.Resources
import java.util.*


class PersistentData(private val resources: Resources,
                     private val sharedPreferences: SharedPreferences) : SharedPreferences.OnSharedPreferenceChangeListener {
    private val persistentDataChangeListeners: MutableList<OnPersistentDataChangeListener>

    interface OnPersistentDataChangeListener {
        fun onPersistentDataChanged(key: String)
    }

    init {
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        persistentDataChangeListeners = Collections.synchronizedList(ArrayList<OnPersistentDataChangeListener>())
    }

    operator fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    operator fun contains(key: Int): Boolean {
        return sharedPreferences.contains(getKeyName(key))
    }

    fun store(key: String, value: Boolean): PersistentData {
        sharedPreferences.edit().putBoolean(key, value).apply()
        return this
    }

    fun store(key: Int, value: Boolean): PersistentData {
        sharedPreferences.edit().putBoolean(getKeyName(key), value).apply()
        return this
    }

    fun store(key: String, value: String): PersistentData {
        sharedPreferences.edit().putString(key, value).apply()
        return this
    }

    fun store(key: Int, value: String): PersistentData {
        sharedPreferences.edit().putString(getKeyName(key), value).apply()
        return this
    }

    fun store(key: String, value: Int): PersistentData {
        sharedPreferences.edit().putInt(key, value).apply()
        return this
    }

    fun store(key: Int, value: Int): PersistentData {
        sharedPreferences.edit().putInt(getKeyName(key), value).apply()
        return this
    }

    fun store(key: String, value: Long): PersistentData {
        sharedPreferences.edit().putLong(key, value).apply()
        return this
    }

    fun store(key: Int, value: Long): PersistentData {
        sharedPreferences.edit().putLong(getKeyName(key), value).apply()
        return this
    }

    fun store(key: String, value: Float): PersistentData {
        sharedPreferences.edit().putFloat(key, value).apply()
        return this
    }

    fun store(key: Int, value: Float): PersistentData {
        sharedPreferences.edit().putFloat(getKeyName(key), value).apply()
        return this
    }

    // Restores

    fun restore(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun restore(key: Int, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(getKeyName(key), defaultValue)
    }

    fun restore(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun restore(key: Int, defaultValue: String): String? {
        return sharedPreferences.getString(getKeyName(key), defaultValue)
    }

    fun restore(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun restore(key: Int, defaultValue: Int): Int {
        return sharedPreferences.getInt(getKeyName(key), defaultValue)
    }

    fun restore(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    fun restore(key: Int, defaultValue: Long): Long {
        return sharedPreferences.getLong(getKeyName(key), defaultValue)
    }

    fun restore(key: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    fun restore(key: Int, defaultValue: Float): Float {
        return sharedPreferences.getFloat(getKeyName(key), defaultValue)
    }

    // Removes

    fun remove(key: String): PersistentData {
        sharedPreferences.edit().remove(key).apply()
        return this
    }

    fun remove(key: Int): PersistentData {
        sharedPreferences.edit().remove(getKeyName(key)).apply()
        return this
    }

    fun clear(): PersistentData {
        sharedPreferences.edit().clear().apply()
        return this
    }

    fun registerOnPersistentDataChangeListener(listener: OnPersistentDataChangeListener) {
        persistentDataChangeListeners.add(listener)
    }

    fun unregisterOnPersistentDataChangeListener(listener: OnPersistentDataChangeListener) {
        persistentDataChangeListeners.remove(listener)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        for (persistentDataChangeListener in persistentDataChangeListeners) {
            persistentDataChangeListener.onPersistentDataChanged(key)
        }
    }

    private fun getKeyName(key: Int): String {
        return resources.getString(key)
    }
}
