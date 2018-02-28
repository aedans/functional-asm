functional-asm
==============

[![Download](https://api.bintray.com/packages/aedans/maven/fasm/images/download.svg)](https://bintray.com/aedans/maven/fasm/_latestVersion) 
 
A functional wrapper for [asm](http://asm.ow2.org/) that prioritizes immutability and type safety over performance.

Functional-asm is written in [Kotlin](http://kotlinlang.org), but is designed to be easily usable from Java and other
jvm languages.

Gradle
------

```gradle
repositories {
    maven { url 'https://dl.bintray.com/aedans/maven/' }
}

dependencies {
    compile 'io.github.aedans:fasm:$functional-asm-version'
}
```
