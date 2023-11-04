package com.capstone.contact.vm


import com.capstone.contact.access.Repo
import com.capstone.contact.access.model.Contacts
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import com.capstone.contact.ui.state.ViewState
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow



class FavoriteVM(

    private val repo: Repo
) : ViewModel()


{


    private val _viewState: MutableStateFlow<ViewState<List<Contacts>>> = MutableStateFlow(ViewState.Load)
    val uiState: StateFlow<ViewState<List<Contacts>>>

        get() = _viewState



    fun getUpdate(id: Int, newState: Boolean)
    {
        repo.finalContact(id, newState)
        getFav()
    }

    fun getFav() = viewModelScope.launch {
        repo.favCon()
            .catch {
                _viewState.value = ViewState.Bug(it.message.toString()) }

            .collect {
                _viewState.value = ViewState.Success(it) } }
}