package controllers

import org.dozer.DozerBeanMapper
import scala.collection.JavaConverters._
import play.api._
import play.api.mvc._
import models.PersonJavaBean
import models.PersonScalaBean

object Application extends Controller {
  
  def index = Action {
    
    var mapper = new DozerBeanMapper()
    
    var personMap = new java.util.HashMap[String, String]
    personMap.put("name", "El Hadji")
    personMap.put("surname", "Diouf")
    
    //FIXME: Why doesn't this work with a Scala class
    // If we can get it to work with Scala class then we can use a lazy val instead of having to invoke init().
    val personJavaBean = mapper.map(personMap, classOf[PersonJavaBean])
    val personScalaBean = mapper.map(personMap, classOf[PersonScalaBean])
    
    println("\n\n\nRESULTS:")
    println("Java Bean Mapping. Peson name = " + personJavaBean.name)
    
    Ok(views.html.index(personJavaBean, personScalaBean))
  }
  
}