# LanZouApi-Java

## 说明
1. 支持检测文件是否被取消

2. 支持带密码的文件分享链接但不支持分享的文件夹

3. 支持生成直链或直接下载

4. 增加ios应用在线安装

5. 解析最终直链

6. 自动识别旧版链接替换为新版并解析

## 使用方法

url:蓝奏云外链链接

type:是否直接下载 值：down

pwd:外链密码

### 直接下载：

无密码：http://localhost:8080/tools/LanZou/api?url=https://wwx.lanzouj.com/i1czagj&type=download

有密码：http://localhost:8080/tools/LanZou/api?url=https://wwi.lanzouj.com/i5l6c2b&password=bd3p&type=download


### 输出直链：

无密码：http://localhost:8080/tools/LanZou/api?url=https://wwx.lanzouj.com/i1czagj

有密码：http://localhost:8080/tools/LanZou/api?url=https://wwi.lanzouj.com/b0zhlejg&password=ezr4


