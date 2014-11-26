import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "blogMVC"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    javaCore,
    javaJdbc,
    javaJpa,
    // Add your project dependencies here,
    "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
    "mysql" % "mysql-connector-java" % "5.1.21",
    "org.jadira.usertype" % "usertype.core" % "3.1.0.CR7"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    // resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns)
  )

}
