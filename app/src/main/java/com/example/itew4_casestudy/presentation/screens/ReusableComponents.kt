package com.example.itew4_casestudy.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.itew4_casestudy.R
import com.example.itew4_casestudy.navigation.Routes

//LAYOUTS
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLayout(
    onClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        navigationIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    modifier = Modifier
                        .size(80.dp),
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu Icon",
                    tint = Color.White
                )
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleLayout(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .height(35.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = CircleShape,
                            clip = false
                        )
                        .border(
                            width = 2.dp,
                            color = Color(red = 179, green = 204, blue = 175),
                            shape = CircleShape
                        )
                        .clip(CircleShape),
                    innerComposable = {
                        Image(
                            modifier = Modifier
                                .fillMaxHeight(),
                            painter = painterResource(R.drawable.pnclogo),
                            contentDescription = "PNC Logo"
                        )
                    }
                )

                Text(
                    text = "PNC Buddy",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(red = 13, green = 61, blue = 3),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun BottomBarLayout(
    navController: NavController,
    selectionIdentifier: Int
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp),
        containerColor = Color(red = 13, green = 61, blue = 3)
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = if (selectionIdentifier == 1) Icons.Filled.Announcement else Icons.Outlined.Announcement,
                    contentDescription = "Announcement Icon",
                    tint = Color(red = 179, green = 204, blue = 175)
                )
            },
            selected = false,
            onClick = {
                navController.navigate(Routes.ANNOUNCEMENT_SCREEN)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = if (selectionIdentifier == 2) Icons.Filled.School else Icons.Outlined.School,
                    contentDescription = "Campus Info Icon",
                    tint = Color(red = 179, green = 204, blue = 175)
                )
            },
            selected = false,
            onClick = {
                navController.navigate(Routes.INFORMATION_SCREEN)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = if (selectionIdentifier == 3) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home Icon",
                    tint = Color(red = 179, green = 204, blue = 175)
                )
            },
            selected = false,
            onClick = {
                navController.navigate(Routes.DASHBOARD_SCREEN)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = if (selectionIdentifier == 4) Icons.Filled.Task else Icons.Outlined.Task,
                    contentDescription = "Tasks Icon",
                    tint = Color(red = 179, green = 204, blue = 175)
                )
            },
            selected = false,
            onClick = {
                navController.navigate(Routes.TASK_SCREEN)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = if (selectionIdentifier == 5) Icons.Filled.Settings else Icons.Outlined.Settings,
                    contentDescription = "Settings Icon",
                    tint = Color(red = 179, green = 204, blue = 175)
                )
            },
            selected = false,
            onClick = {
                navController.navigate(Routes.SETTINGS_SCREEN)
            }
        )
    }
}

@Composable
fun BurgerStackMenuLayout(
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.7f)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmboldenedTextTemplate(
            modifier = Modifier
                .padding(top = 50.dp),
            text = "Menu",
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
            thickness = 5.dp,
            color = Color(red = 13, green = 61, blue = 3)
        )

        CardTemplate(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
            onClick = onSettingsClick,
            colors = CardDefaults.cardColors(
                containerColor = Color(red = 179, green = 204, blue = 175)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(45.dp)
                        .padding(10.dp),
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings Icon",
                    tint = Color(red = 13, green = 61, blue = 3)
                )

                EmboldenedTextTemplate(
                    text = "Settings",
                    textAlign = TextAlign.Center,
                    color = Color(red = 13, green = 61, blue = 3),
                    fontSize = 20.sp
                )
            }
        }

        CardTemplate(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
            onClick = onLogoutClick,
            colors = CardDefaults.cardColors(
                containerColor = Color(red = 179, green = 204, blue = 175)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(45.dp)
                        .padding(10.dp),
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = "Log Out Icon",
                    tint = Color(red = 13, green = 61, blue = 3)
                )

                EmboldenedTextTemplate(
                    text = "Logout",
                    textAlign = TextAlign.Center,
                    color = Color(red = 13, green = 61, blue = 3),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun CircleLayout(
    modifier: Modifier = Modifier,
    innerComposable: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {
        innerComposable()
    }
}

@Composable
fun TextFieldLayout(
    layoutLabel: String,
    innerComposable: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 3.dp, bottom = 3.dp),
            text = layoutLabel,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color(red = 13, green = 61, blue = 3)
        )

        innerComposable()
    }
}

