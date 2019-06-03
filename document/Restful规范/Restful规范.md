# Restful接口

### HTTP协议基础

#### HTTP协议主要特点

* **无连接：**
  
  无连接的含义是限制**每次连接只处理一个请求**。服务器处理完客户的请求，并收到客户的应答后，即断开连接。每次请求需要通过TCP三次握手四次挥手，和服务器重新建立连接，需要耗费不必要的时间和流量。可以通过设置**Connection:  keep-alive**保持连接在一段时间内不断开来减少TCP连接次数，但因为需要httpd进程维护长连接，在**高并发情况下会增加服务端资源消耗**。
  
* **无状态:**
  
  HTTP协议是无状态协议,无状态是指协议对于**事务处理没有记忆能力**。需要借助cookie、session等技术保持会话状态
  
* 支持B/S及C/S模式

* 使用明文通信

* HTTPS（内容加密、验证身份、保护数据完整性）

#### HTTP消息

##### 请求消息

* 请求行（请求行以一个方法符号开头，以空格分开，后面跟着请求的URI的协议和版本）
  
  如：**POST /v1/users  HTTP/1.1**
  
* 请求头（紧接着请求行（即第一行）之后的部分，用来说明服务器要使用的附加信息）
  
  如：**Content-Type: application/json**
  
  ​		**User: usercode:admin&username:admin**
  
* 空行（忽略）

* 请求数据主体(请求消息体)
  
  如：
```
{
   "user_name":"lran",
   "user_code":"172",
   "email":"liu.ran@unisinsight.com",
   "position":"Java"
}
```
##### 响应消息

* 状态行（由HTTP协议版本号， 状态码， 状态消息 三部分组成）
  
  如：**HTTP/1.1 200 OK**
  
* 消息报头（说明客户端要使用的一些附加信息）
  
  如:  **Content-Type: application/json**
  
* 空行（忽略）

* 响应正文(返回消息体)
  
   如：
```
{
   "id":2101,
   "user_name":"lran",
   "user_code":"172",
   "email":"liu.ran@unisinsight.com",
   "position":"Java"
}
```
#### HTTP通信传输过程
##### 请求
1. 建立TCP连接（三次握手）
2. 发送请求行(URI)
3. 发送头信息(Header)和提交请求数据主体(Body)
##### 响应
4. 服务器应答状态行
5. 服务器应答消息报头
6. 服务器应答返回数据
##### 关闭
7. 服务器关闭TCP连接

#### HTTP状态码
##### HTTP状态码分类

* 1xx：指示信息--表示请求已接收，继续处理 

* 2xx：成功--表示请求已被成功接收、理解、接受

* 3xx：重定向--要完成请求必须进行更进一步的操作

* 4xx：客户端错误--请求有语法错误或请求无法实现

* 5xx：服务器端错误--服务器未能实现合法的请求

  

##### HTTP常用状态码

* **200 OK，当GET请求成功完成，DELETE或者PATCH请求同步完成。**

* 201 Created，对于那些要服务器创建对象的请求来说，资源已创建完毕。

* 202 Accepted，POST，DELETE或者PATCH请求提交成功，稍后将异步的进行处理。

* **400 Bad Request**

* **401 Unauthorized: 请求失败，因为用户没有进行认证**

* **403 Forbidden: 请求失败，因为用户被认定没有访问特定资源的权限**

* **404 Not Found: 请求的资源不存在**

* 405 Method Not Allowed：不支持该 Request 的方法

* **500 Internal Server Error: 服务器遇到一个错误，使其无法为请求提供服务**

* 501 Not Implemented：客户端发起的请求超出服务器的能力范围(比如，使用了服务器不支持的请求方法)时，使用此状态码。

* **502 Bad Gateway：代理使用的服务器遇到了上游的无效响应**

  

##### HTTP 1.1 协议方法类型

* **GET 请求指定的页面信息，并返回实体主体**

* HEAD 只请求页面的首部

