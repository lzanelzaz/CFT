package ru.lzanelzaz.cft.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import ru.lzanelzaz.cft.R
import ru.lzanelzaz.cft.model.Bank
import ru.lzanelzaz.cft.model.CardNumber
import ru.lzanelzaz.cft.model.Country

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun <T> CheckField(field: T?, stringRes: Int) {
    if (field == null) return
    when (field) {
        is CardNumber -> if (field == CardNumber()) return
        is Country -> if (field == Country()) return
        is Bank -> if (field == Bank()) return
    }
    androidx.compose.material3.Text(
        stringResource(stringRes),
        style = TextStyle(fontStyle = FontStyle.Italic)
    )
    when (field) {
        is String -> androidx.compose.material3.Text(field)
        is Boolean -> Text(field)
        is CardNumber -> Text(field)
        is Country -> Text(field)
        is Bank -> Text(field)
    }
}

@Composable
fun Text(boolean: Boolean) =
    if (boolean) androidx.compose.material3.Text(stringResource(R.string.yes)) else androidx.compose.material3.Text(
        stringResource(R.string.no)
    )

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Text(number: CardNumber) {
    Row {
        Column(modifier = Modifier.weight(weight = 1f)) {
            CheckField(field = number.length, stringRes = R.string.length)
        }
        Column(modifier = Modifier.weight(weight = 1f)) {
            CheckField(field = number.luhn, stringRes = R.string.luhn)
        }
    }
}

@Composable
fun Text(country: Country) {
    val context = LocalContext.current
    Column {
        androidx.compose.material3.Text(country.emoji + " " + country.name)
        ClickableText(text = AnnotatedString(
            "(${stringResource(R.string.latitude)}: ${country.latitude}, ${
                stringResource(
                    R.string.longitude
                )
            }: ${country.longitude})"
        ), onClick = {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("geo:${country.latitude},${country.longitude}")
                    ).setPackage("com.google.android.apps.maps")
                )
            } catch (e: Exception) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}

@Composable
fun Text(bank: Bank) {
    val context = LocalContext.current
    Column {
        androidx.compose.material3.Text(bank.name + if (bank.city == null) "" else (", " + bank.city))
        if (bank.url != null) ClickableText(text = AnnotatedString(bank.url), onClick = {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse("http://" + bank.url)
                    )
                )
            } catch (e: Exception) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        })

        if (bank.phone != null) ClickableText(text = AnnotatedString(bank.phone), onClick = {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_DIAL, Uri.parse("tel:" + bank.phone)
                    )
                )
            } catch (e: Exception) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}