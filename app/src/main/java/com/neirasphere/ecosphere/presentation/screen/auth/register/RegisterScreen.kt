package com.neirasphere.ecosphere.presentation.screen.auth.register

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.neirasphere.ecosphere.R
import com.neirasphere.ecosphere.presentation.components.AuthForm
import com.neirasphere.ecosphere.presentation.components.AuthWith
import com.neirasphere.ecosphere.presentation.components.PasswordForm
import com.neirasphere.ecosphere.presentation.components.RoundedIconButton
import com.neirasphere.ecosphere.presentation.navigation.Screen
import com.neirasphere.ecosphere.presentation.screen.auth.component.ButtonAuth
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.PrimaryColor
import com.neirasphere.ecosphere.utils.ActionKeyboard
import com.neirasphere.ecosphere.utils.Constant
import com.neirasphere.ecosphere.utils.TypeKeyboard

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val callbackManager = remember {
        CallbackManager.Factory.create()
    }

    var isLoading = viewModel.registerState.collectAsState().value.isLoading
    var isSuccess = viewModel.registerState.collectAsState().value.isSuccess
    var isLoadingDialogShow by remember { mutableStateOf(false) }
    var isSuccessDialogShow by remember { mutableStateOf(false) }

    if(isLoading){
        LoadingDialog(onDismissRequest = { isLoadingDialogShow = false })
    }

        if(isSuccess){
        DialogLoginSuccess(onDismissRequest = { isSuccessDialogShow = false }, moveToLogin = {
            navController.navigate(Screen.LoginScreen.route){
                popUpTo(Screen.RegisterScreen.route){
                    inclusive = true
                }
            }
        })
    }

    @Suppress("DEPRECATION")
    val facebookLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            callbackManager.onActivityResult(result.resultCode, result.resultCode, result.data)
        }

    LoginManager.getInstance().registerCallback(callbackManager, object :
        FacebookCallback<LoginResult> {
        override fun onCancel() {
            Toast.makeText(context, "Facebook Sign-In cancelled", Toast.LENGTH_SHORT).show()
        }

        override fun onError(error: FacebookException) {
            Toast.makeText(context, "Facebook Sign-In failed: ${error.message}", Toast.LENGTH_SHORT)
                .show()
        }

        override fun onSuccess(result: LoginResult) {
            result.accessToken.let {
                viewModel.loginWithFacebook(it)
            }
        }
    })

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
        RegisterContent(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onFirstNameChange = { firstName = it },
            onLastNameChange = { lastName = it },
            onLoginFacebookClick = {
                LoginManager.getInstance().logInWithReadPermissions(
                    context as Activity,
                    listOf("email", "public_profile")
                )
            },
            onLoginGoogleClick = {
                val googleLogin = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(Constant.CLIENT)
                    .build()

                @Suppress("DEPRECATION")
                val googleLoginClient = GoogleSignIn.getClient(context, googleLogin)
                launcher.launch(googleLoginClient.signInIntent)
            },
            onRegisterClick = {
                viewModel.register(firstName, lastName, email, password)
            },
            moveToLogin = {
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(Screen.RegisterScreen.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

@Composable
fun RegisterContent(
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onLoginGoogleClick: () -> Unit,
    onLoginFacebookClick: () -> Unit,
    onRegisterClick: () -> Unit,
    moveToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {

    val isEnableButton =
        if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && password.isNotBlank()) true else false

    Text(
        text = stringResource(id = R.string.sign_up),
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp, top = 30.dp)
    )
    AuthForm(
        label = stringResource(id = R.string.title_first_name),
        value = firstName,
        keyboardAction = ActionKeyboard.NEXT,
        keyboardType = TypeKeyboard.NAME,
        onValueChange = onFirstNameChange
    )
    Spacer(modifier = Modifier.height(17.dp))
    AuthForm(
        label = stringResource(id = R.string.title_last_name),
        value = lastName,
        keyboardAction = ActionKeyboard.NEXT,
        keyboardType = TypeKeyboard.NAME,
        onValueChange = onLastNameChange
    )
    Spacer(modifier = Modifier.height(17.dp))
    AuthForm(
        label = stringResource(id = R.string.title_email),
        value = email,
        keyboardAction = ActionKeyboard.NEXT,
        keyboardType = TypeKeyboard.EMAIL,
        onValueChange = onEmailChange
    )
    Spacer(modifier = Modifier.height(17.dp))
    PasswordForm(
        label = stringResource(id = R.string.title_password),
        value = password,
        onValueChange = onPasswordChange
    )
    Spacer(modifier = Modifier.height(24.dp))
    ButtonAuth(label = "Sign Up", click = { onRegisterClick() }, isDisable = isEnableButton)
    Spacer(modifier = Modifier.height(24.dp))
    AuthWith(string = R.string.continue_with)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        RoundedIconButton(imageId = R.drawable.image_google, onClick = {
            onLoginGoogleClick()
        })
        Spacer(modifier = Modifier.width(4.dp))
        RoundedIconButton(imageId = R.drawable.image_facebook, onClick = {
            onLoginFacebookClick()
        })
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        val text1 =
            stringResource(id = R.string.you_have_account) // Mendapatkan teks pertama dari string resources
        val text2 =
            stringResource(id = R.string.login_in) // Mendapatkan teks kedua dari string resources

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
                moveToLogin()
            })
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 30.dp)
    ) {
        val text1 =
            stringResource(id = R.string.term_1) // Mendapatkan teks pertama dari string resources
        val text2 =
            stringResource(id = R.string.term_2) // Mendapatkan teks kedua dari string resources

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
        ), textAlign = TextAlign.Center, modifier = Modifier
            .padding(start = 2.dp)
            .clickable {

            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            CircularProgressIndicator(
                modifier = Modifier,
                color = PrimaryColor,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}


@Composable
fun DialogLoginSuccess(
    onDismissRequest: () -> Unit,
    moveToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        text = {
            Text(
                text = "Yey, Berhasil Login",
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        confirmButton = {
            TextButton(onClick = moveToLogin) {
                Text(
                    text = "Go to Home",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp
                    ),
                    color = PrimaryColor
                )
            }
        }
    )
}
