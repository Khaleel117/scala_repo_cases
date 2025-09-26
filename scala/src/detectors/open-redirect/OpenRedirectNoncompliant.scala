// {fact rule=open-redirect@v1.0 defects=1}
class OpenRedirectNoncompliant extends HttpServlet {
    def nonCompliant(resp: HttpServletResponse): Unit = {
      println("Enter the URL for redirection:")
      val url = scala.io.StdIn.readLine() 
      // Noncompliant: Untrusted user input used in `resp.addHeader`.
      if (url != null)  resp.addHeader("Location", url)    
  }
}
// {/fact}