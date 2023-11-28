package com.dxxy.game2

class jdnbdjnfnbc_Point(var x: Int, var y: Int) {
    enum class STATUS {
        STATUS_OFF, STATUS_IN, STATUS_ON
    }

    var status: STATUS? = null

    fun setXY(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
} 