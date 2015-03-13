Taslak project has several sub-modules, located in different sub folders.
  * **corelib** (ida-corelib) : The common library for all projects. Basic utility classes reside in this module.
  * **weblib** (ida-weblib) : The common web library for all web based projects.  Web utility classes located in this module.
  * **mesken-core** : Mesken is the user management project/library for IDA projects. This module contains basic CRUD operations for user management operations.
  * **mesken-weblib** : Web library for mesken based applications. (See the note on the bottom of the page for dependencies)
  * **mesken-webapp** : Web application mesken.
  * **core** (taslak-core) : Core module containing basic CRUD operations for Taslak project.
  * **web** (taslak-web) : Web application Taslak.

Here is the dependency diagram for the modules:

![http://taslak.googlecode.com/svn/wiki/Modules/Dependency.png](http://taslak.googlecode.com/svn/wiki/Modules/Dependency.png)

_Note: The dependency from the **web** module to **mesken-weblib** module is marked red, because there is no source code (Java) level dependency between those modules. This dependency is on integration (Struts) level._