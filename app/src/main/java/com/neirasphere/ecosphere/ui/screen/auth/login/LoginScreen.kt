package com.neirasphere.ecosphere.ui.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.ui.components.AuthForm
import com.neirasphere.ecosphere.ui.components.AuthWith
import com.neirasphere.ecosphere.ui.components.ButtonAuth
import com.neirasphere.ecosphere.ui.components.RoundedIconButton
import com.neirasphere.ecosphere.ui.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        LoginContent(navController = navController)
    }

}

@Composable
fun LoginContent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.happy_earth), contentDescription = "LoginImage",
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
    )
    Text(
        text = stringResource(id = R.string.login_in), style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ), textAlign = TextAlign.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp)
    )
    AuthForm(label = stringResource(id = R.string.title_email), value = "", onValueChange = { })
    Spacer(modifier = Modifier.height(10.dp))
    AuthForm(label = stringResource(id = R.string.title_password), value = "", onValueChange = { })
    Text(
        text = stringResource(id = R.string.title_forgot), style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 10.sp,
            textDecoration = TextDecoration.Underline
        ), textAlign = TextAlign.End, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    )
    ButtonAuth(label = "Login", navHostController = navController, click = { navController.navigate(Screen.HomeScreen.route) })
    AuthWith(string = R.string.continue_with)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        RoundedIconButton(imageId = R.drawable.image_google, onClick = {  })
        Spacer(modifier = Modifier.width(4.dp))
        RoundedIconButton(imageId = R.drawable.image_facebook, onClick = {  })
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        val text1 = stringResource(id = R.string.not_have_account) // Mendapatkan teks pertama dari string resources
        val text2 = stringResource(id = R.string.sign_up) // Mendapatkan teks kedua dari string resources

        val combinedText = buildAnnotatedString {
            append(text1)
            append(" ")
            pushStyle(
                SpanStyle(textDecoration = TextDecoration.Underline)
            )
            append(text2)
            pop()
        }
        Text(text = combinedText, style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 10.sp,
            color = BlackColor
        ), modifier = Modifier
            .padding(start = 2.dp)
            .clickable {
                navController.navigate(Screen.RegisterScreen.route)
            })
    }
}

//@Preview
//@Composable
//private fun LoginScreenPrev() {
//    LoginScreen()
//}