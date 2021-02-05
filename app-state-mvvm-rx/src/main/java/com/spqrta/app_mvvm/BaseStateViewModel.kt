package com.spqrta.app_mvvm

import android.arch.lifecycle.MutableLiveData

abstract class BaseStateViewModel: BaseViewModel() {
    var state = MutableLiveData<State()

    open fun reset() {
        state.value = JustInitial
    }
}

open class State
object JustInitial : State()
object JustLoading : State()
object JustSuccess : State()
object JustUnknownError : State()
class JustError(val exception: Throwable) : State()
object JustInvalidData : State()

open class Payload

class UndefinedStateException(state: State): Exception(state.toString())