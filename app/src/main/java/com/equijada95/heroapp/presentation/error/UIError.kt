package com.equijada95.heroapp.presentation.error

sealed class UIError(val messageId: Int) {
    class Error(messageId: Int): UIError(messageId)
}
