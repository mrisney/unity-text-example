#### Unity Text Analyzer Example Exercise
[![Build Status](https://travis-ci.org/mrisney/unity-text-example.svg?branch=master)](https://travis-ci.org/mrisney/unity-text-example)
[![codecov](https://codecov.io/gh/mrisney/unity-text-example/branch/master/graph/badge.svg)](https://codecov.io/gh/mrisney/unity-text-example)

This simple application is used to demonstrate:

- Create a text analyzer that processes text and provides information about its word contents 
- Services  that shows a count of how many times each word occurs in the text. The ouptut can be sorted, with a primary sort of word length, and a secondary ASCII sort
- There is also a word count sort

You can try it out at
[https://aqueous-oasis-12903.herokuapp.com/swagger-ui.html](https://aqueous-oasis-12903.herokuapp.com/swagger-ui.html)

Click on the List of Operations, link on the lower right:

You can use curl to try it out :

```curl -X POST --header 'Content-Type: text/plain' --header 'Accept: application/json' -d 'The quick brown fox jumped over the lazy brown dog’s back' 'https://aqueous-oasis-12903.herokuapp.com/analyze-sort'```





#### Before you start

```text-analyzer-service``` is a SpringBoot service. In order to build this project you need to:

 1. ```text-analyzer-service``` - change to this directory and either run it with springboot Maven plugin :
     ```mvn spring-boot:run```
which will run on [http://localhost:8888](http://localhost:8888)

 1. ```mvn package ``` - which will build a spring boot jar file and build the [unity-text-exercise](https://github.com/mrisney/unity-text-exercise) of my fork of [TextAnalyzer](https://github.com/mrisney/unity-text-exercise/blob/master/src/main/java/com/risney/unity/text/TextAnalyzer.java)
 
#### Build:

In the  text-analyzer-service directory, build the SpringBoot fat jar :

```
mvn clean package
```


## Execute:

then in the root directory 

```
.\docker\run.sh
```


## Test

The text-analyzer-service  is accessible as a Swagger documented  set of endpoints. You can use CURL but the operations are documented with simple text/plain inputs.

Sample queries to use on a POST to http://localhost:88888/swagger-ui.html


### Example using CURL:
```bash
curl -X POST --header 'Content-Type: text/plain' --header 'Accept: application/json' -d 'The quick brown fox jumped over the lazy brown dog’s back' 'http://localhost:8888/analyze'
```

### which would result in the following output:
```
[
  {
    "word": "The",
    "length": 3,
    "count": 1
  },
  {
    "word": "quick",
    "length": 5,
    "count": 1
  },
  {
    "word": "brown",
    "length": 5,
    "count": 2
  },
  {
    "word": "fox",
    "length": 3,
    "count": 1
  },
  {
    "word": "jumped",
    "length": 6,
    "count": 1
  },
  {
    "word": "over",
    "length": 4,
    "count": 1
  },
  {
    "word": "the",
    "length": 3,
    "count": 1
  },
  {
    "word": "lazy",
    "length": 4,
    "count": 1
  },
  {
    "word": "dog’s",
    "length": 5,
    "count": 1
  },
  {
    "word": "back",
    "length": 4,
    "count": 1
  }
]
```
