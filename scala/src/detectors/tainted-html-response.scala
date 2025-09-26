package controllers

import play.api._
import play.api.mvc.{Action, Controller}
import play.twirl.api.Html;

class XssController extends Controller {

  def vulnerable1(value: String) = Action { implicit request: Request[AnyContent] =>
    // {fact rule=cross-site-scripting@v1.0 defects=1}
    // ruleid: tainted-html-response
    Ok(s"Hello $value !").as("text/html")
    // {/fact}
  }

  def vulnerable2(value: String) = Action.async { implicit request: Request[AnyContent] =>
    // {fact rule=cross-site-scripting@v1.0 defects=1}
    // ruleid: tainted-html-response
    Ok("Hello " + value + " !").as("tExT/HtML")
    // {/fact}
  }

  def vulnerable3(value: String, contentType: String) = Action { implicit request: Request[AnyContent] =>
    val bodyVals = request.body.asFormUrlEncoded
    val smth = bodyVals.get("username").head
    // {fact rule=cross-site-scripting@v1.0 defects=1}
    // ruleid: tainted-html-response
    Ok(s"Hello $smth !").as(contentType)
    // {/fact}
  }

  def vulnerable4(value: String) = Action.async(parse.json) { implicit request: Request[AnyContent] =>
    // {fact rule=cross-site-scripting@v1.0 defects=1}
    // ruleid: tainted-html-response
    Ok("Hello " + value + " !").as(ContentTypes.HTML)
    // {/fact}
  }

  def vulnerable5(value: String) = Action(parse.json) {
    // {fact rule=cross-site-scripting@v1.0 defects=1}
    // ruleid: tainted-html-response
    Ok(s"Hello $value !").as(HTML)
    // {/fact}
  }

  def vulnerable6(value:String) = Action { implicit request: Request[AnyContent] =>
    // {fact rule=cross-site-scripting@v1.0 defects=1}
    // ruleid: tainted-html-response
    Ok(views.html.xssHtml.render(Html.apply("Hello "+value+" !")))
    // {/fact}
  }
  
  def vulnerable7(value:String) = Action {
    // {fact rule=cross-site-scripting@v1.0 defects=1}
    // ruleid: tainted-html-response
    Ok(views.html.xssHtml.render(Html.apply("Hello "+value+" !")))
    // {/fact}
  }

  def safeJson(value: String) = Action.async { implicit request: Request[AnyContent] =>
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok("Hello " + value + " !").as("text/json")
    // {/fact}
  }

  def safeTemplate(value:String) = Action {
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok(views.html.template.render(value))
    // {/fact}
  }

  def variousSafe(value: String) = Action { implicit request: Request[AnyContent] =>
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok("Hello "+value+" !")
    // {/fact}
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok(s"Hello $value !").as("text/json")
    // {/fact}
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok("<b>Hello !</b>").as("text/html")
    // {/fact}
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok(views.html.xssHtml.render(Html.apply("<b>Hello !</b>")))

    val escapedValue = org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(value)
    // {/fact}
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok("Hello " + escapedValue + " !")
    // {/fact}
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok("Hello " + escapedValue + " !").as("text/html")

    val owaspEscapedValue = org.owasp.encoder.Encode.forHtml(value)
    // {/fact}
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok("Hello " + owaspEscapedValue + " !")
    // {/fact}
    // {fact rule=cross-site-scripting@v1.0 defects=0}
    // ok: tainted-html-response
    Ok("Hello " + owaspEscapedValue + " !").as("text/html")
    // {/fact}
  }
}
