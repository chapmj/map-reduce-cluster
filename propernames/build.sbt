lazy val commonSettings = Seq(
	version := "1.0",
	scalaVersion := "2.12.8",
	scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation"),
	resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
	libraryDependencies ++= Seq(
		"com.typesafe.akka" %% "akka-actor" % "2.5.22",
		"com.typesafe.akka" %% "akka-remote" % "2.5.22"))

lazy val root = (project in file("."))
	.aggregate(propernames)

lazy val propernames = (project in file("propernames"))
	.settings(commonSettings: _*)
	.settings(name := "propernames")



