# EasyAOP
* 基于CGLib的Java面向切面框架

### 使用方法
* 创建一个继承于Aspect的类
<BLOCKQUOTE>简单的继承方式是覆盖before,doSlicedMethod和after方法
这三个方法分别是,方法前,方法本身,方法完成后
直接覆盖doSlicedMethod会导致原方法不被调用
这时就需要调用super.doSlicedMethod
如果面向切面的是接口(以及其他原方法不可被调用的情况)
也需要覆盖此方法避免接口被调用
如果必要,可以重写addChips方法,添加一些Chips
具体关于Chips的处理方式,addChips有相应的JavaDoc</BLOCKQUOTE>
* 对所需要使用切面的类/方法添加注解
<BLOCKQUOTE>SliceAllMehod 是针对类/接口的注解
SliceTheMethod 是针对方法的注解
targetAspectClass 的 value必须继承于Aspect
</BLOCKQUOTE>
* 通过EasyAOP.getProxy()获取原类的代理
<BLOCKQUOTE>每次获取的代理都会是一个新的对象</BLOCKQUOTE>
* Use it

### 内部实现
* CGLib是一个字节码生成框架,依赖于它可以实现针对所有Class的(动态)代理
* 调用原方法代理的时候,将会运行该方法对应Aspect的aspectRun
* aspectRun会依次运行所有AspectChip
* Aspect.doSlicedMethod(原方法),Aspect.before,Aspect.after最终依旧是以AspectChip形式被调用的
* Aspect在构造的时候,会运行addChips方法添加AspectChip,会将三个方法转化成Chip也加入到运行List,最后会对List从小到大排序
* 每次调用aspectRun时的AspectMessage都是一个新的对象(通常情况下)
* Java的形参改变之后,并不改变传入参数的引用,所以无法(简单的)实现多个Chips之间的message传递,所以AspectMessage的存在意义是,对对象进行操作(而不是对引用进行操作),以发送/获取消息

### 更新日志
* 2016-3-28 完成整体项目
* 2016-3-29 修改AspectChip之间的Message模块,使用简单的方式实现


### LICENSE
* UNDER THE [APACHE LICENSE VERSION 2.0](http://www.apache.org/licenses/LICENSE-2.0 ) LICENSE

***
**SunTao UESTC mrls@live.cn**