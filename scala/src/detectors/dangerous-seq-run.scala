class Foo {
  def run1(command: String, arg1: String) = {
    import sys.process._
    // {fact rule=os-command-injection@v1.0 defects=1}
    // ruleid: dangerous-seq-run
    Seq(command, arg1).!
    // {/fact}
  }

  def run2(command: String) = {
    import sys.process._
    // {fact rule=os-command-injection@v1.0 defects=1}
    // ruleid: dangerous-seq-run
    val result = Seq(command, "--some-arg").!!
    // {/fact}
    return result
  }

  def run3(message: String) = {
    import sys.process._
    // {fact rule=os-command-injection@v1.0 defects=0}
    // ok: dangerous-seq-run
    Seq("ls", "-la").!!
    // {/fact}
  }

  def run4(message: String) = {
    import sys.process._
    // {fact rule=os-command-injection@v1.0 defects=0}
    // ok: dangerous-seq-run
    Seq("sh", "-c", "ls").!!
    // {/fact}
  }

  def run5(message: String) = {
    import sys.process._
    // {fact rule=os-command-injection@v1.0 defects=0}
    // ok: dangerous-seq-run
    Seq(message, "123")
    // {/fact}
  }

  def run6(command: String) = {
    // {fact rule=os-command-injection@v1.0 defects=0}
    // ok: dangerous-seq-run
    val result = Seq(command, "--some-arg").!!
    // {/fact}
    return result
  }
}