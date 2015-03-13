# Introduction #

This document describes how to run the **_taslak_** application from scratch.

# Steps #

  * Checkout the project from SVN folowing the [instructions](http://code.google.com/p/taslak/source).
  * **cd taslak**
  * run the command to create the database and insert sample data.
```
mvn hsql:start test
```
  * We can now deploy our project by using maven-jetty-plugin.In the /taslak/web/ directory run the command:
```
mvn hsql:start jetty:run-war -Dmaven.test.skip=true
```
  * You can now browse to the http://localhost:8080/.
    * User name : **admin**
    * Password  : **admin**

  * If you want to make jetty see the changes on the fly, you should follow these steps.

  * in the directory **/taslak/** run the command **mvn war:inplace**
  * delete the directory **/taslak/web/src/main/webapp/WEB-INF/lib**
  * remove the **struts.xml** file in **/taslak/web/src/main/webapp/WEB-INF/classes/**
  * now you can deploy your application by the command **mvn jetty:run** in the **/taslak/web/** directory.

## Additional Notes ##

  * If you alter anything in the /taslak/core/ directory, you should run the command under **/taslak/core/** directory.
```
mvn install -Dmaven.test.skip=true
```
  * Now on the **web** module you can see the changes.

## Running Tests ##

If you would like to run the command above, you can simply remove the "-Dmaven.test.skip=true" parameter