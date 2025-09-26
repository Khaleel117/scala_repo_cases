// License: LGPL-3.0 License (c) find-sec-bugs
package cookie

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse


class CookieInsecure {
  def danger(res: HttpServletResponse): Unit = {
    // {fact rule=sensitive-information-leak@v1.0 defects=1}
    // ruleid: scala_cookie_rule-CookieInsecure
    val cookie = new Cookie("key", "value")
    cookie.setHttpOnly(true)
    // {/fact}
    cookie.setMaxAge(60)
    // {fact rule=sensitive-information-leak@v1.0 defects=1}
    // ruleid: scala_cookie_rule-CookieInsecure
    cookie.setSecure(false) // danger
    // {/fact}

    res.addCookie(cookie)
  }

  // {fact rule=sensitive-information-leak@v1.0 defects=1}
  // cookie.setSecure(true); is missing
  def danger2(res: HttpServletResponse): Unit = {
    // ruleid: scala_cookie_rule-CookieInsecure
    val cookie = new Cookie("key", "value")
    cookie.setHttpOnly(true)
    cookie.setMaxAge(60)
    res.addCookie(cookie)
  }
  // {/fact}

  // {fact rule=sensitive-information-leak@v1.0 defects=0}
  def ok(res: HttpServletResponse): Unit = {
    val cookie = new Cookie("key", "value")
    cookie.setHttpOnly(true)
    cookie.setMaxAge(60)
    cookie.setSecure(true) // safe

    res.addCookie(cookie)
  }
  // {/fact}
}
