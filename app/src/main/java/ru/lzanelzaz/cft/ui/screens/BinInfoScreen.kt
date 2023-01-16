package ru.lzanelzaz.cft.ui.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.lzanelzaz.cft.BinViewModel
import ru.lzanelzaz.cft.R
import ru.lzanelzaz.cft.State
import ru.lzanelzaz.cft.ui.BinItem


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinInfoScreen(viewModel: BinViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.padding(4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.bin,
            onValueChange = { if (it.length <= 8) viewModel.updateBin(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = state is State.LengthError,
            label = {
                if (state is State.LengthError) Text(stringResource(R.string.lengthErrorLabel))
                else Text(stringResource(R.string.label))
            }
        )

        OutlinedButton(onClick = {
            if (viewModel.bin.length != 8) viewModel.setLengthError()
            else viewModel.getBinInfo()
        }) {
            Text(stringResource(R.string.getInfo))
        }
        if (state is State.Valid) BinItem(bin = (state as State.Valid).binInfo)
        if (state is State.ShowError) Text(stringResource((state as State.ShowError).messageResource))
        if (state is State.SomethingError) Toast.makeText(
            LocalContext.current,
            (state as State.SomethingError).message,
            Toast.LENGTH_LONG
        ).show()
    }
}

