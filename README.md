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
  * Publish commits on `develop` as snapshots to [maven central sanpshots repository](https://oss.sonatype.org/content/repositories/snapshots/)
  * Publish commits tagged with a version as staged releases to [maven central staging repository](https://oss.sonatype.org/service/local/staging/deploy/maven2)
  * Automatically close and release staged releases to [maven central](https://oss.sonatype.org/service/local/staging/deploy/maven2) - no need to log into the nexus UI
* `README` pre-formatted with some handy [shields](https://shields.io)

## Usage

1. If you have not configured open source repository hosting follow [these instructions](https://central.sonatype.org/pages/ossrh-guide.html), specifying a `groupId` of `io.github.<your gh username>`. You will need to wait a few hours for this ticket to be completed before pushing any changes.
2. Clone this repository to a new folder

        git clone git@github.com:clormor/skeleton-java.git <my-new-project> && cd <my-project>

3. Update the project name in all places where it is referenced

        ./scripts/rename-project.sh skeleton-java <my-new-project>

4. Create a new repository and point `origin` to your new repo

        git remote set-url origin git@github.com:<repository>/<my-new-project>

5. Set the following environment variables on your local machine e.g. in `~/.bashrc`:
  * `NEXUS_USER` : Your sonatype nexus username (see step 1)
  * `NEXUS_PASSWORD` : Your sonatype nexus password (see step 1)
  * `NEXUS_KEY_ID` : Your public key for signing artifacts
  * `NEXUS_KEY_PASSWORD` : The password you used to encrypt your public key
  * `NEXUS_KEY_FILE` : `~/.gnupg/secring.gpg`

6. Commit and push your changes to the develop branch

        git commit -am "initial commit"
        git push --set-upstream origin develop

7. Add your new project as projects in [CircleCI](https://circleci.com/) and [CodeClimate](https://codeclimate.com)

8. To sign artifacts in circle, copy your base64-encoded secret key to your OS' clipboard. For example, on OSX:

        base64 -i ~/.gnupg/secring.gpg | pbcopy

9. Set the following environment variables in CircleCI:
  * `NEXUS_USER` : (see step 5)
  * `NEXUS_PASSWORD` : (see step 5)
  * `NEXUS_KEY_ID` : (see step 5)
  * `NEXUS_KEY_PASSWORD` : (see step 5)
  * `NEXUS_KEY_FILE` : `secring.gpg` (note this is not the same as the value you set in step 5)
  * `NEXUS_KEY_BASE64` : Your base64-encoded secret key (the output from step 8)

