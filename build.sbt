name := """ReviewBook"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.32",
  "org.springframework" % "spring-context" % "4.1.0.RELEASE",
  "org.springframework" % "spring-jdbc" % "4.1.0.RELEASE",
  "org.springframework" % "spring-tx" % "4.1.0.RELEASE",
  "org.apache.commons" % "commons-csv" % "1.0",
  "commons-collections" % "commons-collections" % "3.2.1",
  "org.apache.commons" % "commons-lang3" % "3.3.2",
  "com.google.guava" % "guava" % "12.0"
)
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true