* **POST 请求服务器接受所指定的文档作为对所标识的URI的新的从属实体**

* **PUT 从客户端向服务器传送的数据取代指定的文档的内容**

* **DELETE 请求服务器删除指定的页面**

* OPTIONS 允许客户端查看服务器的性能

* TRACE 请求服务器在响应中的实体主体部分返回所得到的内容 

* **PATCH 实体中包含一个表，表中说明与该URI所表示的原内容的区别**

  

### Restful接口

#### 定义好的规范，已经成功了一大半
* **在 REST 架构风格中，每一个 URI 代表一种资源。因此，URI 是每一个资源的地址的唯一资源定位符**
* API 接口都是不断演进的。因此，我们需要在一定程度上适应变化。在 RESTful API 中，API 接口应该尽量兼容之前的版本

#### 一个完整的Restful接口定义

```
接口名：创建用户                            //接口名
请求
POST /v1/users  HTTP/1.1       			 //请求行
HOST: school.unisinsight.com             //服务地址
Accept: application/json                 //Accept 发送端希望接收的数据类型
Content-Type: application/json           //Content-Type 发送端发送的实体数据类型
Authorization: bearer {token}            //身份验证信息
User: {userInfo}                         //用户信息
										 //空行
{										 //请求实体格式（字段格式及含义说明）
	"user_name": "lran",                 //用户名，非空必填，最小长度1，最大长度32
    "user_code": "172",                  //用户编号，非空必填，唯一，最小长度5，最大长度16
	"email": "liu.ran@unisinsight.com",  //邮箱，非必填
	"position": "Java"                   //岗位，非必填
}

响应
HTTP/1.1 201 Created 				      //状态行
Content-Type: application/json            //Content-Type 服务端返回的实体数据类型
                                          //空行
{										  //返回body（字段格式及含义说明）
	"id": 2101,
	"user_name": "lran",
	"user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

错误码定义
0000000001 系统内部异常
0101020001 用户已存在
...
```


#### Restful接口要素

* **接口名(请求行)**

* **请求头**

* **请求数据格式**

* 响应状态码

* 响应头

* **响应数据格式**

* **错误码定义**

  

#### 接口命名规范

* Resource URL仅用来表示**资源的路径**，及一些**特殊的actions**，一个URL能够表示**网络上唯一的资源**

* 以复数(名词)进行命名，不管返回单个或是多个资源

* 使用小写、数字及连字符（也有使用下划线的，公司级别应该保持一致）

* 资源的路径从根到子依次如下/{resources}/{resource_id}/{sub_resources}/{sub_resource_id}

  URI要具有很好的可读性，如：

  /学校/重庆邮电大学/学院/计算机科学与技术学院

  /学校/重庆邮电大学/食堂/xx食堂

  /食堂/xx食堂

* 示例

  - POST /v1/users										//创建用户
  - PUT  /v1/users/{userId}                         //更新用户
  - GET  /v1/users                                         //获取用户列表
  - GET  /v1/users/{userId}                         //获取用户详情
  - DELETE  /v1/users/{userId}                   //删除用户
  - PATCH /v1/users/{userId}/status         //更新用户状态
  
  

#### HTTP方法使用原则

* POST 创建资源；执行资源的actions；超长的GET

* GET 获取或查询资源

* PUT 更新资源

* PATCH 局部更新资源

* DELETE 删除接口
  注：HTTP协议方法本身带有约定俗成的语义，不能乱用HTTP方法，比如PUT更新和PATCH局部更新方法作用类似，但实现不同。

  

#### 接口设计原则
##### 单一职责原则
接口功能要尽量小、高内聚、定制服务

不好的设计

```
接口名：更新或添加用户                            
请求
POST /v1/users/add-or-update  HTTP/1.1      
HOST: school.unisinsight.com                
Accept: application/json             
Content-Type: application/json         
										
{
	"user_name": "lran",
    "user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

响应
HTTP/1.1 201 Created 				     
Content-Type: application/json           
                                          
{
	"id": 2101,
	"user_name": "lran",
	"user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

错误码定义
0000000001 系统内部异常
```
原因：职责不单一，接口同时拥有创建和更新的功能，接口可维护性差，不易于理解

