import java.io.File

plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
}

val localBuildRoot = providers.environmentVariable("LOCALAPPDATA")
    .map { File(it, "LifeHackOrUrbanMyth-gradle-build") }
    .orElse(provider { rootDir.resolve(".local-build") })

layout.buildDirectory.set(layout.dir(localBuildRoot.map { it.resolve("root") }))

subprojects {
    layout.buildDirectory.set(rootProject.layout.dir(localBuildRoot.map { it.resolve(name) }))
}
