package com.spqrta.architecture_sandbox.model

import com.spqrta.architecture_sandbox.Task

interface RetrofitService {
    fun getTasks(callback: (List<Task>) -> Unit)
}