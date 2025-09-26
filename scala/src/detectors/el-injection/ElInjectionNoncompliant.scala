import javax.el._
import javax.faces.context.FacesContext
import scala.io.StdIn.readLine

class ElInjectionNoncompliant {
    // {fact rule=el-injection@v1.0 defects=1}
    def nonCompliant(): Unit = {
        val userInput = readLine()
        val context = FacesContext.getCurrentInstance
        val expressionFactory = context.getApplication.getExpressionFactory
        val elContext = context.getELContext

        // Noncompliant: User input is directly used in creating or evaluating EL expression.
        val vex = expressionFactory.createValueExpression(elContext, userInput, classOf[Any])
        println(vex.getValue(elContext).toString)
    }
    // {/fact}
}
