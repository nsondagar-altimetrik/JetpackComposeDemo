package com.nikunjsondagar.composedemo

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchUsersTopBar(
    searchText: String,
    searchTextPlaceHolder: String,
    onSearchTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 1.dp
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = {
                onSearchTextChange(it)
            },
            placeholder = {
                Text(text = searchTextPlaceHolder, color = Color.White)
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = Color.White
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { }, modifier = Modifier.alpha(1f)) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if(searchText.isEmpty()) {
                       onSearchCloseClicked()
                    } else {
                        onSearchTextChange("")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClicked(searchText)
            }),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.DarkGray.copy(alpha = 1f)
            )
        )
    }

}