package com.support

open class POJOModel {
    var id: Long = 0
        get() = field
        set(value) {
            field = value
        }

    var isLoader: Boolean = false
        get() = field
        set(value) {
            field = value
        }
}