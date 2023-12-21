import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.2.2"
}

group = property("group")!!
version = property("version")!!

bukkit {
    name = "Kastle"
    main = "net.kastle.Main"
    description = "A Minecraft plugin for my SMP server."
    author = "kyomi <me@kyomi.dev>"
    website = "https://kyomi.dev"
    apiVersion = "1.20"
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    prefix = "kastle"
    commands {
        register("party") {
            description = "Create a party with your friends."
            usage = "§c/party [ create | invite | kick | join | leave | list | disband ]"
            permission = "kastle.party"
            aliases = listOf("p")
        }
    }
}

java.toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("net.kyori:adventure-api:4.14.0")
    implementation("net.kyori:adventure-text-serializer-plain:4.14.0")
    testImplementation(kotlin("test"))

    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
}

tasks {
    test {
        useJUnitPlatform()
    }

    build {
        dependsOn(shadowJar)
    }

    runServer {
        minecraftVersion("1.20.1")
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}
