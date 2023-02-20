package com.e.tmdb.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.e.tmdb.R

@Composable
fun ReviewCard() {

    Column() {
        Row(modifier = Modifier.padding(start = 10.dp)) {
            Card(
                shape = CircleShape,
                modifier = Modifier.size(56.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rdj),
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop,
                )
            }

            Column(Modifier.padding(start = 16.dp)) {
                Text(
                    text = "A review by Tony Stark",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Written by Tony Stark on January 12, 2020",
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            text = "When director Jon Favreau and Sarah Halley cast Robert Downey Jr, they glimpsed something magnificent: a more-than-skilled actor who faultlessly portrayed the role of Tony Stark. Despite Favreau's initial decision in choosing a fresh face, he ended up delighted due to his charismatic, natural and comfortable attitude. He did not realise it yet, but he was moulding with the right measures a whole superhero cinematic universe which lasted until today and still goes for more.\n" +
                    "\n" +
                    "The filmmakers took the proper time to introduce a character whose production was undecided since New Line Pictures argu... read the rest.",
            color = Color.Gray
        )

    }


}