plugins {
    id("java-library")
    id("maven-publish")

    id("io.github.goooler.shadow") version "8.1.8"
}

tasks["jar"].enabled = false

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    group = "net.somewhatcity"
    version = "1.0.7"

    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

subprojects {
    publishing {
        publications.create<MavenPublication>("maven${project.name}") {
            artifactId = "${rootProject.name}-${project.name}".lowercase()
            from(components["java"])
        }
        repositories {
            mavenLocal()
        }
    }
}