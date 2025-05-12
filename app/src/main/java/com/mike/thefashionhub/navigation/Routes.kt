package com.mike.thefashionhub.navigation


const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_START = "start"
const val ROUT_INTENT = "intent"
const val ROUT_EXPLORE = "explore"
const val ROUT_OTHERS = "others"

const val ROUT_FAVOURITES = "favourites"







//authentication
const val ROUT_REGISTER = "Register"
const val ROUT_LOGIN = "Login"

//splash route
const val ROUT_SPLASH = "splash"


//Products

const val ROUT_ADD_PRODUCT = "add_product"
const val ROUT_PRODUCT_LIST = "product_list"
const val ROUT_PRODUCTADMIN_LIST = "product_adminlist"

const val ROUT_EDIT_PRODUCT = "edit_product/{productId}"


// ✅ Helper function for navigation
fun editProductRoute(productId: Int) = "edit_product/$productId"

//cart
const val ROUT_CART = "cart"

