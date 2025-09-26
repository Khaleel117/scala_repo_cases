// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=com.amazonaws.aws-java-sdk-simpledb@1.12.187
package inject

import java.io.IOException
import java.util.Arrays

class CommandInjection {
  @throws[IOException]
  def danger(cmd: String): Unit = {
    val r = Runtime.getRuntime
    r.exec(cmd)
    r.exec(Array[String]("test"))
    // {fact rule=os-command-injection@v1.0 defects=1}
    // ruleid: scala_inject_rule-CommandInjection
    r.exec(Array[String]("bash", cmd))
    // {/fact}


    // {fact rule=os-command-injection@v1.0 defects=1}
    // ruleid: scala_inject_rule-CommandInjection
    r.exec(Array[String]("/bin/sh", "-c", cmd))
    // {/fact}
  }

  def danger2(cmd: String): Unit = {
    val b = new ProcessBuilder()
    // {fact rule=os-command-injection@v1.0 defects=1}
    // ruleid: scala_inject_rule-CommandInjection
    b.command(cmd)
    // {/fact}
    b.command("test")
    // {fact rule=os-command-injection@v1.0 defects=1}
    // ruleid: scala_inject_rule-CommandInjection
    b.command(Arrays.asList("/bin/sh", "-c", cmd))
    // {/fact}
  }
}
