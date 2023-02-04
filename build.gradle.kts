plugins {
    id("java")
    id("maven-publish")
}

group = "me.lucaslah"
version = property("version") as String
description = "A simple java config library"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.7.1")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = "javaconfig"
            version = property("version") as String

            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri("https://nexus.cssudii.xyz/repository/mavenmain/")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }

        maven {
            url = uri("https://maven.pkg.github.com/Lucaslah/JavaConfig")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

