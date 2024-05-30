package com.neirasphere.ecosphere.presentation.screen.auth.register

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
import com.neirasphere.ecosphere.presentation.components.AuthForm
import com.neirasphere.ecosphere.presentation.components.AuthWith
import com.neirasphere.ecosphere.presentation.components.ButtonAuth
import com.neirasphere.ecosphere.presentation.components.RoundedIconButton
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor

@Composable
fun RegisterScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        RegisterContent(navController = navController)
    }
}

@Composable
fun RegisterContent(navController: NavHostController, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.sign_up), style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ), textAlign = TextAlign.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp, top = 30.dp)
    )
    AuthForm(label = stringResource(id = R.string.title_first_name), value = "", onValueChange = { })
    Spacer(modifier = Modifier.height(17.dp))
    AuthForm(label = stringResource(id = R.string.title_last_name), value = "", onValueChange = { })
    Spacer(modifier = Modifier.height(17.dp))
    AuthForm(label = stringResource(id = R.string.title_email), value = "", onValueChange = { })
    Spacer(modifier = Modifier.height(17.dp))
    AuthForm(label = stringResource(id = R.string.title_password), value = "", onValueChange = { })
    Spacer(modifier = Modifier.height(24.dp))
    ButtonAuth(label = "Sign Up", click = { /*TODO*/ })
    Spacer(modifier = Modifier.height(24.dp))
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
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        val text1 = stringResource(id = R.string.you_have_account) // Mendapatkan teks pertama dari string resources
        val text2 = stringResource(id = R.string.login_in) // Mendapatkan teks kedua dari string resources

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
                navController.navigate(Screen.LoginScreen.route)
            })
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 30.dp)
    ) {
        val text1 = stringResource(id = R.string.term_1) // Mendapatkan teks pertama dari string resources
        val text2 = stringResource(id = R.string.term_2) // Mendapatkan teks kedua dari string resources

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
        ), textAlign = TextAlign.Center,modifier = Modifier
            .padding(start = 2.dp)
            .clickable {

            })
    }
}
