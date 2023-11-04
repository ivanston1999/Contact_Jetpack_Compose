package com.capstone.contact.vm


import com.capstone.contact.access.Repo
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.capstone.contact.access.model.Contacts
import com.capstone.contact.ui.state.ViewState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow


class DetailVM(
    private val repo: Repo
) : ViewModel()


{
    private val viewState: MutableStateFlow<ViewState<Contacts>> =
        MutableStateFlow(ViewState.Load)


    val uiState: StateFlow<ViewState<Contacts>>
        get() = viewState




    fun final(id: Int, newState: Boolean) = viewModelScope.launch {
        repo.finalContact(id, !newState)
            .collect { isUpdated ->
                if (isUpdated) getId(id)
            }
    }


    fun getId(id: Int) = viewModelScope.launch {
        viewState.value = ViewState.Load
        viewState.value = ViewState.Success(repo.uniqueId(id))
    }



}