# Company REST Sample

==============

Small application for Test.
Created with STS and Maven on Java 1.7.

## Contents

* [Technology](#technology)
* [Running instance](#running-instance)
* [REST API](#rest-api)
* [cURL usage](#curl-usage)
  * [Company management](#company-management)
  * [Owner management](#owner-management)

## Technology

* Spring Boot - for easy startup and embedded Tomcat
* Spring MVC, REST
* Spring JPA - as ORM 
* Liquibase - for creating DB on-fly
* Jackson - for JSON
* AngularJS - as simply GUI
  * ng-table - for rendering HTML Table
  * ng-resource - for easy access to REST endpoints
* Database - H2 (so after rebuild and restart all entered Data will be wiped)
  * Entity
    * Company
    * Owner


## Running instance

Instance at Heroku:

[https://company-rest-sample.herokuapp.com/](https://company-rest-sample.herokuapp.com/)

Heroku is set ass free plan so first usage (after 30 minutes of idle) will trigger its wake-up (You should wait couple of seconds for entering application)

## REST API

API work by REST actions with JSON as communication language

Endpoints/Mappings:

* api/company
* api/companyOwner


## cURL usage

Web service can be queried using cURL

### Company management

```
curl -I https://company-rest-sample.herokuapp.com/api/company
```

Allow: GET, PUT, POST

GET - get all Company Entities

```
curl https://company-rest-sample.herokuapp.com/api/company
```

GET - get one Company Entity by ID

```
curl https://company-rest-sample.herokuapp.com/api/company/1
```


POST - create Company Entity

```
curl -X POST -H "Content-Type: application/json"  -d @json.txt https://company-rest-sample.herokuapp.com/api/company
```


PUT - create or update Company Entity

```
curl -X PUT -H "Content-Type: application/json"  -d '{"name":"Company Co","address":"Washington","city":"Main","country":"USA","email":"comapny@co.com","phoneNumber":"567-890-123"}' https://company-rest-sample.herokuapp.com/api/company

For Windows (encoding problem) we use File with data

curl -X PUT -H "Content-Type: application/json"  -d @jsonUpdate.txt https://company-rest-sample.herokuapp.com/api/company
```


FILE json.txt

```
{"name":"Company Co","address":"Washington","city":"Main","country":"USA","email":"comapny@co.com","phoneNumber":"567-890-123"}
```

FILE jsonUpdate.txt

```
{"id":1,"name":"Company Co","address":"Washington","city":"Main","country":"USA","email":"comapny@co.com","phoneNumber":"567-890-123777","owners":[]}
```


### Owner management


POST - create Owner Entity and add Owner to Company

```
curl -X POST -H "Content-Type: application/json"  -d @jsonOwner.txt https://company-rest-sample.herokuapp.com/api/companyOwner
```

FILE jsonOwner.txt

```
{"name":"Test owner","companyId":1}
```

## cURL usage example

Add Company:

```
curl -X PUT -H "Content-Type: application/json"  -d @json.txt https://company-rest-sample.herokuapp.com/api/company
```

List Companies:

```
curl https://company-rest-sample.herokuapp.com/api/company
```

returns:

```
[{"id":1,"name":"Company Co","address":"Washington","city":"Main","country":"USA","email":"comapny@co.com","phoneNumber":"567-890-123","owners":[]}]
```

Add Owner to Company:

```
curl -X POST -H "Content-Type: application/json"  -d @jsonOwner.txt https://company-rest-sample.herokuapp.com/api/companyOwner
```


Get Company by ID

```
curl https://company-rest-sample.herokuapp.com/api/company/1
```

returns:

```
{"id":1,"name":"Company Co","address":"Washington","city":"Main","country":"USA","email":"comapny@co.com","phoneNumber":"567-890-123777","owners":[{"id":1,"name":"Test owner"}]}
```
