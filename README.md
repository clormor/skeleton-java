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

A skeleton project used as a template for creating java libraries.

## Features

* Imports commonly-used java libraries
* Configures the project and IDEs for code generation using [Immutables](https://immutables.github.io/)
* Configures [CircleCI](https://circleci.com/) to;
  * Build all commits of the project
  * Upload jUnit test results and all report artifacts
  * Upload code coverage analysis to [CodeClimate](https://codeclimate.com)
  * Publish commits on `develop` as snapshots to the [maven central sanpshots repository](https://oss.sonatype.org/content/repositories/snapshots/)
  * Publish commits tagged with a version as staged releases to the [maven central staging repository](https://oss.sonatype.org/service/local/staging/deploy/maven2)
  * Automatically close and release staged releases to [maven central](https://oss.sonatype.org/service/local/staging/deploy/maven2) using the [Gradle Nexus Staging plugin](https://github.com/Codearte/gradle-nexus-staging-plugin)
* `README` pre-formatted with some handy [shields](https://shields.io)
* Perform releases and manage version numbers using the [Gradle Sonatype Nexus plugin](https://github.com/bmuschko/gradle-nexus-plugin)

## Usage

1. If you have not configured open source repository hosting follow [these instructions](https://central.sonatype.org/pages/ossrh-guide.html), specifying a `groupId` of `io.github.<your gh username>`. You will need to wait a few hours for this ticket to be completed before pushing any changes.
2. Clone this repository to a new folder

        git clone git@github.com:clormor/skeleton-java.git <my-new-project> && cd <my-project>

3. Update the project name in all places where it is referenced

        ./scripts/rename-project.sh skeleton-java <my-new-project>

4. Update the `group` in `build.gradle` to your `groupId` (see step 1)

5. Set the following environment variables on your local machine e.g. in `~/.bashrc`:
  * `NEXUS_USER` : Your sonatype nexus username (see step 1)
  * `NEXUS_PASSWORD` : Your sonatype nexus password (see step 1)
  * `NEXUS_KEY_ID` : Your public key for signing artifacts
  * `NEXUS_KEY_PASSWORD` : The password you used to encrypt your public key
  * `NEXUS_KEY_FILE` : `~/.gnupg/secring.gpg`

6. As a sanity check, confirm that your project builds successfully on your local machine

        ./gradlew clean build

7. Create a new repository in [GitHub](https://github.com) and point update the `origin` remote

        git remote set-url origin git@github.com:<repository>/<my-new-project>

8. Add your new project in [CircleCI](https://circleci.com/) and [CodeClimate](https://codeclimate.com)
  * You may need to configure the default branch in CodeClimate to `develop` under Repo Settings.

9. Grab the Test Reporter Id from CodeClimate and update the value of `CC_TEST_REPORTER_ID` in `circle.yml`

10. To sign artifacts in circle, copy your base64-encoded secret key to your clipboard. For example, on Mac:

        base64 -i ~/.gnupg/secring.gpg | pbcopy

11. Set the following environment variables in CircleCI:
  * `NEXUS_USER` : (see step 5)
  * `NEXUS_PASSWORD` : (see step 5)
  * `NEXUS_KEY_ID` : (see step 5)
  * `NEXUS_KEY_PASSWORD` : (see step 5)
  * `NEXUS_KEY_FILE` : `secring.gpg` (note this is not the same as the value you set in step 5)
  * `NEXUS_KEY_BASE64` : Your base64-encoded secret key (the output from step 10)

12. Commit and push your changes to `develop`

        git commit -am "initial commit initiated from https://github.com/clormor/skeleton-java"
        git push --set-upstream origin develop

## Performing the first release

Releases are generated using the [Gradle Sonatype Nexus plugin](https://github.com/bmuschko/gradle-nexus-plugin). See its documentation for guidance.

By default, this project is configured to publish releases to the `release` branch. The first time you wish to perform a release, you may need to create the `release` branch, if it does not already exist.

    git branch release

You should now be able to perform a release by running `./gradlew release` and following the on-screen prompts.

