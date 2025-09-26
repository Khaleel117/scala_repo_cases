import java.io.IOException
import dispatch._
import Defaults._
import scala.io.{Codec, Source}
import scalaj.http.{Http, Token}
import scala.io.StdIn

object ServerSideRequestForgeryNoncompliant {
    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    def nonCompliant(): Unit = {
        val request_url = scala.io.StdIn.readLine()
        // Noncompliant: Potential server-side request forgery (SSRF) vulnerability as 'request_url' is controlled by user input.
        val req = url(request_url)
        val data = Http.default(req OK as.String)
        return data
    }
    // {/fact}
}