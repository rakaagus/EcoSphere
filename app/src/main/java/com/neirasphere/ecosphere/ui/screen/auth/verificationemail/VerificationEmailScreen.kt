package com.neirasphere.ecosphere.ui.screen.auth.verificationemail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.components.ButtonProfile
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey

@Composable
fun VerificationEmailScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        VerificationEmailContent(navController = navController)
    }
}

@Composable
fun VerificationEmailContent(navController: NavController, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.image_verif_email),
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(180.dp)
    )
    Text(
        text = "Kode Verifikasi Terkirim!", style = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Bold
        ), modifier = Modifier.padding(bottom = 18.dp)
    )
    Text(
        text = "Yuk, cek emailmu dan masukkan kode verifikasi yang kamu \nterima di sini ya",
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 43.dp)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth().padding(bottom = 43.dp)
    ) {
        OtpFormCode(value = "", onValueChange = {  })
        OtpFormCode(value = "", onValueChange = {  })
        OtpFormCode(value = "", onValueChange = {  })
        OtpFormCode(value = "", onValueChange = {  })
    }
    ButtonProfile(label = "Next", isLogoutButton = false, click = {  }, modifier = Modifier.padding(bottom = 26.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ) {
        Text(text = "Belum menerima email?", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.width(3.dp))
        Text(text = "Kirim Ulang", style = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Bold
        ))
    }
}

@Composable
fun OtpFormCode(
    value: String,
    onValueChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChange },
        modifier = modifier.size(width = 54.dp, height = 40.dp),
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = BlackColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = BlackColor,
            unfocusedContainerColor = NeutralColorGrey,
            focusedContainerColor = NeutralColorGrey
        )
    )
}

@Preview
@Composable
private fun VerificationEmailScreenPrev() {
    VerificationEmailScreen(rememberNavController())
}