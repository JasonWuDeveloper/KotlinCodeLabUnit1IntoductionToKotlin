package com.codlab.kotlincodelabunit1intoductiontokotlin

import android.content.Context
import androidx.core.content.res.ResourcesCompat

class Presenter(_view: MVPContract.View):MVPContract.Presenter {
    private var view: MVPContract.View = _view
    override fun selectTipResult() {
    }
}