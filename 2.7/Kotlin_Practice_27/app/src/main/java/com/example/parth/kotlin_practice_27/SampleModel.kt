package com.example.parth.kotlin_practice_27

import com.support.POJOModel

data class SampleModel(var name: String, var age: Int) : POJOModel(){
    init {
        SampleModel.id++
        this.id=SampleModel.id
        name+=this.id
    }
    companion object {
        var id: Long = 0
    }
}