**Q1. I get the following error when I run integration tests, but everything seems to be fine.**
```
[INFO] ------------------------------------------------------------------------
[ERROR] BUILD ERROR
[INFO] ------------------------------------------------------------------------
[Server@e0cc23]: Initiating shutdown sequence...
[INFO] Error executing ant tasks

Embedded error: The following error occurred while executing this line:
java.lang.OutOfMemoryError: Java heap space
[INFO] ------------------------------------------------------------------------
[INFO] For more information, run Maven with the -e switch
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2 minutes 20 seconds
[Server@e0cc23]: Shutdown sequence completed in 6547 ms.
[Server@e0cc23]: 2008-05-05 23:31:41.560 SHUTDOWN : System.exit() is called next
[INFO] Finished at: Mon May 05 23:31:41 CEST 2008
[INFO] Final Memory: 63M/63M
[INFO] ------------------------------------------------------------------------
```

A1. You get an OutOfMemoryError. Try expanding your heap space using the following command before your maven command.
```
set MAVEN_OPTS=-Xmx512m
```