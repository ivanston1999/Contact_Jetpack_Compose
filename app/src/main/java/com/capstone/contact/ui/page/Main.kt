package com.capstone.contact.ui.page
import com.capstone.contact.ui.components.ContactItem
import com.capstone.contact.ui.components.EmptyListState

import com.capstone.contact.vm.VMFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.ui.res.stringResource
import com.capstone.contact.ui.state.ViewState
import com.capstone.contact.vm.HomeVM
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.contact.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.capstone.contact.access.model.Contacts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import com.capstone.contact.di.DependencyInjection
import com.capstone.contact.ui.components.BtnSearch
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier



@Composable
fun Main(
    modifier: Modifier = Modifier,
    viewModel: HomeVM = viewModel(
        factory = VMFactory(DependencyInjection.useRepo())
    ),
    toDetail: (Int) -> Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = ViewState.Load).value.let { uiState ->
        when (uiState) {
            is ViewState.Load -> {
                viewModel.search(query)
            }
            is ViewState.Success -> {
                MainContact(
                    query = query,
                    onQueryChange = viewModel::search,
                    listContacts = uiState.data,
                    toFav = { id, newState ->
                        viewModel.updateContacts(id, newState)
                    },
                    toDetail = toDetail
                )
            }
            is ViewState.Bug-> {}
        }
    }
}

@Composable
fun MainContact(
    query: String,
    onQueryChange: (String) -> Unit,
    listContacts: List<Contacts>,
    toFav: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    toDetail: (Int) -> Unit,
) {
    Column {
        BtnSearch(
            query = query,
            onQueryChange = onQueryChange
        )
        if (listContacts.isNotEmpty()) {
            ListContacts(
                listContacts = listContacts,
                toFav = toFav,
                toDetail = toDetail
            )
        } else {
            EmptyListState(
                information = stringResource(R.string.zero),
                modifier = Modifier.testTag("emptyList")
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)



@Composable
fun ListContacts(

    modifier: Modifier = Modifier,
    listContacts: List<Contacts>,
    toFav: (id: Int, newState: Boolean) -> Unit,
    toDetail: (Int) -> Unit,
    contentPaddingTop: Dp = 0.dp,
) {



    LazyColumn(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
            top = contentPaddingTop
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("lazy_list")
    )


    {
        items(listContacts, key = { it.id }) { item ->
            ContactItem(
                id = item.id,
                fullName = item.fullName,
                hp = item.hp,
                ig = item.ig,
                image = item.img,
                toTheFavorite = item.fav,
                favIcon = toFav,
                modifier = Modifier.animateItemPlacement(tween(durationMillis = 200)).clickable { toDetail(item.id) }.background(Color.Black)
            )
        }
    }
}
