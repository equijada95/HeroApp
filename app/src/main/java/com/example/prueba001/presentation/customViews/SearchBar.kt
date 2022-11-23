package com.example.prueba001.presentation.customViews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.prueba001.R

@Composable
fun SearchBar(
    setSearch: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                searchText = ""
                                setSearch(searchText)
                            }
                        )
                )
            }
        },
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_constraint))
            .fillMaxWidth(),
        value = searchText,
        onValueChange = {
            searchText = it
            setSearch(it)
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.search_bar_corner_radius)),
        label = { Text(stringResource(id = R.string.hero_search)) }
    )
}