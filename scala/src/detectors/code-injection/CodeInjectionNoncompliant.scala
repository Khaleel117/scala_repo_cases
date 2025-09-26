import org.springframework.expression.spel.standard.SpelExpressionParser

class CodeInjectionNoncompliant {
  val parser = new SpelExpressionParser()

  // {fact rule=code-injection@v1.0 defects=1}
  def nonCompliant(): Unit = {
    val untrustedInput = scala.io.StdIn.readLine()
    // Noncompliant: Untrusted input is parsed as an expression.
    val expression = parser.parseExpression(untrustedInput)
  }
  // {/fact}

}