package com.spqrta.common.model

import android.util.LruCache

class TasksLruCache() : LruCache<Int, Task>(3) {


}