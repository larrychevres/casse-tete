package com.nymag.beans

import java.lang.Integer
import org.apache.solr.client.solrj.beans.Field
import java.util.Date
import org.apache.solr.client.solrj.impl.HttpSolrServer
import org.apache.solr.client.solrj.SolrQuery
import scala.collection.JavaConversions

case class Article() {
  

  @Field
  val id : String = null

  @Field
  val headline : String = null
  
  @Field 
  val authors : java.util.List[String] = null
  
  @Field("short_headline")
  val shortHeadline : String = null
  
  @Field
  val body : String = null
  
  @Field
  val thumbnail : String = null
  
  @Field
  val popular : Boolean = false
  
  @Field("type")
  val typeCode : Integer = null
  
  @Field("featured_type")
  val featuredTypeCode : Integer = null
  
  @Field("datetime")
  val publishDate : Date = null
  
  @Field("updated_at")
  val updateDate : Date = null

}

object Article extends Entity[Article]{
  
  def getFashionNewsFeed() = {
    val query = new SolrQuery()
    query.setQuery("category:2")
    query.setSortField("datetime", SolrQuery.ORDER.desc);
    query.setRows(30)
    
    getBeans(query)
  }
  
  def getServer() = {
    val solrUrl = "http://ec2.qa.nymetro.com:8080/solr"
    val schema = "nym"
    val url = solrUrl + "/" + schema
    new HttpSolrServer(url)
  }
}