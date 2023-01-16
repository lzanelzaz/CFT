package ru.lzanelzaz.cft.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.lzanelzaz.cft.R
import ru.lzanelzaz.cft.model.BinInfo


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BinItem(bin: BinInfo) {
    Text(stringResource(id = R.string.bin) + ": ${bin.bin}")
    Card(Modifier.padding(horizontal = 4.dp, vertical = 2.dp)) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(weight = 1f)) {
                CheckField(field = bin.scheme, stringRes = R.string.scheme)
                CheckField(field = bin.brand, stringRes = R.string.brand)
                CheckField(field = bin.number, stringRes = R.string.cardNumber)
            }
            Spacer(modifier = Modifier.size(8.dp))

            Column(modifier = Modifier.weight(weight = 1f)) {
                CheckField(field = bin.type, stringRes = R.string.type)
                CheckField(field = bin.prepaid, stringRes = R.string.prepaid)
                CheckField(field = bin.country, stringRes = R.string.country)
            }
            Spacer(modifier = Modifier.size(8.dp))

            Column(modifier = Modifier.weight(weight = 1f)) {
                CheckField(field = bin.bank, stringRes = R.string.bank)
            }
        }
    }
}
