package web

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.DefaultHeaders
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.route
import io.ktor.routing.get

fun Application.appModule() {
    install(DefaultHeaders)
    install(Routing) {
        route("/") {
            get {
                call.respondText("hello")
            }
        }
        route("/home") {
            get {
                call.respondText("home")
            }
        }
        route("/knot") {
            get {
                call.respondText("knot")
            }
        }
    }
}