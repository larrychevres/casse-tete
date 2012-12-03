# HashMap to Bean Mapping with Dozer in Scala
  
  

## Introduction
"Dozer is a Java Bean to Java Bean mapper that recursively copies data from one object to another." - http://dozer.sourceforge.net

## Problem
Mapping from a Map to a Bean fails when the Bean is implemented in Scala.

## Where
Look at the Map to Bean mapping in Application.java. There is one for a Java Bean and another for the equivalent Scala Bean.
The Mapping is successful when we use the Java Bean but not when we use the Scala Bean. 