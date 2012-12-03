package controllers

import play.api._
import play.api.mvc._

object Persons extends Controller{
  
  def echo(name: String, surname: String) = Action {
    var msg = "Hello " + name + " " + surname + ". This message was generated in the server, unbelievable!"
    Ok(views.html.personAjaxResult(msg));
  }
}