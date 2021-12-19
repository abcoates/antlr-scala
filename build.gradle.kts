group = "org.contakt.scala.antlr"
version = "1.0-SNAPSHOT"

plugins {
    scala
    antlr
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala-library:2.13.7")
    implementation("org.antlr:antlr4:4.9.3")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.scalatest:scalatest_2.13:3.2.10")
    testImplementation("org.scalatestplus:scalatestplus-junit_2.13:1.0.0-M2")
    antlr("org.antlr:antlr4:4.9.3")
}
