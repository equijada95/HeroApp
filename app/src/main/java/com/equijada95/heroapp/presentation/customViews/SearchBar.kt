package com.equijada95.heroapp.presentation.customViews

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
import com.equijada95.heroapp.R

@Composable
fun SearchBar(
    searchText: String,
    setSearch: (String) -> Unit
) {

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
                                setSearch("")
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
            setSearch(it)
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.search_bar_corner_radius)),
        label = { Text(stringResource(id = R.string.hero_search)) }
    )
}