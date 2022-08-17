package com.codlab.kotlincodelabunit1intoductiontokotlin

interface MVPContract {
    interface View {
       fun updateTipResult(result: String)
    }
    interface Presenter {
        fun selectTipResult()
    }
}