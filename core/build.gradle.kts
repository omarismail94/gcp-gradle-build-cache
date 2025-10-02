/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(gradleApi())
    implementation(platform(libs.okhttp.bom))
    api(platform(libs.kotlin.bom))
    api(libs.kotlin.stdlib)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.google.gson)
    implementation(libs.okhttp)
}

kotlin {
    jvmToolchain {
        jvmToolchain(17)
    }
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            useJUnit()
        }
    }
}

// b/250726951 Gradle ProjectBuilder needs reflection access to java.lang.
val jvmAddOpensArgs = listOf("--add-opens=java.base/java.lang=ALL-UNNAMED")
tasks.withType<Test>() {
    this.jvmArgs(jvmAddOpensArgs)
}
