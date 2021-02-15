package com.sleekdeveloper.android.securechat.chats

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sleekdeveloper.android.securechat.data.source.domain.Chat

object ChatsBinding {
    @BindingAdapter("chats")
    @JvmStatic
    fun setChatHeads(recyclerView: RecyclerView, chats: List<Chat>?) {
        if (chats == null) return
        if (recyclerView.adapter == null) {
            recyclerView.adapter = ChatsListAdapter()
        }
        (recyclerView.adapter as ChatsListAdapter).submitList(chats)
    }
}