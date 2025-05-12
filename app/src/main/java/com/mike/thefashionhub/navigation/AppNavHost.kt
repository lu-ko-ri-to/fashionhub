package com.mike.thefashionhub.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mike.thefashionhub.data.UserDatabase
import com.mike.thefashionhub.data.UserPreference
import com.mike.thefashionhub.repository.UserRepository
import com.mike.thefashionhub.ui.theme.screens.auth.RegisterScreen
import com.mike.thefashionhub.ui.theme.screens.auth.LoginScreen
import com.mike.thefashionhub.ui.theme.screens.cart.CartScreen
import com.mike.thefashionhub.ui.theme.screens.explore.ExploreScreen
import com.mike.thefashionhub.ui.theme.screens.intent.IntentScreen
import com.mike.thefashionhub.ui.theme.screens.home.HomeScreen
import com.mike.thefashionhub.ui.theme.screens.others.OthersScreen

import com.mike.thefashionhub.ui.theme.screens.splash.SplashScreen
import com.mike.thefashionhub.ui.theme.viewmodel.AuthViewModel
import com.mike.thefashionhub.ui.theme.screens.start.StartScreen
import com.mike.thefashionhub.ui.theme.screens.products.AddProductScreen
import com.mike.thefashionhub.ui.theme.screens.products.EditProductScreen
import com.mike.thefashionhub.ui.theme.screens.products.ProductAdminListScreen
import com.mike.thefashionhub.ui.theme.screens.products.ProductListScreen
import com.mike.thefashionhub.ui.theme.viewmodel.CartViewModel
import com.mike.thefashionhub.ui.theme.viewmodel.PaymentViewModel
import com.mike.thefashionhub.ui.theme.viewmodel.ProductViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  startDestination: String = ROUT_SPLASH,
  productViewModel: ProductViewModel = viewModel(),
  cartViewModel: CartViewModel = viewModel(),
  paymentViewModel: PaymentViewModel = viewModel(),
  userPreference: UserPreference




  ) {
  val context = LocalContext.current


  NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier
  ) {
    composable(ROUT_HOME) {
      HomeScreen(navController,userPreference,cartViewModel)
    }

    composable(ROUT_INTENT) {
      IntentScreen(navController,userPreference)
    }
    composable(ROUT_START) {
      StartScreen(navController)
    }
    composable(ROUT_EXPLORE) {
      ExploreScreen(navController)
    }
    composable(ROUT_OTHERS) {
      OthersScreen(navController)
    }
    composable(ROUT_SPLASH) {
      SplashScreen(navController,userPreference)
    }
    //favorite


    //cart
    composable(ROUT_CART) {
      CartScreen(cartViewModel)
    }
    //AUTHENTICATION

    // Initialize Room Database and Repository for Authentication
    val appDatabase = UserDatabase.getDatabase(context)
    val authRepository = UserRepository(appDatabase.userDao())
    val authViewModel = AuthViewModel(authRepository,userPreference)
    composable(ROUT_REGISTER) {
      RegisterScreen(authViewModel, navController) {
        navController.navigate(ROUT_LOGIN) {
          popUpTo(ROUT_REGISTER) { inclusive = true }
        }
      }
    }

    composable(ROUT_LOGIN) {
      LoginScreen(authViewModel, navController) {
        navController.navigate(ROUT_HOME) {
          popUpTo(ROUT_LOGIN) { inclusive = true }
        }
      }
    }

    // PRODUCTS
    composable(ROUT_ADD_PRODUCT) {
      AddProductScreen(navController,productViewModel)
    }

    composable(ROUT_PRODUCT_LIST) {
      ProductListScreen(navController,productViewModel)
    }
    composable(ROUT_PRODUCTADMIN_LIST) {
      ProductAdminListScreen(navController,productViewModel)
    }

    composable(
      route = ROUT_EDIT_PRODUCT,
      arguments = listOf(navArgument("productId") { type = NavType.IntType })
    ) { backStackEntry ->
      val productId = backStackEntry.arguments?.getInt("productId")
      if (productId != null) {
        EditProductScreen(productId,navController,productViewModel)
      }
    }




  }
}
