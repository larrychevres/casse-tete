package com.nymag.beans
import scala.collection.JavaConversions
import org.apache.solr.client.solrj.impl.HttpSolrServer
import org.apache.solr.client.solrj.SolrQuery

abstract class Entity[T <: AnyRef : ClassManifest] {

  def getBeans(queryStr: String, limit: java.lang.Integer = -1) : Seq[T] = {
    
    // Prepare query
    val query = new SolrQuery()
    query.setQuery(queryStr)
    
    if(limit > 0){
        query.setRows(limit)
    }

    getBeans(query)
  }
  
  def getBeans(query : SolrQuery) : Seq[T] = {
    // Execute query
    val server = getServer()
    val rsp = server.query(query)
    
    // shutdown server
    server.shutdown()
    
    // Map results to list of beans
    //FIXME: Use non-deprecated approach
    val beans = rsp.getBeans(classManifest[T].erasure)
    
    // return as sequence
    JavaConversions.asScalaBuffer(beans).asInstanceOf[Seq[T]]
  }
  
  def getBeans() : Seq[T]= {
    getBeans("*:*")
  }
  
  def getBean(queryStr: String) : T = {
    val beans =  getBeans(queryStr, 1)
    
    //FIXME: handle empty result.
    // This will throw ArrayOutOfBoundException if no results are returned
    beans.apply(0);
    
    // Return result
    /*
    val bean = null
    if(!beans.isEmpty){
      // return retrieved label
      beans.apply(0);
    }else{
      // return null bean
    }*/
  }
  def getServer() : HttpSolrServer

}