package com.e.tmdb.ui.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.e.tmdb.R

@ExperimentalMaterialApi
@Composable
fun SearchBar(
    modifier: Modifier,
) {
    var inputText by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                disabledLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .fillMaxHeight(),
                    text = "Search...",
                    color = Color.White
                )
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color(R.color.dark_blue).copy(1f),
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun SearchPreview() {
    SearchBar(modifier = Modifier)
}