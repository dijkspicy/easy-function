语法
-----

[TOC]

# 可重用模型
## 函数（function）
### Intrinsic functions
基本函数

#### concat
用以将多个字符串拼成一个字符串

**语法**
```yaml
concat: [string_value_expressions_* ]
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|string_value_expressions_*|yes|list|一个list类型，其中可以包含表达式|

**样例**
```yaml
outputs:
  description: Concatenate the URL for a server from other template values
  server_url:
  value: { concat: [ 'http://',
                     get_attribute: [ server, public_address ],
                     ':',
                     get_attribute: [ server, port ] ] }
```

#### join
用以将指定字符串列表中间插入特定字符拼接为新的字符串

**语法**
```yaml
join: [list of string_value_expressions_* [ delimiter ] ]
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|list of string_value_expressions_*|yes|list|一个list类型，其中可以包含表达式|
|delimiter|no|string|分隔符|

**样例**
```yaml
outputs:
   example1:
       # Result: prefix_1111_suffix
       value: { join: [ ["prefix", 1111, "suffix" ], "_" ] }
   example2:
       # Result: 9.12.1.10,9.12.1.20
       value: { join: [ { get_input: my_IPs }, "," ] }
```

#### token
使用特定算法（MD5）生成java令牌

**语法**
```yaml
token: [ string_with_tokens, string_of_token_chars, substring_index ]
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|string_with_tokens|yes|string|原始字符串|
|string_of_token_chars|yes|string|用以分隔原始字符串|
|substring_index|yes|integer|表示从分割后的哪项字符串开始，最终时候从这里开的子字符串|

**样例**
```yaml
outputs:
   webserver_port:
     description: the port provided at the end of my server’s endpoint’s IP address
     value: { token: [ get_attribute: [ my_server, data_endpoint, ip_address ],
                       ':',
                       1 ] }
```

#### Property functions
获取模型对象属性值

#### get_input
获取inputs参数的值

**语法**
```yaml
get_input: input_property_name
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|input_property_name|yes|string|input的属性名称|

**样例**
```yaml
inputs:
  cpus:
    type: integer
 
node_templates:
  my_server:
    type: tosca.nodes.Compute
    capabilities:
      host:
        properties:
          num_cpus: { get_input: cpus }
```

#### get_property
获取模型对象的property属性值

**语法**
```yaml
get_property: [ modelable_entity_name, optional_req_or_cap_name, property_name, nested_property_name_or_index_1, ..., nested_property_name_or_index_n ]
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|modelable entity name|yes|string|模型对象名称|
|property_name|yes|string|模型属性名，往后跟的prop将会拼成jsonpath进行属性获取|

**样例**
```yaml
node_templates:
 
  mysql_database:
    type: tosca.nodes.Database
    properties:
      name: sql_database1
 
  wordpress:
    type: tosca.nodes.WebApplication.WordPress
    ...
    interfaces:
      Standard:
        configure:
          inputs:
            wp_db_name: { get_property: [ mysql_database, name ] }
```
### Attribute functions
用以获取指定模型对象的attribute属性值

#### get_attribute
获取模型对象的attribute属性值

**语法**
```yaml
get_attribute: [ modelable_entity_name, optional_req_or_cap_name, attribute_name, nested_attribute_name_or_index_1, ..., nested_attribute_name_or_index_n ]
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|modelable entity name|yes|string|模型对象名称|
|attribute_name|yes|string|模型属性名，往后跟的attr将会拼成jsonpath进行属性获取|

**样例**
```yaml
node_templates:
 
  mysql_database:
    type: tosca.nodes.Database
    properties:
      name: sql_database1
 
  wordpress:
    type: tosca.nodes.WebApplication.WordPress
    ...
    interfaces:
      Standard:
        configure:
          inputs:
            wp_db_name: { get_attribute: [ mysql_database, name ] }
```

### Operation functions
用以获取模型对象执行操作之后的属性值

#### get_operation_output
获取模型对象指定操作指定的输出值

**语法**
```yaml
get_operation_output: [ modelable_entity_name, interface_name, operation_name, output_variable_name ]
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|modelable entity name|yes|string|模型对象名称|
|interface_name|yes|string|操作所在的接口名|
|operation_name|yes|string|操作名称|
|output_variable_name|yes|string|指定输出值名称|

**样例**
```yaml
node_templates:
 
  mysql_database:
    type: tosca.nodes.Database
    properties:
      name: sql_database1
 
  wordpress:
    type: tosca.nodes.WebApplication.WordPress
    ...
    interfaces:
      Standard:
        configure:
          inputs:
            wp_db_name: { get_operation_output: [ mysql_database, Standard, create, name ] }
```

### Navigation functions
用以获取某些模型对象

#### get_nodes_of_type
用以获取指定类型的模型对象列表

**语法**
```yaml
get_nodes_of_type: node_type_name
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|node_type_name|yes|string|指定类型|

### Artifact functions

#### get_artifact
用以获取某些模型对象的附件

**语法**
```yaml
get_artifact: [ modelable_entity_name, artifact_name, location, remove ]
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|modelable_entity_name|yes|string|模型对象名称|
|artifact_name|yes|string|附件名称|
|location|no|string|附件位置|
|remove|no|boolean|是否删除|

**样例**
```yaml
node_templates:
 
  wordpress:
    type: tosca.nodes.WebApplication.WordPress
    ...
    interfaces:
      Standard:
        configure:
          create:
            implementation: wordpress_install.sh
            inputs
              wp_zip: { get_artifact: [ SELF, zip ] }
    artifacts:
      zip: /data/wordpress.zip
```

### Extend functions

#### constraint
用于判断两个值是否满足某种条件

**语法**
```yaml
constraint: { operator: [expectedValue, presentValue]}
```

**参数**

|名称|必须？|类型|描述|
|:---|:---|:---|:---|
|operator|yes|string|约束名称|
|expectedValue|yes|any|期望值|
|presentValue|yes|any|实际值|

**样例**
```yaml
node_templates:
 
  wordpress:
    type: tosca.nodes.WebApplication.WordPress
    ...
    interfaces:
      Standard:
        configure:
          create:
            implementation: wordpress_install.sh
            inputs:
              test: { constraint: {equal: [ test, test ] }}
```
