package com.nymag

import org.dozer.DozerBeanMapper
import com.nymag.beans.PersonJavaBean
import com.nymag.beans.PersonScalaBean
import scala.collection.JavaConverters._

object Main {
  
  
  def main(args: Array[String]): Unit = {

    var mapper = new DozerBeanMapper()
    
    var personMap = new java.util.HashMap[String, String]
    personMap.put("name", "El Hadji")
    personMap.put("surname", "Diouf")
    
    //FIXME: Why doesn't this work with a Scala class
    // If we can get it to work with Scala class then we can use a lazy val instead of having to invoke init().
    val personJavaBean : PersonJavaBean = mapper.map(personMap3.asJava, classOf[PersonJavaBean])
    //val personScalaBean = mapper.map(renditionMap.asJava, classOf[PersonScalaBean])
    
    println("\n\n\nRESULTS:")
    println("Java Bean Mapping. Peson name = " + personJavaBean.name)
   // println("Java Scala Mapping: Bean's vertical1x property = " + renditionsScalaBean.vertical1x)
    
  }

  
  val personMap2 =  scala.collection.immutable.Map(
      "name" -> "El Hadji", 
      "surname"-> "Diouf")
  
  val personMap3: scala.collection.mutable.Map[String, String] = {
    var personMap3 = scala.collection.mutable.Map[String, String]()
    personMap3.put("name", "El Hadji")
    personMap3.put("surname", "Diouf")
    
    personMap3
  }
}