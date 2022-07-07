package com.ncode.test2tvapp.retrofit

interface ControllerInterFace {

    fun <T> onSuccess(response:T,method:String)
    fun onFail(message: String?)
}