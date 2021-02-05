package com.spqrta.common.tasks

import android.util.LruCache

class TasksLruCache() : LruCache<Int, Task>(3) {


}