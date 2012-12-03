# Obtaining A Class Object From A Generic Type
  
  
## The Problem
How can we get the class object of a generic type T if we can't do classOf[T]?

## Where
In Entity.scala.

## Note
The workaround uses ClassManifest, which is deprecated in Scala 2.10:
http://pastebin.com/YGwTweyF