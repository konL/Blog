Element-ui组件 ：https://element.eleme.cn/#/zh-CN/component/link

# **运行准备**

（1）开启redis：https://www.cnblogs.com/nanstar/p/13367747.html

在redis文件夹下开启PowerShell,输入` ./redis-server.exe`

（2）开启后端服务器

` 运行BlogVueApplication.java`

（3）开启前端

进入vueblog-vue项目，Terminal执行`npm run serve`
（4）准备数据

```sql
use konblog;
CREATE TABLE `m_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `status` int(5) NOT NULL,
  `created` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `m_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `content` longtext,
  `created` datetime NOT NULL ,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
INSERT INTO `konblog`.`m_user` (`id`, `username`, `avatar`, `email`, `password`, `status`, `created`, `last_login`) VALUES ('1', 'jiahui', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', NULL, '96e79218965eb72c92a549dd5a330112', '0', '2020-04-20 10:44:01', NULL);

INSERT INTO `konblog`.`m_user` (`id`, `username`, `avatar`, `email`, `password`, `status`, `created`, `last_login`) VALUES ('2', 'markerhub', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', NULL, '96e79218965eb72c92a549dd5a330112', '0', '2020-04-20 10:44:01', NULL);

```

# 页面展示

### （一）登录（/login）

账户1：jiahui，111111

账户2：markerhub，111111

**登录成功：**
![GIF 2022-3-5 13-16-29](https://user-images.githubusercontent.com/24618393/156869227-f2751f56-6744-40be-824e-f8f81a1203eb.gif)


**登陆失败：**

用户不存在或者密码错误
![2](https://user-images.githubusercontent.com/24618393/156869393-bd320e64-47e3-485c-8662-dec872e735dc.gif)

**检测到用户已经登录，点击退出则注销账号**
![3](https://user-images.githubusercontent.com/24618393/156869742-dfe04af5-98f1-4c35-a089-b7335026f93e.gif)

**检测到用户未登录却进入了blos页面，可点击登录后再进行编辑，发表文章操作（不登录也可阅读文章)**
![4](https://user-images.githubusercontent.com/24618393/156869777-263a5bb7-157f-4969-a1ea-cfd083836799.gif)


### （二）查看文章页面（blogs）
只要登录成功之后则直接进入文章页面，包含博客所有的文章，
接下来点击进去任意的文章查看文章内容
![image](https://user-images.githubusercontent.com/24618393/156869401-395fa253-455e-4bc8-8d0b-5a280a5c7898.png)
### （三）编辑页面（edit)
**点击发表文章**
![5](https://user-images.githubusercontent.com/24618393/156871048-9a5e7404-6c7b-497a-95cf-e46cb17955d4.gif)

**点击某个文章编辑**
![6](https://user-images.githubusercontent.com/24618393/156871451-837e345f-ebc2-4b3c-b3c9-bfd025afdfb9.gif)

**如果文章不是本人的文章不显示编辑按钮**
![7](https://user-images.githubusercontent.com/24618393/156871439-368c18c3-672f-4296-aea2-57423d9cee4c.gif)

### （四）查看文章（detail）
任意文章都可以点击进入查看
![8](https://user-images.githubusercontent.com/24618393/156871529-60df41fa-745e-4ea5-a6c0-775c3dd7d235.gif)



关于权限：

登录时候的表单验证

未登录的，header显示重新登录

到add提交之后提示需要登录
![9](https://user-images.githubusercontent.com/24618393/156871593-4bc7c36e-3c85-415e-b90a-b222060ec063.gif)


判断用户是否为主人才可以编辑

