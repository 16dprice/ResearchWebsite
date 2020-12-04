package web

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.DefaultHeaders
import io.ktor.routing.Routing

fun Application.appModule() {
    install(DefaultHeaders)
    install(Routing) {
    }
}