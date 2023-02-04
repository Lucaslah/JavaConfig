## Add repository

**NOTE:** If you want to use GitHub packages to download replace the url with `https://maven.pkg.github.com/Lucaslah/JavaConfig`

Maven
```xml
<repository>
    <id>cssudii-nexus</id>
    <name>CSSUDII Nexus</name>
    <url>https://nexus.cssudii.xyz/repository/mavenmain/</url>
</repository>
```

Gradle
```groovy
maven {
        url = uri("https://nexus.cssudii.xyz/repository/mavenmain/")
}
```

## Add dependency
Maven
```xml
<dependency>
  <groupId>me.lucaslah</groupId>
  <artifactId>javaconfig</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Gradle (Groovy)
```groovy
implementation 'me.lucaslah:javaconfig:1.0-SNAPSHOT'
```

Gradle (Kotlin)
```kotlin
implementation("me.lucaslah:javaconfig:1.0-SNAPSHOT")
```