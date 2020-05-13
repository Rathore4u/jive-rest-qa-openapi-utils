# jive-cloud-core-api-utils

Api utils for the Jive cloud product family.

## YAML

OpenAPI yaml are organized following this general structure:


 - src/main/resources
    - /main    
      This will contain the main openapi files
    - /models
      One file per model 
    - /apis
      Yaml file organized following the url path structure of the request
      each file will contain all the HTTP methods for the corresponding url
