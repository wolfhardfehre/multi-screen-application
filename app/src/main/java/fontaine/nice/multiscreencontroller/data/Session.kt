package fontaine.nice.multiscreencontroller.data

class Session(private val persistentData: PersistentData) {
    private val cache: MutableMap<Int, Any> = HashMap()

    operator fun contains(key: Int): Boolean {
        val contains = cache.containsKey(key)
        return contains || persistentData.contains(key)
    }

    operator fun set(key: Int, value: Boolean) {
        cache[key] = value
    }

    operator fun set(key: Int, value: String) {
        cache[key] = value
    }

    operator fun set(key: Int, value: Int) {
        cache[key] = value
    }

    operator fun set(key: Int, value: Long) {
        cache[key] = value
    }

    operator fun set(key: Int, value: Float) {
        cache[key] = value
    }

    fun store(key: Int, value: Boolean) {
        set(key, value)
        persistentData.store(key, value)
    }

    fun store(key: Int, value: String) {
        set(key, value)
        persistentData.store(key, value)
    }

    fun store(key: Int, value: Int) {
        set(key, value)
        persistentData.store(key, value)
    }

    fun store(key: Int, value: Long) {
        set(key, value)
        persistentData.store(key, value)
    }

    fun store(key: Int, value: Float) {
        set(key, value)
        persistentData.store(key, value)
    }

    operator fun get(key: Int, defaultValue: Boolean): Boolean {
        var value = cache[key]
        if (value == null) {
            value = persistentData.restore(key, defaultValue)
            cache[key] = value
            return value
        } else {
            return value as Boolean
        }
    }

    operator fun get(key: Int, defaultValue: String): String {
        var value = cache[key]
        return if (value == null) {
            value = persistentData.restore(key, defaultValue)
            cache[key] = value!!
            value
        } else {
            value as String
        }
    }

    operator fun get(key: Int, defaultValue: Int): Int {
        var value = cache[key]
        return if (value == null) {
            value = persistentData.restore(key, defaultValue)
            cache[key] = value
            value
        } else {
            value as Int
        }
    }

    operator fun get(key: Int, defaultValue: Long): Long {
        var value = cache[key]
        return if (value == null) {
            value = persistentData.restore(key, defaultValue)
            cache[key] = value
            value
        } else {
            value as Long
        }
    }

    operator fun get(key: Int, defaultValue: Float): Float {
        var value = cache[key]
        return if (value == null) {
            value = persistentData.restore(key, defaultValue)
            cache[key] = value
            value
        } else {
            value as Float
        }
    }

    fun remove(key: Int) {
        persistentData.remove(key)
        cache.remove(key)
    }

    fun clear() {
        cache.clear()
        persistentData.clear()
    }
}
