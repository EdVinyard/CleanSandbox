Clean Architecture Sandbox
===========================

The purpose of this project is to explore the idealized shape of a specific,
existing Java system, including the important details of that system,
conforming to the [Clean Architecture, as articulated by Robert C.
Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

The primary emphasis is decoupling the code that embodies Entities and Use
Cases (the essentials) from code that embodies application-specific details.
Because some consideration is given to the migration of a pre-existing
medium-size codebase to this structure, not everything is going to be perfect.

![clean architecture diagram](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

Prerequisites
--------------

- Java SDK 14+
- Maven 3.6+

Enforcement
------------

Module/package dependency structure is enforced by [CheckStyle
ImportControl](build-tools/src/main/resources/import-control.xml).

You can visualize inter-module and external dependencies by running the
following command.

```bash
mvn compile dependency:tree
```
