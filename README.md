# bbupdate
bbupdate用于通过shell来控制后台业务分流


该项目由dubbo-admin项目简化改变而来，主要是为了操作方便些 

有mylibs和mylibss这两个东西是为了原生的dubbo-admin在jdk8布署会出现一些问题，根据网上提示去进行的更改。

原来的操作后台业务需要到dubbo-admin的web界面上去。
为了使用配合shell来进行后台业务操作，所以特制作了这个东西。

这个是项目的初始化，运行是没有任何问题，这里在src目录上有两个mylibs和mylibss文件夹

mylibs文件夹是用来存放spring2的依赖的
mylibss文件夹是用来存放spring3的依赖的

该项目使用了maven  我们公司自己有单独的maven私服，用的是nexus架设，
其实这个项目没有使用其它java包，因为是从dubbo-admin提取出来的。所以需要dubbo.jar这个由是dubbo这个父项目打包出来的，我用提2.5.4的，自己打包出来传到私服里的。
另外还用了servlet-api.jar 和jsp-api.jar这两个都是我从tomcat里拷出来传到私服里的。主要是在maven库里一搜，有许多，我都不知道用那个了。所以还不如用自己从tomcat中取。

其它的可以在maven官网库里找到。
