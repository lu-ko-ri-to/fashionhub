package com.mike.thefashionhub.mpesa

data class STKPushResponse (
    val MerchantRequestID: String,
    val CheckoutRequestID: String,
    val ResponseCode: String,
    val ResponseDescription: String,
    val CustomerMessage: String
)
