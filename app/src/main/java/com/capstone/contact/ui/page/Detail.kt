package com.capstone.contact.ui.page



import com.capstone.contact.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.capstone.contact.di.DependencyInjection
import com.capstone.contact.ui.state.ViewState
import com.capstone.contact.vm.DetailVM
import com.capstone.contact.vm.VMFactory
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp


@Composable
fun Detail
            (
    id: Int,
    back: () -> Unit,
    modifier: Modifier = Modifier,


    vm: DetailVM = viewModel
        (
        factory = VMFactory(DependencyInjection.useRepo())
    )
) {
    vm.uiState.collectAsState(initial = ViewState.Load).value.let { uiState ->
        when (uiState) {
            is ViewState.Load -> {
                vm.getId(id)
            }
            is ViewState.Success -> {
                val data = uiState.data
                allInfo(
                    image = data.img,
                    id = data.id,
                    name = data.fullName,
                    hp = data.hp,
                    address = data.address,
                    ig = data.ig,
                    email = data.email,
                    desc = data.desc,
                    fav = data.fav,
                    back = back,
                    toFav = { id, state ->
                        vm.final(id, state)
                    }
                )
            }
            is ViewState.Bug -> {}
        }
    }
}

@Composable
fun allInfo(
    id: Int,
    name: String,
    hp: String,
    address: String,
    ig: String,
    email: String,
    desc: String,
    @DrawableRes image: Int,
    fav: Boolean,
    back: () -> Unit,
    toFav: (id: Int, state: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("scroll")
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {
                        toFav(id, fav)
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = if (!fav) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                        contentDescription = if (!fav) stringResource(R.string.toFav) else stringResource(
                            R.string.del_fav),
                        tint = if (!fav) Color.Black else Color.Green
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = stringResource(R.string.phone),
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = hp,
                        overflow = TextOverflow.Visible,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = address,
                        overflow = TextOverflow.Visible,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = ig,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier.padding(start = 4.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = email,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
            Text(
                text = desc,
                fontSize = 16.sp,
                lineHeight = 28.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        IconButton(
            onClick = back,
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .align(Alignment.TopStart)
                .size(40.dp)
                .testTag("back")
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
            )
        }
    }
}
