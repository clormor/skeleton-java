# skeleton-java

<p align="center">
    <a href="https://circleci.com/gh/clormor/skeleton-java">
        <img src="https://img.shields.io/circleci/project/github/clormor/skeleton-java.svg?style=plastic" />
    </a>
    <a href="https://maven-badges.herokuapp.com/maven-central/io.github.clormor/skeleton-java">
        <img src="https://img.shields.io/maven-central/v/io.github.clormor/skeleton-java.svg?style=plastic" />
    </a>
    <a href="https://codeclimate.com/github/clormor/skeleton-java">
        <img src="https://img.shields.io/codeclimate/coverage/clormor/skeleton-java.svg?style=plastic" />
    </a>
    <a href="https://codeclimate.com/github/clormor/skeleton-java/issues">
        <img src="https://img.shields.io/codeclimate/maintainability/clormor/skeleton-java.svg?style=plastic" />
    </a>
    <a href="https://github.com/clormor/skeleton-java/commits">
        <img src="https://img.shields.io/github/last-commit/clormor/skeleton-java.svg?style=plastic" />
    </a>
</p>

A skeleton project used as a template for creating java libraries. It provides a set of useful features for open-source software development which are either enabled by default, or can easily be enabled by un-commenting various sections of the build configuration.

## Features

