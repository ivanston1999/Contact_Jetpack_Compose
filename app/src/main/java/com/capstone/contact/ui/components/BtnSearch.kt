package com.capstone.contact.ui.components


import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.ui.unit.dp
import com.capstone.contact.R
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource


@Composable
fun BtnSearch(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
)
{
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = stringResource(R.string.find)
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            cursorColor = MaterialTheme.colors.onSurface,
            leadingIconColor = MaterialTheme.colors.onSurface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.body1,
        placeholder = {
            Text(
                text = stringResource(id = R.string.find),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        },
        modifier = modifier.padding(16.dp).fillMaxWidth().height(56.dp).shadow(4.dp, shape = RoundedCornerShape(24.dp))
    )
}
