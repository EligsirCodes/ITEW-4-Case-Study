package com.example.itew4_casestudy.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.itew4_casestudy.R
import com.example.itew4_casestudy.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun InformationScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            BurgerStackMenuLayout(

                onSettingsClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(Routes.SETTINGS_SCREEN)
                },

                onLogoutClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(Routes.LOGIN_SCREEN) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopBarLayout(
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            bottomBar = { BottomBarLayout( navController = navController, selectionIdentifier = 2) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(1) {
                        CircleLayout(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 5.dp)
                                .size(220.dp)
                                .shadow(
                                    elevation = 10.dp,
                                    shape = CircleShape,
                                    clip = false
                                )
                                .border(
                                    width = 5.dp,
                                    color = Color(red = 13, green = 61, blue = 3),
                                    shape = CircleShape
                                )
                                .clip(CircleShape),
                            innerComposable = {
                                Image(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    painter = painterResource(R.drawable.pnclogo),
                                    contentDescription = "PNC Logo"
                                )
                            }
                        )

                        CardTemplate(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(red = 179, green = 204, blue = 175)
                            ),
                            innerComposable = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    PNCLogoWithNameTemplate(
                                        modifier = Modifier
                                            .fillMaxWidth(.95f)
                                            .padding(top = 20.dp)
                                    )

                                    HorizontalDivider(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                                        thickness = 5.dp,
                                        color = Color(red = 13, green = 61, blue = 3)
                                    )

                                    NormalTextTemplate(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 10.dp),
                                        text = "Pamantasan ng Cabuyao (PnC) is a public university located in Cabuyao City, Laguna, established in 2003 to provide accessible and affordable quality higher education to residents of the city. The university offers a range of undergraduate, senior high school, and technical-vocational programs designed to develop competent, skilled, and socially responsible graduates prepared for professional careers.",
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp
                                    )

                                    CardTemplate(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(red = 13, green = 61, blue = 3)
                                        ),
                                        innerComposable = {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize(),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                EmboldenedTextTemplate(
                                                    modifier = Modifier
                                                        .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 5.dp),
                                                    text = "Mission",
                                                    color = Color.White
                                                )

                                                NormalTextTemplate(
                                                    modifier = Modifier
                                                        .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp),
                                                    text = "As an institution of higher learning, UC(PnC) is committed to equip individuals with knowledge, skills, and values that will enable them to achieve their professional goals & provide leadership and service for national development.",
                                                    color = Color.White,
                                                    textAlign = TextAlign.Center,
                                                    fontSize = 15.sp
                                                )
                                            }
                                        }
                                    )

                                    CardTemplate(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 20.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(red = 13, green = 61, blue = 3)
                                        ),
                                        innerComposable = {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize(),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                EmboldenedTextTemplate(
                                                    modifier = Modifier
                                                        .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 5.dp),
                                                    text = "Vision",
                                                    color = Color.White
                                                )

                                                NormalTextTemplate(
                                                    modifier = Modifier
                                                        .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp),
                                                    text = "A premier institution of higher learning in Region IV, developing globally-competitive and value-laden professionals and leaders instrumental to community development and nation-building.",
                                                    color = Color.White,
                                                    textAlign = TextAlign.Center,
                                                    fontSize = 15.sp
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        )

                        CardTemplate(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(red = 179, green = 204, blue = 175)
                            ),
                            innerComposable = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(end = 10.dp),
                                            imageVector = Icons.Filled.School,
                                            contentDescription = "School Icon",
                                            tint = Color(red = 13, green = 61, blue = 3)
                                        )

                                        EmboldenedTextTemplate(
                                            text = "University College Departments",
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                    HorizontalDivider(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                                        thickness = 5.dp,
                                        color = Color(red = 13, green = 61, blue = 3)
                                    )

                                    CourseBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        circleLayoutModifier = Modifier
                                            .padding(start = 20.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
                                        painter = painterResource(R.drawable.ccs),
                                        text = "College of Computing Studies (CCS)"
                                    )

                                    CourseBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        circleLayoutModifier = Modifier
                                            .padding(start = 20.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
                                        painter = painterResource(R.drawable.coed),
                                        text = "College of Education (COED)"
                                    )

                                    CourseBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        circleLayoutModifier = Modifier
                                            .padding(start = 20.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
                                        painter = painterResource(R.drawable.cbaa),
                                        text = "College of Business, Accountancy and Administration (CBAA)",
                                        fontSize = 15.sp
                                    )

                                    CourseBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        circleLayoutModifier = Modifier
                                            .padding(start = 20.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
                                        painter = painterResource(R.drawable.chas),
                                        text = "College of Health and Allied Sciences (CHAS)"
                                    )

                                    CourseBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        circleLayoutModifier = Modifier
                                            .padding(start = 20.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
                                        painter = painterResource(R.drawable.cas),
                                        text = "College of Arts and Sciences (CAS)"
                                    )

                                    CourseBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 20.dp),
                                        circleLayoutModifier = Modifier
                                            .padding(start = 20.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
                                        painter = painterResource(R.drawable.coe),
                                        text = "College of Engineering (COE)"
                                    )
                                }
                            }
                        )

                        CardTemplate(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(red = 179, green = 204, blue = 175)
                            ),
                            innerComposable = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(end = 10.dp),
                                            imageVector = Icons.Filled.CoPresent,
                                            contentDescription = "Office Icon",
                                            tint = Color(red = 13, green = 61, blue = 3)
                                        )

                                        EmboldenedTextTemplate(
                                            text = "University Offices"
                                        )
                                    }

                                    HorizontalDivider(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                                        thickness = 5.dp,
                                        color = Color(red = 13, green = 61, blue = 3)
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        text = "Office of the University Secretary / Board Secretary",
                                        fontSize = 17.sp
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        text = "Management Information Systems Department",
                                        fontSize = 17.sp
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        text = "Data Protection Department",
                                        fontSize = 17.sp
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        text = "Office of the University Registrar",
                                        fontSize = 17.sp
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        text = "Office of the University Library",
                                        fontSize = 17.sp
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        text = "Student Affairs and Services Department",
                                        fontSize = 17.sp
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        text = "Guidance and Counseling Department",
                                        fontSize = 17.sp
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        text = "Admissions Department",
                                        fontSize = 17.sp
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                                        text = "Placement, Alumni, and Linkage Department",
                                        fontSize = 17.sp
                                    )

                                    OfficeBarLayout(
                                        cardTemplateModifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 20.dp),
                                        text = "Gender and Development Department",
                                        fontSize = 17.sp
                                    )
                                }
                            }
                        )

                        CardTemplate(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(red = 179, green = 204, blue = 175)
                            ),
                            innerComposable = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(end = 10.dp),
                                            imageVector = Icons.Filled.BusinessCenter,
                                            contentDescription = "Management Icon",
                                            tint = Color(red = 13, green = 61, blue = 3)
                                        )

                                        EmboldenedTextTemplate(
                                            text = "Management",
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                    HorizontalDivider(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp),
                                        thickness = 5.dp,
                                        color = Color(red = 13, green = 61, blue = 3)
                                    )

                                    EmboldenedTextTemplate(
                                        text = "Dr. Roberto F. Rañola, Jr.",
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp
                                    )

                                    NormalTextTemplate(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp),
                                        text = "OIC University President",
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp
                                    )

                                    EmboldenedTextTemplate(
                                        text = "Dr. George V. Lambot",
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp
                                    )

                                    NormalTextTemplate(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp),
                                        text = "Vice President, Academics and Student Services University of Cabuyao",
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp
                                    )

                                    EmboldenedTextTemplate(
                                        text = "Ma'am Angelica B. Tan",
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp
                                    )

                                    NormalTextTemplate(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp),
                                        text = "Guidance Counselor",
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        )

                        CardTemplate(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(red = 179, green = 204, blue = 175)
                            ),
                            innerComposable = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(end = 10.dp),
                                            imageVector = Icons.Filled.Contacts,
                                            contentDescription = "Contacts Icon",
                                            tint = Color(red = 13, green = 61, blue = 3)
                                        )

                                        EmboldenedTextTemplate(
                                            text = "Contacts",
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                    HorizontalDivider(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp),
                                        thickness = 5.dp,
                                        color = Color(red = 13, green = 61, blue = 3)
                                    )

                                    EmboldenedTextTemplate(
                                        text = "(049) 832 3033",
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp
                                    )

                                    NormalTextTemplate(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp),
                                        text = "University Hotline",
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp
                                    )

                                    EmboldenedTextTemplate(
                                        modifier = Modifier
                                            .padding(start = 10.dp, end = 10.dp),
                                        text = "Katapatan Mutual Homes, Brgy. Banay-banay, City of Cabuyao, Laguna 4025",
                                        textAlign = TextAlign.Center,
                                        fontSize = 17.sp
                                    )

                                    NormalTextTemplate(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp),
                                        text = "University Address",
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}