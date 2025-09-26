import java.io.IOException
import java.util.Arrays

class OsCommandInjectionCompliant {
    // {fact rule=os-command-injection@v1.0 defects=0}
    def compliant(message: String) = {
        import sys.process._
        // Compliant: The command being executed is constant and does not rely on user input, mitigating the risk of command injection.
        Seq("ls", "-la").!!
    }
    // {/fact}
}