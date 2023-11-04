package com.capstone.contact.vm
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.catch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.contact.access.Repo
import com.capstone.contact.access.model.Contacts
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.StateFlow
import com.capstone.contact.ui.state.ViewState
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.launch


class HomeVM(
    private val repo: Repo
) : ViewModel()

{

    private val _ViewState: MutableStateFlow<ViewState<List<Contacts>>> =
        MutableStateFlow(ViewState.Load)



    val uiState: StateFlow<ViewState<List<Contacts>>>
        get() = _ViewState



    private val _queryFind = mutableStateOf("")
    val query: State<String> get() = _queryFind
    fun updateContacts(id: Int, newState: Boolean) = viewModelScope.launch{
        repo.finalContact(id, newState)
            .collect { isUpdated ->
                if (isUpdated) search(_queryFind.value) } }



    fun search(newQuery: String) = viewModelScope.launch {
        _queryFind.value = newQuery
        repo.findContact(_queryFind.value)


            .catch{
                _ViewState.value = ViewState.Bug(it.message.toString())
            }
            .collect{
                _ViewState.value = ViewState.Success(it) } }


}