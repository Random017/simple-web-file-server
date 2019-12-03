###1，配置文件存储路径 
C:/test/sfs/resources/
###2，配置文件服务器域名前缀 
http://127.0.0.1/resources/
###3，在Nginx中如下配置
        location ^~ /resources/ {
            root   C:/test/sfs;
        }        
请求URL：http://127.0.0.1/resources/2019/12/3/14/989510cb-7cbd-4f6c-abaf-c61cdccfd7aa.jpg

等价于访问 C:/test/sfs/resources/2019/12/3/14/989510cb-7cbd-4f6c-abaf-c61cdccfd7aa.jpg