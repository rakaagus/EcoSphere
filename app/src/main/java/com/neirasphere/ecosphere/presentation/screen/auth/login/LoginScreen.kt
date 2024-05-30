package com.neirasphere.ecosphere.presentation.screen.auth.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Api.Client
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.components.AuthForm
import com.neirasphere.ecosphere.presentation.components.AuthWith
import com.neirasphere.ecosphere.presentation.components.ButtonAuth
import com.neirasphere.ecosphere.presentation.components.RoundedIconButton
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.utils.Constant.CLIENT

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val googleLoginState = viewModel.stateGoogle.value
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    @Suppress("DEPRECATION")
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.loginWithGoogle(credential)
            } catch (it: ApiException) {
                Toast.makeText(context, "it", Toast.LENGTH_LONG).show()
            }
        }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        LoginContent(
            email = email,
            password = password,
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onLoginClick = {
               navController.navigate(Screen.HomeScreen.route){
                   popUpTo(Screen.LoginScreen.route) {
                       inclusive = true
                   }
               }
            },
            moveToForgot = {},
            onRegisterClick = {
                navController.navigate(Screen.RegisterScreen.route)
            },
            onLoginGoogleClick = {
                 val googleLogin = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                     .requestEmail()
                     .requestIdToken(CLIENT)
                     .build()

                @Suppress("DEPRECATION")
                val googleLoginClient = GoogleSignIn.getClient(context, googleLogin)
                launcher.launch(googleLoginClient.signInIntent)
            },
            onLoginFacebookClick = {}
        )
    }

}

@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginGoogleClick: () -> Unit,
    onLoginFacebookClick: () -> Unit,
    onLoginClick: () -> Unit,
    moveToForgot: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.happy_earth), contentDescription = "LoginImage",
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
    )
    Text(
        text = stringResource(id = R.string.login_in),
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp)
    )
    AuthForm(label = stringResource(id = R.string.title_email), value = email, onValueChange = onEmailChange)
    Spacer(modifier = Modifier.height(10.dp))
    AuthForm(label = stringResource(id = R.string.title_password), value = "", onValueChange = onPasswordChange)
    Text(
        text = stringResource(id = R.string.title_forgot),
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 10.sp,
            textDecoration = TextDecoration.Underline
        ),
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clickable {
                moveToForgot()
            }
    )
    ButtonAuth(label = "Login",
        click = onLoginClick)
    AuthWith(string = R.string.continue_with)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        RoundedIconButton(imageId = R.drawable.image_google, onClick = onLoginGoogleClick)
        Spacer(modifier = Modifier.width(4.dp))
        RoundedIconButton(imageId = R.drawable.image_facebook, onClick = { })
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        val text1 =
            stringResource(id = R.string.not_have_account) // Mendapatkan teks pertama dari string resources
        val text2 =
            stringResource(id = R.string.sign_up) // Mendapatkan teks kedua dari string resources

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
                onRegisterClick()
            })
    }
}

//@Preview
//@Composable
//private fun LoginScreenPrev() {
//    LoginScreen()
//}