package com.spqrta.common.base.display

import androidx.lifecycle.MutableLiveData

abstract class BaseStateViewModel: BaseViewModel() {
    var state = MutableLiveData<State>()

    open fun setInitialState() {
        state.value = JustInitial
    }
}

//todo reusables
open class State
object JustInitial : State()
object JustLoading : State()
object JustSuccess : State()
object JustUnknownError : State()
class JustError(val exception: Throwable) : State()
object JustInvalidData : State()

open class Payload

class UndefinedStateException(state: State): Exception(state.toString())