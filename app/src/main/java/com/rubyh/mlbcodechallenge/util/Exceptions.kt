package com.rubyh.mlbcodechallenge.util

class NetworkNotAvailableException(message: String) : Exception(message)

class ApiException(message: String, code: Int) : Exception("$message - $code")