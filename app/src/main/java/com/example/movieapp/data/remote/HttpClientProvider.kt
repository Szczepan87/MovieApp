package com.example.movieapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import javax.inject.Inject

class HttpClientProvider @Inject constructor() {
    val client: HttpClient
        get() = HttpClient {
            install(ContentNegotiation.toString()) {
                provideJson()
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST
                    path(BASE_PATH)
                    parameters.append(API_KEY_PARAMETER, API_KEY_VALUE)
                }
                headers {
                    append(AUTH_KEY_NAME, "$BEARER_KEY_NAME $AUTH_BEARER")
                }
            }
        }

    private fun provideJson() = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    companion object {
        const val API_KEY_PARAMETER = "api_key"
        const val API_KEY_VALUE = "75e0dbf9a2866e285036e4431ea39594"
        const val AUTH_BEARER = ""
        const val AUTH_KEY_NAME = "Authorization"
        const val BASE_PATH = "3/"
        const val BEARER_KEY_NAME = "Bearer"
        const val HOST = "api.themoviedb.org"
    }
}