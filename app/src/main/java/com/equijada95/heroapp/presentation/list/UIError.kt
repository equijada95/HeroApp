package com.equijada95.heroapp.presentation.list

sealed class UIError(val messageId: Int? = null) {
    class Error(messageId: Int): UIError(messageId)
    class NoError : UIError()
}