修改后（拆分成添加用户和更新用户两个接口）

```
接口名：添加用户                            
请求
POST /v1/users  HTTP/1.1      
HOST: school.unisinsight.com                
Accept: application/json             
Content-Type: application/json 
										
{
	"user_name": "lran",
    "user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

响应
HTTP/1.1 201 Created 				     
Content-Type: application/json           
                                          
{
	"id": 2101,
	"user_name": "lran",
	"user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

错误码定义
0000000001 系统内部异常
```

```
接口名：更新用户                           
请求
PUT /v1/users/{userId}  HTTP/1.1      
HOST: school.unisinsight.com                
Accept: application/json             
Content-Type: application/json
										
{
	"user_name": "lran",
    "user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

响应
HTTP/1.1 200 OK 				     
Content-Type: application/json           
                                          
{
	"id": 2101,
	"user_name": "lran",
	"user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

错误码定义
0000000001 系统内部异常
...
```

不好的设计
```
接口名：添加学生到班级
POST /v1/classes/{classId}/students  HTTP/1.1      
HOST: school.unisinsight.com               
Accept: application/json             
Content-Type: application/json
										
{
	"user_id": "14001"
}

响应
HTTP/1.1 201 Created 				     
Content-Type: application/json           
                                          
{
	"id": 1711,
	"class_id": 1001,
    "user_id": "14001",
	"student_num": 35  //学生人数
}

错误码定义
0000000001 系统内部异常
...
```
原因：职责不单一，**返回学生人数功能应该由单独的接口提供**



##### 避免层级过深的URI

不好的设计
```
接口名：获取学生
GET /v1/schools/{schoolId}/classes/{classId}/sutdents
HOST: school.unisinsight.com
Accept: application/json             

响应
HTTP/1.1 200 OK	     
Content-Type: application/json           
                                          
[
    {
        "id": 2101,
        "user_name": "lran",
        "user_code": "172",
        "email": "liu.ran@unisinsight.com",
        "position": "Java"
    },
     {
        "id": 2102,
        "user_name": "张三",
        "user_code": "172",
        "email": "zhang.san@unisinsight.com",
        "position": "Java"
    }
]

错误码定义
0000000001 系统内部异常
```
原因：URI层级太深、列表没有分页功能

修改后

```
接口名：获取学生
GET /v1/sutdents?class_id={classId}&page_index={pageIndex}&page_size={pageSize}&order_filed={orderField} {orderRule}
HOST: school.unisinsight.com
Accept: application/json             

响应
HTTP/1.1 200 OK    
Content-Type: application/json           
                                          
{
	"page_param":{
		"total": 2,
		"page_index": 1
	},
	"data":[
        {
            "id": 2101,
            "user_name": "lran",
            "user_code": "172",
            "email": "liu.ran@unisinsight.com",
            "position": "Java"
        },
         {
            "id": 2102,
            "user_name": "张三",
            "user_code": "173",
            "email": "zhang.san@unisinsight.com",
            "position": "Java"
        }
	]
}

错误码定义
0000000001 系统内部异常
...
```


##### 避免接口数膨胀

不好的设计
​更新用户状态接口
​错误示例
​	1、禁用 PUT /v1/users/{userId}/forbid
​	2、启用 PUT /v1/users/{userId}/using
​	3、黑名单 PUT /v1/users/{userId}/black

修改后
```
接口名：更新用户                           
请求
PUT /v1/users/{userId}/status  HTTP/1.1      
HOST: school.unisinsight.com                
Accept: application/json             
Content-Type: application/json
										
{
	"status": "1"
}

响应
HTTP/1.1 200  OK	     
Content-Type: application/json           
                                          
{
	"status": 1
}

错误码定义
0000000001 系统内部异常
```



