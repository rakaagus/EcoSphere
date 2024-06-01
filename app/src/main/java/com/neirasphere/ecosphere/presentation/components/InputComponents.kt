package com.neirasphere.ecosphere.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.neirasphere.ecosphere.ui.theme.BlackColor
import com.neirasphere.ecosphere.ui.theme.NeutralColorGrey
import com.neirasphere.ecosphere.utils.ActionKeyboard
import com.neirasphere.ecosphere.utils.TypeKeyboard

@Composable
fun AuthForm(
    label: String,
    value: String,
    keyboardType: TypeKeyboard,
    keyboardAction: ActionKeyboard,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val tipeKeyboard = when(keyboardType) {
        TypeKeyboard.EMAIL -> KeyboardType.Email
        TypeKeyboard.NAME -> KeyboardType.Text
    }
    val tipeKeyboardAction = when(keyboardAction){
        ActionKeyboard.NEXT -> ImeAction.Next
        ActionKeyboard.END -> ImeAction.Done
    }

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = BlackColor
            )
        },
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            cursorColor = BlackColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = BlackColor,
            unfocusedContainerColor = NeutralColorGrey,
            focusedContainerColor = NeutralColorGrey
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = tipeKeyboardAction,
            keyboardType = tipeKeyboard
        ),
    )
}

@Composable
fun EditProfileForm(
    label: String,
    value: String,
    keyboardType: TypeKeyboard,
    keyboardAction: ActionKeyboard,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tipeKeyboard = when(keyboardType) {
        TypeKeyboard.EMAIL -> KeyboardType.Email
        TypeKeyboard.NAME -> KeyboardType.Text
    }
    val tipeKeyboardAction = when(keyboardAction){
        ActionKeyboard.NEXT -> ImeAction.Next
        ActionKeyboard.END -> ImeAction.Done
    }

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = BlackColor
            )
        },
        textStyle = MaterialTheme.typography.bodySmall,
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = BlackColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = BlackColor,
            unfocusedContainerColor = NeutralColorGrey,
            focusedContainerColor = NeutralColorGrey
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = tipeKeyboard,
            imeAction = tipeKeyboardAction
        )
    )
}

@Composable
fun PasswordForm(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val passwordVisible = remember {
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = BlackColor
            )
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                "Visibility Icon"
            } else {
                "Visibility Off Icon"
            }

            IconButton(
                onClick = { passwordVisible.value = !passwordVisible.value }
            ) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodySmall,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            cursorColor = BlackColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = BlackColor,
            unfocusedContainerColor = NeutralColorGrey,
            focusedContainerColor = NeutralColorGrey
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
    )
}


@Composable
fun EditPasswordProfileForm(
    label: String,
    value: String,
    keyboardAction: ActionKeyboard,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    val tipeKeyboardAction = when(keyboardAction){
        ActionKeyboard.NEXT -> ImeAction.Next
        ActionKeyboard.END -> ImeAction.Done
    }

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = BlackColor
            )
        },
        textStyle = MaterialTheme.typography.bodySmall,
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = BlackColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = BlackColor,
            unfocusedContainerColor = NeutralColorGrey,
            focusedContainerColor = NeutralColorGrey
        ),
        singleLine = true,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = tipeKeyboardAction
        ),
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                "Visibility Icon"
            } else {
                "Visibility Off Icon"
            }

            IconButton(
                onClick = { passwordVisible.value = !passwordVisible.value }
            ) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
    )
}

