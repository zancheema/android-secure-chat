package com.sleekdeveloper.android.securechat.chat

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sleekdeveloper.android.securechat.data.source.domain.ChatMessage

object ChatBinding {
    @BindingAdapter("chat_messages")
    @JvmStatic
    fun setChatMessages(recyclerView: RecyclerView, messages: List<ChatMessage>?) {
        if (messages == null) return
        if (recyclerView.adapter == null) {
            recyclerView.adapter = MessagesListAdapter()
        }
        (recyclerView.adapter as MessagesListAdapter).submitList(messages)
    }
}