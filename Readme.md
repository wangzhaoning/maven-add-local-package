## 添加本地jar到Maven私人仓库小工具

**仅支持Windows下使用**

### 使用方式
在主函数里添加路径运行即可

### 执行模块
- 调用cmd.exe 重命名所有jar包加上版本号默认 1.0
- 调用cmd.exe 执行
```
mvn install:install-file -Dfile=jarname-1.0.jar -DgroupId=jarname -DartifactId=jarname -Dversion=1.0 -Dpackaging=jar //添加到本地仓库
```
- 打印`dependency`添加到`pom`即可

### 注意
[在代码的这部分]:[url] 需要按照你jar包的名称修改逻辑

[url]: https://github.com/wangzhaoning/maven-add-local-package/blob/master/src/main/java/main/java/GenerateDependency.java#L79-L88
