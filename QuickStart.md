
```
0) \taslak-SVN>svn up

1) \taslak-SVN>mvn hsql:start install 

2) \taslak-GEN\core>mvn hsql:start install
3) \taslak-GEN\web>mvn hsql:start jetty:run-war -Dmaven.test.skip=true
```