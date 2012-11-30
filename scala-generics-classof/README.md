Obtaining a class object from a generic type
==========

The problem:
How can we get the class object of a generic type T if we can't do classOf[T]?

The workaround uses ClassManifest, which is deprecated:
http://pastebin.com/YGwTweyF

The solution: ?