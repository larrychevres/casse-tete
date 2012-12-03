package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("javascriptRoutes sandbox application."))
  }
  
  def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
        Calculator.add, Calculator.subtract, 
        Persons.echo
      )
    ).as("text/javascript")
  }
  
}