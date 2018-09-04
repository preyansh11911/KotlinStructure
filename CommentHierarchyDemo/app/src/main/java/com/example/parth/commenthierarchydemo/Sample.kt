package com.example.parth.commenthierarchydemo

import com.support.POJOModel

data class Sample(var title: String = "", var desc: String = "") : POJOModel() {
    init {
        Sample.id++
        this.id = Sample.id
        title += this.id
    }

    companion object {
        var id: Long = 0
    }

}