package com.capstone.contact.ui.components

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.rounded.Favorite


@Composable
fun ContactItem(
    id: Int,
    fullName: String,
    hp: String,
    ig: String,
    image: Int,
    toTheFavorite: Boolean,
    favIcon: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(9.dp).fillMaxWidth().wrapContentHeight(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(81.dp).clip(CircleShape).background(Color(0xFF3F51B5))
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = "photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(start = 17.dp).fillMaxWidth()
            ) {
                Text(
                    text = fullName,
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = hp,
                    style = MaterialTheme.typography.caption,
                    color = Color.White
                )
                Text(
                    text = ig,
                    style = MaterialTheme.typography.caption,
                    color = Color.White
                )

            }
        }
        Icon(
            imageVector = if (toTheFavorite) Icons.Rounded.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            tint = if (!toTheFavorite) Color.White else Color.Green,
            modifier = Modifier.align(Alignment.CenterEnd).padding(9.dp).size(35.dp).testTag("fav").clickable { favIcon(id, !toTheFavorite) }
        )
    }
}
