package com.capstone.contact.ui.page
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import com.capstone.contact.vm.FavoriteVM
import com.capstone.contact.vm.VMFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import com.capstone.contact.access.model.Contacts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import com.capstone.contact.di.DependencyInjection
import com.capstone.contact.ui.components.EmptyListState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.contact.ui.state.ViewState



@Composable
fun Favorite(

    toDetail: (Int) -> Unit,

    modifier: Modifier = Modifier,

    vm: FavoriteVM = viewModel(
        factory = VMFactory(DependencyInjection.useRepo())
    )
) {
    vm.uiState.collectAsState(initial = ViewState.Load).value.let { uiState ->
        when (uiState) {
            is ViewState.Load -> {
                vm.getFav()
            }
            is ViewState.Success -> {
                FavoriteInformation(
                    listContacts = uiState.data,
                    toDetail = toDetail,
                    toFav = { id, newState ->
                        vm.getUpdate(id, newState)
                    }
                )
            }
            is ViewState.Bug -> {}
        }
    }
}

@Composable
fun FavoriteInformation(
    listContacts: List<Contacts>,

    toDetail: (Int) -> Unit,
    toFav: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
)
{




    Column(
        modifier = modifier
    ) {
        if (listContacts.isNotEmpty()) {
            ListContacts(
                listContacts = listContacts,
                toFav = toFav,
                contentPaddingTop = 16.dp,
                toDetail = toDetail
            )
        }


        else

        { EmptyListState(information = "")
            Box(modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "",
                    fontSize = 20.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                ) } } } }
