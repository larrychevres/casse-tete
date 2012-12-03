package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import models.Person
import models.Address

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index(personForm, addressForm))
  }
  
  def savePerson = Action{ implicit request =>
    
    personForm.bindFromRequest.fold(
      hasErrors = { form => 
        
        // Get error messages
        var errMsg : String = ""
        form.errors.seq.foreach(formError =>
          errMsg = errMsg + formError.message + " "
        )
        
        // Send 400 error message
        BadRequest("Error saving the person: " + errMsg)
      }, 
      
      success = { person : Person =>
        // Send 200 success message
        Accepted("Successfully saved the person: " + person.name + " " + person.surname)
     })
  }
  
  def saveAddress = Action{ implicit request =>
    
    addressForm.bindFromRequest.fold(
      hasErrors = { form => 
        
        // Get error messages
        var errMsg : String = ""
        form.errors.seq.foreach(formError =>
          errMsg = errMsg + formError.message + " "
        )
        
        // Send 400 error message
        BadRequest("Error saving the address: " + errMsg)
      }, 
      
      success = { address : Address =>
        // Send 200 success message
        Accepted("Successfully saved the address: " + address.city + " " + address.country)
     })
  }
  
  // Form
  private var personForm : Form[Person] = Form(
    /*
     * The first part of the mapping specifies the fields and how to validate them. 
     * There are several different validations and you can easily add your own.
     * 
     * The second and third parts of the mapping are the functions the form will use to 
     * create a Person model instance from the contents of the form and fill the form from 
     * an existing Person, respectively. 
     * 
     * Our form's fields map directly to the Person class' fields, so we simply use the 
     * apply and unapply methods that the Scala compiler generates for case classes. 
     * 
     * The deconstruction function is used when we fill a form with an existing Person value. 
     * This is useful if we want the load a user from the database and prepare a form to update it.
     */
    mapping(
      //FIXME: how to create custom error messages for nonEmptyText? 
      //Temporary fix: use text.verifying as a workaround.
      //"name" -> nonEmptyText,
      "name" -> text.verifying("name is required.", {!_.isEmpty}),
      "surname" -> text.verifying("Surname is required.", {!_.isEmpty})
    )(Person.apply)(Person.unapply)
  )
  
  
  /*
   * If you're not using case classes or there is no one-to-one mapping between the case 
   * class and the form, you'll have to supply your own functions here.
   */
  private var addressForm : Form[Address] = Form(
    mapping(
      "city" -> text.verifying("city is required.", {!_.isEmpty}),
      "country" -> text.verifying("country is required.", {!_.isEmpty})
    )
    ((city, country) => initAddress(city, country))
    ((address: Address) => Some(address.city, address.country))
  )
  
  def initAddress(city: String, country: String) = {
    var address = new Address
    address.city = city
    address.country = country
    
    address
  }
  
}