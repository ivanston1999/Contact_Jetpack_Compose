package com.capstone.contact.ui.page



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.contact.R
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource


@Composable
fun About(
    modifier: Modifier = Modifier,
) {

    val dicodingEmail = stringResource(R.string.myEmail)
    val nickName = stringResource(R.string.myNicname)

    val phoneNumber = stringResource(R.string.myPN)




    val profilePic = painterResource(id = R.drawable.ivan)
    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(27.dp)

            .border(


                width = 2.dp,
                color = Color(0xFF39FF14),
                shape = RectangleShape

            )

    )

    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        {
            Text(
                text = stringResource(R.string.profile),
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 24.dp),
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .align(Alignment.CenterHorizontally)
            )
            {
                Image(
                    painter = profilePic,
                    contentDescription = stringResource(R.string.pp),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = stringResource(R.string.profile),
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = nickName,
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = stringResource(R.string.phone),
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = phoneNumber,
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 4.dp),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = stringResource(R.string.email),
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = dicodingEmail,
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 4.dp),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.sosmed),
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = stringResource(R.string.twt),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.instagram),
                    contentDescription = stringResource(R.string.ig),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.linkedin),
                    contentDescription = stringResource(R.string.linkedin),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                ) } } }
}