##### 暴露不必要的参数

接口参数中暴露没必要的参数
```
接口名：添加学生
请求
POST /v1/students   HTTP/1.1
HOST: school.unisinsight.com                
Accept: application/json             
Content-Type: application/json

{   
    "school_id": "10",//school_id和class_id冗余
	"class_id": 1001,
    "user_name": "lran"
}

响应
...
```
为了减少接口维护，在开发阶段把一些不必要的字段暴露出来，造成代码优化困难，接口使用不合理，如GET /v1/students/search 



##### 无意义的接口命名

* POST /v1/users/add

* PUT  /v1/users/{userId}/update

* GET  /v1/users/list

* GET  /v1/users/{userId}/detail

* DELETE  /v1/users/{userId}/delete

* GET /v1/configuration/student/info

  

### CURL示例
#### 创建（POST）

```
接口名：添加用户                            
请求
POST /v1/users  HTTP/1.1      
HOST: school.unisinsight.com                
Accept: application/json             
Content-Type: application/json
										
{
	"user_name": "lran",
    "user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

响应
HTTP/1.1 201 Created 				     
Content-Type: application/json           
                                          
{
	"id": 2101,
	"user_name": "lran",
	"user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

错误码定义
0000000001 系统内部异常
...
```
#### 更新（PUT/PATCH）

```
接口名：更新用户                           
请求
PUT /v1/users/{userId}  HTTP/1.1      
HOST: school.unisinsight.com                
Accept: application/json             
Content-Type: application/json
										
{
	"user_name": "lran",
    "user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

响应
HTTP/1.1 200 OK 				     
Content-Type: application/json           
                                          
{
	"id": 2101,
	"user_name": "lran",
	"user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

错误码定义
0000000001 系统内部异常
...
```
#### 获取详情（GET）

```
接口名：获取用户详情                           
请求
GET /v1/users/{userId}  HTTP/1.1      
HOST: school.unisinsight.com                
Accept: application/json             
										
响应
HTTP/1.1 200 OK 				     
Content-Type: application/json           
                                          
{
	"id": 2101,
	"user_name": "lran",
	"user_code": "172",
	"email": "liu.ran@unisinsight.com",
	"position": "Java"
}

错误码定义
0000000001 系统内部异常
...
```
#### 查询（GET/POST）

在URI参数过长的情况下使用POST请求代替GET

```
接口名：获取用户
GET /v1/users?page_index={pageIndex}&page_size={pageSize}&order_filed={orderField} {orderRule}
HOST: school.unisinsight.com
Accept: application/json             

响应
HTTP/1.1 200 OK    
Content-Type: application/json           
                                          
{
	"page_param":{
		"total": 2,
		"page_index": 1
	},
	"data":[
         {
            "id": 2101,
            "user_name": "lran",
            "user_code": "172",
            "email": "liu.ran@unisinsight.com",
            "position": "Java"
         },
         {
            "id": 2102,
            "user_name": "张三",
            "user_code": "173",
            "email": "zhang.san@unisinsight.com",
            "position": "Java"
        }
	]
}

错误码定义
0000000001 系统内部异常
...
```
#### 删除（DELETE）

```
接口名：删除用户                           
请求
DELETE /v1/users/{userId}  HTTP/1.1      
HOST: school.unisinsight.com                

响应
HTTP/1.1 200 OK 				     

错误码定义
0000000001 系统内部异常
...
```

### 参考资料
https://www.toutiao.com/i6691673426781798924/?tt_from=weixin&utm_campaign=client_share&wxshare_count=2&from=groupmessage&timestamp=1558224558&app=news_article&utm_source=weixin&isappinstalled=0&utm_medium=toutiao_android&req_id=20190519080918010152022233246C2B5&group_id=6691673426781798924&pbid=6693295242256860680
https://blog.csdn.net/yunqiinsight/article/details/85335968