* Imports commonly-used dependencies
* Configures common static analysis tools: [jUnit](https://junit.org), [findbugs](https://findbugs.sourceforge.net) and [jacoco](https://www.eclemma.org/jacoco)
* Configures the project and IDEs for code generation using the [immutables.io](https://immutables.github.io/) library *(disabled by default)*
* Adds `-sources` and `-javadoc` artifacts for publishing using the [gradle nexus plugin](https://github.com/bmuschko/gradle-nexus-plugin)
* Automatically signs artifacts using the [signing plugin](https://docs.gradle.org/current/userguide/signing_plugin.html)
* Configures [CircleCI](https://circleci.com/) to;
  * Build all commits of the project
  * Upload test results and reports
  * Upload code coverage analysis to [CodeClimate](https://codeclimate.com)
  * Publish commits on `develop` as snapshots to the maven central sanpshots repository *(disabled by default)*
  * Publish commits tagged with a version as staged releases to the maven central staging repository *(disabled by default)*
  * Automatically close and release staged releases to [maven central](https://search.maven.org/) using the [nexus-staging gradle plugin](https://github.com/Codearte/gradle-nexus-staging-plugin) *(disabled by default)*
* `README` pre-formatted with some handy [shields](https://shields.io)
* Perform releases and manage version numbers using the [gradle nexus plugin](https://github.com/bmuschko/gradle-nexus-plugin)

## Local Setup

1. Clone this repository to a new folder

        git clone git@github.com:clormor/skeleton-java.git <my-new-project> && cd <my-new-project>

2. Update the project name in all places where it is referenced

        ./scripts/rename-project.sh skeleton-java <my-new-project>

3. Update the `modifyPom` closure in `build.gradle` with your project name, license and developer information

4. Update the `group` in `build.gradle` to your `groupId` (see step 1)

5. Set the following environment variables on your local machine e.g. in `~/.bashrc`:
  * `NEXUS_USER` : Your sonatype nexus username (see step 1)
  * `NEXUS_PASSWORD` : Your sonatype nexus password (see step 1)
  * `NEXUS_KEY_ID` : Your public key for signing artifacts
  * `NEXUS_KEY_PASSWORD` : The password you used to encrypt your public key
  * `NEXUS_KEY_FILE` : `~/.gnupg/secring.gpg`

6. Confirm that your project builds successfully on your local machine

        ./gradlew clean build

7. Re-initialise git and to push your changes to the `develop` branch

		rm -rf .git
		git remote set-url origin <my-new-project-github-url>
		git commit -am "initial commit initiated from https://github.com/clormor/skeleton-java"
		git push --set-upstream origin develop

## Using Immutables.io

If you wish to use the [immutables.io](https://immutables.github.io/), you can configure your project and your IDE to automatically generate the source as part of a build.

1. Un-comment the following line from `build.gradle` to enable the [org.inferred.processors](https://plugins.gradle.org/plugin/org.inferred.processors) plugin
	
		plugins {
			...
		//    id 'org.inferred.processors' version '2.1.0'
			...
		}

2. Un-comment the following lines to add the relevant dependencies to your project's build

		dependencies {
			...
		//    implementation "org.immutables:value-annotations:$immutablesVersion"
		//    annotationProcessor "org.immutables:value:$immutablesVersion"
			...
		}
		
3. Re-build your project and your IDE's project files

		./gradlew clean cleanIdea idea build

4. Commit and push your changes

## CircleCI Integration

Once configured, [CircleCI](https://circleci.com/) will build all commits pushed to GitHub. It can also be configured, if desired, to publish snapshots and releases to maven central.

1. Add your new project in [CircleCI](https://circleci.com/)

2. To sign artifacts in CircleCI, copy your base64-encoded secret key to your clipboard. For example, on Mac:

		base64 -i ~/.gnupg/secring.gpg | pbcopy

3. Configure the following environment variables for your project in CircleCI:
  * `NEXUS_USER` : Your sonatype nexus username
  * `NEXUS_PASSWORD` : Your sonatype nexus password
  * `NEXUS_KEY_ID` : Your public key for signing artifacts
  * `NEXUS_KEY_PASSWORD` : The password you used to encrypt your public key
  * `NEXUS_KEY_FILE` : `secring.gpg` *(this is different to the value you set on your local machine)*
  * `NEXUS_KEY_BASE64` : Your base64-encoded secret key (the output from step 2 above)

All commits pushed to GitHub should now be built in CircleCI. You can configure the build by making changes to `circle.yml`

## CodeClimate Integration

[CodeClimate](https://codeclimate.com) will analyse the jacoco coverage reports to determine test coverage and assess maintainability of your project's source code.

CircleCI must be configured in order to upload reports to CodeClimate (See [CircleCI Integration](##CircleCI Integration)).

1. Add your new project in [CircleCI](https://circleci.com/) and [CodeClimate](https://codeclimate.com)

2. Under *Repo Settings*, set the default branch in CodeClimate to `develop`

3. Grab the Test Reporter Id from CodeClimate and set this as the value of `CC_TEST_REPORTER_ID` in `circle.yml`

4. Un-comment the following section in the `build` build defined in  `circle.yml` to trigger uploads to CodeClimate

        #- run:
        #    name: Uploading coverage report to CodeClimate
        #    command: |
        #      export JACOCO_SOURCE_PATH=src/main/java
        #      ./cc-test-reporter format-coverage build/reports/jacoco/test/jacocoTestReport.xml -t jacoco
        #      ./cc-test-reporter upload-coverage

5. Commit and push your changes

All CircleCI builds should now upload coverage reports to CodeClimate.

## Publishing to nexus

*Publishing to nexus is disabled by default.*

You can configure the project to automatically publish snapshots and releases to nexus using the [nexus-staging gradle plugin](https://github.com/Codearte/gradle-nexus-staging-plugin).

1. If you have not configured open source repository hosting follow [these instructions](https://central.sonatype.org/pages/ossrh-guide.html), specifying a `groupId` of `io.github.<your-github-username>`. You will need to wait a few hours for this ticket to be completed before pushing any changes.

2. Un-comment the following section in the `publish-snapshot` build defined in  `circle.yml` to publish snapshots from `develop`

		#- run:
		#    name: Uploading to maven snapshot repository
		#    command: ./gradlew --info --refresh-dependencies clean uploadArchives

3. Un-comment the following section in the `publish-release` build defined in  `circle.yml` to publish releases for commits tagged with a version label

		#- run:
		#    name: Uploading to maven staging repository
		#    command: ./gradlew --info --refresh-dependencies clean uploadArchives
		
4. Commit and push your changes

Any commits pushed to `develop` will now publish your artifacts to the nexus snapshot repository. Any tagged releases will be published to the nexus staging repository (see [Releases](##Releases)).

## Automatically release nexus staging repositories

*Automatically releasing artifacts to maven central is disabled by default.*

Once you are happy that your staged releases are being built correctly, you can elect to have CircleCI automatically release them to [maven central](https://search.maven.org/). This means you no longer need to manually close the repositories in the Nexus UI.

1. Un-comment the following line in `build.gradle` to enable the nexus-staging gradle plugin

		//apply plugin: 'io.codearte.nexus-staging'

2. Un-comment the following section in the `publish-release` build defined in  `circle.yml`
 
		#- run:
		#    name: Releasing archives from staging repository
		#    command: ./gradlew --info closeAndReleaseRepository
      
3. Commit and push your changes

Releases will now be automatically promoted to maven central (see [Releases](##Releases)).

## Releases

Releases are created using the [gradle nexus plugin](https://github.com/bmuschko/gradle-nexus-plugin). A release can be performed locally by running

	./gradlew release

The task will prompt you to confirm the version number of the release you wish to create, and the subsequent snapshot version. Consult the [plugin documentation](https://github.com/bmuschko/gradle-nexus-plugin) for further guidance.

By default, this project is configured to publish releases to the `release` branch. The first time you wish to perform a release, you may need to create the `release` branch, if it does not already exist.

    git branch release

You should now be able to perform a release by running `./gradlew release` and following the on-screen prompts.

