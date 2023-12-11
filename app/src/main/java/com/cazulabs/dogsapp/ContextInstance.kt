package com.cazulabs.dogsapp

import android.content.Context
import androidx.annotation.NonNull


class ContextInstance private constructor(){
    private var context: Context? = null

    companion object {
        val instance: ContextInstance by lazy {
            ContextInstance()
        }
    }

    fun getContext(): Context? {
        return context
    }

    fun setContext(@NonNull context:  Context ) {
        this.context = context
    }
}