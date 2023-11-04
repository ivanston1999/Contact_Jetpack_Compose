package com.capstone.contact.ui.state

sealed class ViewState<out T: Any?>

{

    data class Success <out T: Any>(val data: T) : ViewState<T>()
    object Load : ViewState<Nothing>()
    data class Bug(val message: String) : ViewState<Nothing>()
}