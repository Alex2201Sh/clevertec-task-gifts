# **Task 1 - Basics**
<details>
  <summary>Task</summary>

### Business requirements

#### 1.
Develop web service for Gift Certificates system with the following entities (many-to-many):
gift_certificate, tag
_(IMPLEMENTED: many-to-many implemented with linking table 'certificate_tag')_

CreateDate, LastUpdateDate - format ISO 8601 (https://en.wikipedia.org/wiki/ISO_8601). Example: 2018-08-29T06:12:15.156. More discussion here: https://stackoverflow.com/questions/3914404/how-to-get-current-moment-in-iso-8601-format-with-date-hour-and-minute
_(IMPLEMENTED with class LocalDateTime)_

Duration - in days (expiration period)
_(IMPLEMENTED with class PGInterval)_

#### 2.

The system should expose REST APIs to perform the following operations:

* CRUD operations for GiftCertificate. If new tags are passed during creation/modification – they should be created in the DB. For update operation - update only fields, that pass in request, others should not be updated. Batch insert is out of scope.
_(IMPLEMENTED in GiftController)_

* CRUD operations for Tags.
_(IMPLEMENTED in TagController)_
* Get certificates with tags (all params are optional and can be used in conjunction):
by tag name (ONE tag)
search by part of name/description (can be implemented, using DB function call)
sort by date or by name ASC/DESC (extra task: implement ability to apply both sort type at the same time).
_(all requirements IMPLEMENTED in GiftController)_

### Application requirements
1. JDK version: 17 – use Streams, java.time.*, etc. where it is possible. _(IMPLEMENTED)_
2. Application packages root: ru.clevertec.ecl. _(IMPLEMENTED)_
3. Any widely-used connection pool could be used. _(IMPLEMENTED)_
4. Spring JDBC Template should be used for data access. _(IMPLEMENTED)_
5. Use transactions where it’s necessary. _(IMPLEMENTED)_
6. Java Code Convention is mandatory (exception: margin size – 120 chars). _(IMPLEMENTED)_
7. Build tool: Gradle, latest version. _(IMPLEMENTED)_
8. Web server: Apache Tomcat. _(IMPLEMENTED)_
9. Application container: Spring IoC. Spring Framework, the latest version. _(IMPLEMENTED)_
10. Database: PostgreSQL, latest version. _(IMPLEMENTED)_
11. Testing: JUnit 5.+, Mockito. _(IMPLEMENTED)_
12. Service layer should be covered with unit tests not less than 80%. _(IMPLEMENTED)_
13. Repository layer should be tested using integration tests with an in-memory embedded database (all operations with certificates). _(NOT IMPLEMENTED)_
14. As a mapper use Mapstruct. _(IMPLEMENTED)_
15. Use lombok. _(IMPLEMENTED)_


### General requirements
1. Code should be clean and should not contain any “developer-purpose” constructions. _(IMPLEMENTED)_
2. App should be designed and written with respect to OOD and SOLID principles. _(IMPLEMENTED)_
3. Code should contain valuable comments where appropriate. _(IMPLEMENTED)_
4. Public APIs should be documented (Javadoc). _(IMPLEMENTED)_
5. Clear layered structure should be used with responsibilities of each application layer defined. _(IMPLEMENTED)_
6. JSON should be used as a format of client-server communication messages. _(IMPLEMENTED)_
7. Convenient error/exception handling mechanism should be implemented: all errors should be meaningful on backend side. Example: handle 404 error:
HTTP Status: 404
response body    
{
“errorMessage”: “Requested resource not found (id = 55)”,
“errorCode”: 40401
}
where "errorCode" is your custom code (it can be based on http status and requested resource - certificate or tag)
   _(IMPLEMENTED)_
8. Abstraction should be used everywhere to avoid code duplication. _(IMPLEMENTED)_
9. Several configurations should be implemented (at least two - dev and prod). _(IMPLEMENTED)_

### Application restrictions
It is forbidden to use:
1. Spring Boot. _(NOT IMPLEMENTED)_
2. Spring Data Repositories._(IMPLEMENTED)_
3. JPA._(IMPLEMENTED)_

</details>

<details>
  <summary>How to use</summary>

Endpoints:
* /gifts [GET] - uses to get all GiftCertificates
* /gifts [POST] - uses to create new entity
* /gifts/{id} [GET] - uses to get entity by id
* /gifts/{id} [PATCH] - uses to edit only required field of entity
* /gifts/{id} [DELETE] - uses to delete entity by id
* /tags [GET] - uses to get all Tags
* /tags [POST] - uses to create new entity
* /tags/{id} [GET] - uses to get entity by id
* /tags/{id} [PATCH] - uses to edit only required field of entity
* /tags/{id} [DELETE] - uses to delete entity by id

/gifts [GET] - supports request parameters:
* "tag_name" - can be applied to filter GiftCertificates by Tag name;
* "cert_name" - can be applied to filter GiftCertificates by part of its name
* "description" - can be applied to filter GiftCertificates by part of its description
* "sort_by_name" - can be applied to sort GiftCertificates by name
* "sort_by_date" - can be applied to sort GiftCertificates by createDate

For example:
URL http://localhost:8080/gifts?tag_name=two&cert_name=se&description=abc&sort_by_name=any&sort_by_date=desc
1. will be filtered by tag name "two"
2. filtered GiftCertificates with name contains "se"
3. filtered GiftCertificates with description contains "abc"
4. then sorted by GiftCertificates name
5. then sorted by GiftCertificates createDate in reverse order



</details>

