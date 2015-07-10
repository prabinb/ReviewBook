name := """ReviewBook"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

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
  "org.apache.commons" % "commons-io" % "1.3.2",
  "com.google.guava" % "guava" % "12.0",
  "org.jsoup" % "jsoup" % "1.8.2"
)
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

fork in run := true