@Composable
fun CourseBarLayout(
    cardTemplateModifier: Modifier = Modifier,
    circleLayoutModifier: Modifier = Modifier,
    painter: Painter,
    text: String,
    fontSize: TextUnit = 17.sp
) {
    CardTemplate(
        modifier = cardTemplateModifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(red = 13, green = 61, blue = 3)
        ),
        innerComposable = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleLayout(
                    modifier = circleLayoutModifier
                        .size(70.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = CircleShape,
                            clip = false
                        )
                        .border(
                            width = 3.dp,
                            color = Color(red = 179, green = 204, blue = 175),
                            shape = CircleShape
                        )
                        .clip(CircleShape),
                    innerComposable = {
                        Image(
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = painter,
                            contentDescription = "College Logo"
                        )
                    }
                )

                EmboldenedTextTemplate(
                    modifier = Modifier
                        .padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
                    text = text,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = fontSize
                )
            }
        }
    )
}

@Composable
fun OfficeBarLayout(
    cardTemplateModifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 20.sp
) {
    CardTemplate(
        modifier = cardTemplateModifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(red = 13, green = 61, blue = 3)
        ),
        innerComposable = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EmboldenedTextTemplate(
                    modifier = Modifier
                        .padding(15.dp),
                    text = text,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = fontSize
                )
            }
        }
    )
}

@Composable
fun LogInOrSignUpLayout(
    initialText: String,
    underlinedText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = initialText,
            fontSize = 15.sp
        )

        Text(
            modifier = Modifier
                .clickable(
                    onClick = onClick
                ),
            text = underlinedText,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            color = Color(red = 13, green = 61, blue = 3),
            fontSize = 15.sp
        )
    }
}

@Composable
fun OptionLayout(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    optionText: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier,
            imageVector = imageVector,
            contentDescription = "Option Icon",
            tint = Color(red = 179, green = 204, blue = 175)
        )

        Text(
            text = optionText,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
    }
}

//TEMPLATES
@Composable
fun EmboldenedTextTemplate(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Left,
    fontSize: TextUnit = 25.sp,
    color: Color = Color(red = 13, green = 61, blue = 3)
) {
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.ExtraBold,
        color = color,
        textAlign = textAlign,
        fontSize = fontSize
    )
}

@Composable
fun NormalTextTemplate(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Left,
    fontSize: TextUnit = 20.sp,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        fontSize = fontSize
    )
}

@Composable
fun CardTemplate(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    colors: CardColors = CardDefaults.cardColors(
        containerColor = Color(red = 13, green = 61, blue = 3)
    ),
    innerComposable: @Composable () -> Unit
) {
    if (onClick != null) {
        Card(
            modifier = modifier,
            onClick = onClick,
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = colors
        ) {
            innerComposable()
        }
    } else {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = colors
        ) {
            innerComposable()
        }
    }
}

@Composable
fun ButtonTemplate(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String,
    fontSize: TextUnit = 20.sp
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(red = 13, green = 61, blue = 3),
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 12.dp
        )
    ) {
        Text(
            text = buttonText,
            fontWeight = FontWeight.ExtraBold,
            fontSize = fontSize
        )
    }
}

@Composable
fun TextFieldTemplate(
    value: String,
    valueUpdate: (String) -> Unit,
    textFieldLabel: String,
    icon: ImageVector
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        value = value,
        onValueChange = valueUpdate,
        placeholder = {
            Text(
                text = textFieldLabel
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "TextField Icon",
                tint = Color(0xFF0D3D03)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(red = 13, green = 61, blue = 3),
            unfocusedBorderColor = Color(red = 13, green = 61, blue = 3)
        )
    )
}

@Composable
fun PasswordTextFieldTemplate(
    value: String,
    valueUpdate: (String) -> Unit,
    textFieldLabel: String,
    leadingIcon: ImageVector
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        value = value,
        onValueChange = valueUpdate,
        placeholder = {
            Text(
                text = textFieldLabel
            )
        },

        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "Password Icon",
                tint = Color(0xFF0D3D03)
            )
        },

        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff,
                    contentDescription = if (passwordVisible)
                        "Hide Password"
                    else
                        "Show Password",
                    tint = Color(0xFF0D3D03)
                )
            }
        },

        visualTransformation = if (passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF0D3D03),
            unfocusedBorderColor = Color(0xFF0D3D03)
        )
    )
}

@Composable
fun PNCLogoWithNameTemplate(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.pnclogowithname),
        contentDescription = "PNC Logo with Name"
    )
}