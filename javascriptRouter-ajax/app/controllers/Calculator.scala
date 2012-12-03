package controllers

import play.api._
import play.api.mvc._

object Calculator extends Controller {
  
  def add(num1: String, num2: String) = Action {
    val answer = Integer.valueOf(num1) + Integer.valueOf(num2);
    Ok(views.html.calculatorAjaxResult(answer));
  }
  
  def subtract(num1: String, num2: String) = Action {
    val answer = Integer.valueOf(num1) - Integer.valueOf(num2);
    Ok(views.html.calculatorAjaxResult(answer));
  }
}