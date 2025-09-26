// License: LGPL-3.0 License (c) find-sec-bugs
package ssrf

import java.io.IOException
import java.net._

object SSRF {
  @throws[IOException]
  def testURL(url: String): Unit = {
    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    // ruleid: scala_ssrf_rule-SSRF
    new URL(url).openConnection.connect()
    // {/fact}


    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    // ruleid: scala_ssrf_rule-SSRF
    new URL("http://safe.com").openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(url, 8080))).connect()
    // {/fact}

    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    // ruleid: scala_ssrf_rule-SSRF
    new URL(url).openConnection.getInputStream()
    // {/fact}

    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    // ruleid: scala_ssrf_rule-SSRF
    new URL(url).openConnection.getLastModified()
    // {/fact}


    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    // ruleid: scala_ssrf_rule-SSRF
    new URL(url).openStream()
    // {/fact}


    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    // ruleid: scala_ssrf_rule-SSRF
    new URL(url).getContent()
    // {/fact}



    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    // ruleid: scala_ssrf_rule-SSRF
    new URL(url).getContent(new Array(0))
    // {/fact}
  }

  @throws[IOException]
  @throws[URISyntaxException]
  def testURI(url: String): Unit = {
    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    // ruleid: scala_ssrf_rule-SSRF
    new URI(url).toURL.openConnection.connect
    // {/fact}
  }
}
