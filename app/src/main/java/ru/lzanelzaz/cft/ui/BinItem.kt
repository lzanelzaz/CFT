package ru.lzanelzaz.cft.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import ru.lzanelzaz.cft.R
import ru.lzanelzaz.cft.model.Bank
import ru.lzanelzaz.cft.model.BinInfo
import ru.lzanelzaz.cft.model.CardNumber
import ru.lzanelzaz.cft.model.Country
import ru.lzanelzaz.cft.ui.theme.mediumTextStyle
import ru.lzanelzaz.cft.ui.theme.smallTextStyle


@Composable
fun BinItem(bin: BinInfo) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(stringResource(R.string.scheme), style = mediumTextStyle)
                Text(bin.scheme, style = mediumTextStyle)

                Text(stringResource(R.string.brand), style = mediumTextStyle)
                Text(bin.brand, style = mediumTextStyle)

                if (bin.number != CardNumber()) {
                    Text(
                        stringResource(R.string.cardNumber),
                        style = mediumTextStyle
                    )
                    Text(bin.number)
                }
            }
            Column {
                Text(stringResource(R.string.type), style = mediumTextStyle)
                Text(bin.type, style = mediumTextStyle)
                if (bin.prepaid != null) {
                    Text(
                        stringResource(R.string.prepaid),
                        style = mediumTextStyle
                    )
                    Text(bin.prepaid.toText(), style = mediumTextStyle)
                }
                if (bin.country != Country()) {
                    Text(stringResource(R.string.country), style = mediumTextStyle)
                    Text(bin.country)
                }
            }
            Column {
                if (bin.bank != Bank()) {
                    Text(stringResource(R.string.bank), style = mediumTextStyle)
                    Text(bin.bank)
                }
            }
        }
    }
}

@Composable
fun Boolean.toText(): String =
    if (this) stringResource(R.string.yes) else stringResource(R.string.no)

@Composable
fun Text(number: CardNumber) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        if (number.length != null) {
            Column {
                Text(
                    stringResource(R.string.length),
                    style = smallTextStyle
                )
                Text(number.length, style = mediumTextStyle)
            }
        }

        if (number.luhn != null) {
            Column {
                Text(
                    stringResource(R.string.luhn),
                    style = smallTextStyle
                )
                Text(number.luhn.toText(), style = mediumTextStyle)
            }
        }
    }

}

@Composable
fun Text(country: Country) {
    val context = LocalContext.current
    Column {
        Text(country.emoji + " " + country.name, style = mediumTextStyle)
        ClickableText(
            text = AnnotatedString(
                "(${stringResource(R.string.latitude)}: ${country.latitude}, ${
                    stringResource(
                        R.string.longitude
                    )
                }: ${country.longitude})"
            ), onClick = {
                val gmmIntentUri =
                    Uri.parse("google.streetview:cbll=${country.latitude},${country.longitude}")
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        gmmIntentUri
                    ).setPackage("com.google.android.apps.maps")
                )
            }, style = smallTextStyle
        )
    }
}

@Composable
fun Text(bank: Bank) {
    val context = LocalContext.current
    Column {
        Text(
            bank.name + if (bank.city == null) "" else (", " + bank.city),
            style = mediumTextStyle
        )
        if (bank.url != null)
            ClickableText(text = AnnotatedString(bank.url), style = mediumTextStyle,
                onClick = {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse("http://" + bank.url)
                        )
                    )
                })

        if (bank.phone != null)
            ClickableText(text = AnnotatedString(bank.phone), style = mediumTextStyle,
                onClick = {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_DIAL, Uri.parse("tel:" + bank.phone)
                        )
                    )
                })
    }
}