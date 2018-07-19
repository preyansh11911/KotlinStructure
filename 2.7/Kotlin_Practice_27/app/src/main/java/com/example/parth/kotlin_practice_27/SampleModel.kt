package com.example.parth.kotlin_practice_27

import com.support.POJOModel

data class SampleModel(var name: String, var age: Int) : POJOModel() {
    override var id: Long = 0
        get() = field
        set(value) {field = value}
    override var isLoader: Boolean = false
        get() = field
        set(value) {field = value}
}