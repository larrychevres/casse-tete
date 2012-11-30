package com.nymag

import com.nymag.beans.Article

object Main {
  def main(args: Array[String]): Unit = {
    
    println("\n\nLATEST FASHION NEWS:\n")
    
    // Iterate through results, display headline.
    val fashionNews = Article.getFashionNewsFeed()
    fashionNews.foreach(fn => println(fn.headline))
  }
}