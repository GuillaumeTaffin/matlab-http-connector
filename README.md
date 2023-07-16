# MATLAB HTTP API

The Matlab Engine API is not easy to set up in projects, and constraints the runtime environment is a manner that is not always acceptable to build tool on top of it.

This project allows to create a server that will expose an HTTP API over the Matlab Engine Api. It's first target is to allow to evaluate MATLAB commands through a language agnostic API in order to control the MATLAB instance. For instance run matlab scripts and unit tests, compile m-files, set up the matlab environment etc...

## Contribute to development

### Local Matlab Installation

Having an instance of MATLAB installed locally is required to be able to compile the project locally.

For now this project is built and tested against the Matlab 2023a version of the API.

For local development the Matlab Engine API needs to be on your classpath. You can change the path in the gradle.properties if needed.
