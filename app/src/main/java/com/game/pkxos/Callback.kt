package com.game.pkxos

import androidx.recyclerview.widget.LinearLayoutManager

abstract class Callback {
    abstract fun OnFinishListener()
    var layoutManagers: List<LinearLayoutManager>? = null
}