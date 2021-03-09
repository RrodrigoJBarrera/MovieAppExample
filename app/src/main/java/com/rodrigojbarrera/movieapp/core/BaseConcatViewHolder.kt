package com.rodrigojbarrera.movieapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseConcatViewHolder<T>(itemView : View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adapter : T)
}