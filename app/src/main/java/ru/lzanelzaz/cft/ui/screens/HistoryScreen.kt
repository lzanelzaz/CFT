package ru.lzanelzaz.cft.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.lzanelzaz.cft.BinViewModel
import ru.lzanelzaz.cft.R
import ru.lzanelzaz.cft.ui.BinItem

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HistoryScreen(viewModel: BinViewModel = hiltViewModel()) {
    val bins by viewModel.data.collectAsState()
    if (bins.isEmpty()) Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.noHistory))
    }
    else
        LazyColumn {
            items(
                items = bins,
                itemContent = {
                    BinItem(bin = it)
                })
        }
}

