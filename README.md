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

Implementation
---------------

Java Modules are used to represent DDD Bounded Contexts. A single Java Module
represents a single Bounded Context. Each module represents a complete instance
of the Clean Architecture diagram. Contact and interaction with other Bounded
Contexts should be treated in much the same way as contact with an external web
service, database, or any other dependency: through interfaces with unknown
implementations and Data Trasfer Objects (i.e., data structures lacking
behavior or encapsulation).

Within a Bounded Context, Java Packages divide the system into the rings and
ring segments of the Clean Architecture diagram. Within the `com.example`
namespace, the top-level Java Package names the bounded context and matches the
module name. The second-level Java Package names the architectural component:

- **domain** - the Value Types, Entities, Use-Cases that comprise the critical
business and application policies implemented by the system; these rules exist
independent from (and without dependency on) frameworks and specific
technologies (e.g., Spring, Vert.x, PostgreSQL, RabbitMQ)

- **postgres** - contains implementations of data access interfaces defined in
domain, with implementation details specific to PostgreSQL

- **web** - contains Controllers and Presenters (in the MVP) specific to a web
site or service implemented using `com.sun.net.httpserver.HttpServer`

Module/package dependency structure is enforced by [CheckStyle
ImportControl](build-tools/src/main/resources/import-control.xml).
