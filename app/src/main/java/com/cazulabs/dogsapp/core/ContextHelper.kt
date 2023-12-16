package com.cazulabs.dogsapp.core

import android.content.Context
import androidx.annotation.NonNull


class ContextHelper private constructor(){
    private var context: Context? = null

    companion object {
        val instance: ContextHelper by lazy {
            ContextHelper()
        }
    }

    fun getContext(): Context? {
        return context
    }

    fun setContext(@NonNull context:  Context ) {
        this.context = context
    }
}