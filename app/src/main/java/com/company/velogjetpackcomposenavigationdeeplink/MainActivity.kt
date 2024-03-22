package com.company.velogjetpackcomposenavigationdeeplink

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.company.velogjetpackcomposenavigationdeeplink.ui.theme.VelogJetpackComposeNavigationDeepLinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VelogJetpackComposeNavigationDeepLinkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(
                                    onClick = {
                                        navController.navigate("detail")
                                    }
                                ) {
                                    Text(text = "To detail")
                                }
                            }
                        }
                        composable(
                            route = "detail",
                            deepLinks = listOf(
                                navDeepLink {
                                    uriPattern = "https://geonnuyasha.com/{id}"
                                    action = Intent.ACTION_VIEW
                                }
                            ),
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { entry ->
                            val id = entry.arguments?.getInt("id")
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "The id is $id")
                            }
                        }
                    }

                }
            }
        }
    }
}

// Navigation DeepLink : '앱의 알림을 클릭' || '특정 링크 클릭' 시 앱의 특정 화면으로 바로 이동 할 수 있게 도와준다.
// DeepLink의 종류는 '명시적 딥 링크' 와 '암시적 딥 링크' 이렇게 두개로 나뉘게 된다.
// 명시적 딥링크 : 딥 링크를 충족할 정확한 앱을 지정 해주는 것
// 암시적 딥링크 : 딥 링크를 충족할 정확한 앱을 지정 해주지 않는 것 -> intent filter('앱을 시작 할 수 있음'을 지정)를 적용 해주어야 한다.

