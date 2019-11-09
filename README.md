## 添加本地jar到Maven私人仓库小工具

[![Badge](https://img.shields.io/badge/link-996.icu-%23FF4D5B.svg?style=flat-square)](https://996.icu/#/zh_CN)
[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg?style=flat-square)](https://github.com/996icu/996.ICU/blob/master/LICENSE)
[![Slack](https://img.shields.io/badge/slack-996icu-green.svg?style=flat-square)](https://join.slack.com/t/996icu/shared_invite/enQtNjI0MjEzMTUxNDI0LTkyMGViNmJiZjYwOWVlNzQ3NmQ4NTQyMDRiZTNmOWFkMzYxZWNmZGI0NDA4MWIwOGVhOThhMzc3NGQyMDBhZDc)
[![HitCount](http://hits.dwyl.io/996icu/996.ICU.svg)](http://hits.dwyl.io/996icu/996.ICU)

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

[在这部分代码里](https://github.com/wangzhaoning/maven-add-local-package/blob/master/src/main/java/main/java/GenerateDependency.java#L79-L88 "code")，需要按照你jar包的名称修改逻辑。

