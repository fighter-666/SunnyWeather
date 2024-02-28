pluginManagement {
    repositories {
        maven {
            url =uri("http://maven.aliyun.com/nexus/content/groups/public/")
            isAllowInsecureProtocol = true}
        maven {
            url =uri("http://maven.aliyun.com/nexus/content/repositories/jcenter")
            isAllowInsecureProtocol = true}
        maven {
            url =uri("http://maven.aliyun.com/nexus/content/repositories/google")
            isAllowInsecureProtocol = true}
        maven {
            url =uri("http://maven.aliyun.com/nexus/content/repositories/gradle-plugin")
            isAllowInsecureProtocol = true}
        maven {
            url =uri("https://jitpack.io")
            isAllowInsecureProtocol = true}

        gradlePluginPortal()
        google()
        mavenCentral()

    }
}


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url =uri("http://maven.aliyun.com/nexus/content/groups/public/")
            isAllowInsecureProtocol = true}
        maven {
            url =uri("http://maven.aliyun.com/nexus/content/repositories/jcenter")
            isAllowInsecureProtocol = true}
        maven {
            url =uri("http://maven.aliyun.com/nexus/content/repositories/google")
            isAllowInsecureProtocol = true}
        maven {
            url =uri("http://maven.aliyun.com/nexus/content/repositories/gradle-plugin")
            isAllowInsecureProtocol = true}
        maven {
            url =uri("https://jitpack.io")
            isAllowInsecureProtocol = true}

    }
}

rootProject.name = "Sunny Weather"
include(":app")
 