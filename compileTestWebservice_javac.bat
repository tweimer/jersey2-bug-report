javac -cp .;./runtime/classes_jersey/* -d ./runtime/classes_javac -sourcepath src  ./src/testJersey2/WebServer.java ./src/testJersey2/rootClasses/WebServiceRootClass.java
jar cf ./runtime/classes_javac/testWebservice_javac.jar -C ./runtime/classes_javac testJersey2/WebServer.class -C ./runtime/classes_javac testJersey2/rootClasses/WebServiceRootClass.class
pause
