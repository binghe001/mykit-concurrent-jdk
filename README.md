# 《深入理解高并发编程：JDK核心技术》——随书源码

## 关于本项目

📚 本项目是《深入理解高并发编程：JDK核心技术》随书源码

## 关于我

>冰河，互联网资深技术专家、[TVP腾讯云最具价值专家](https://cloud.tencent.com/tvp/member/669)、数据库技术专家、分布式与微服务架构专家，[《深入理解高并发编程：核心原理与案例实战》](https://item.jd.com/13190783.html)、[《深入理解分布式事务：原理与实战》](<https://item.jd.com/12972343.html>)、[《海量数据处理与大数据技术实战》](<https://item.jd.com/12710993.html>)、[《MySQL技术大全：开发、优化与运维实战》](<https://item.jd.com/13036154.html>) 图书作者，“冰河技术”微信公众号作者，可视化多数据源数据异构中间件mykit-data作者。多年来，一直致力于分布式系统架构、微服务、分布式数据库、分布式事务与大数据技术的研究，在高并发、高可用、高可扩展性、高可维护性和大数据等领域拥有丰富的架构经验。

- :bus: 作品：[`BingheGuide | 冰河指南`](https://github.com/binghe001/BingheGuide) | [`《深入理解高并发编程：核心原理与案例实战》`](https://github.com/binghe001/mykit-concurrent-principle) | [`《深入理解高并发编程：JDK核心技术》`](https://github.com/binghe001/mykit-concurrent-jdk) | [`数据同步`](https://github.com/binghe001/mykit-data) | [`Spring核心技术`](https://github.com/binghe001/spring-annotation-book) | [`分布式限流`](https://github.com/binghe001/mykit-ratelimiter) | [`分布式锁`](https://github.com/binghe001/mykit-lock) | [`分布式缓存`](https://github.com/binghe001/mykit-cache) | [`异步并行框架`](https://github.com/binghe001/mykit-async) | [`分布式事务`](https://github.com/binghe001/mykit-transaction-message) | [`简易版IM`](https://github.com/binghe001/mykit-chat) | [`微信SDK`](https://github.com/binghe001/mykit-wechat-sdk) | [`延迟队列`](https://github.com/binghe001/mykit-delay) | [`分布式ID`](https://github.com/binghe001/mykit-serial) | [更多搜索...](https://github.com/binghe001?tab=repositories)
- :seedling: 干货：[公众号『 冰河技术 』](https://img-blog.csdnimg.cn/20210426115714643.jpg)
- :pencil: 博客：[binghe.gitcode.host](https://binghe.gitcode.host/) - 硬核文章，应有尽有！
- :tv: 视频：[B站 冰河技术](https://space.bilibili.com/517638832)
- :love_letter: 微信：[hacker_binghe](https://binghe.gitcode.host/images/personal/hacker_binghe.jpg) - 备注来意
- :feet: 我的知识星球：[手写企业级中间件项目、大厂高并发秒杀系统、并发编程、性能调优、框架源码、分布式、微服务、1对1解答、答辩晋升技巧、定期直播](https://binghe.gitcode.host/md/starball/2023-01-01-2023%E6%98%9F%E7%90%83%E6%96%B0%E5%B9%B4%E8%A7%84%E5%88%92.html)

---

👨‍💻作者：冰河
<br/>
🌱微信：hacker_binghe —— 可以添加微信备注【高并发读书加群】

>沉淀、成长、突破，帮助他人，成就自我！

## ⛳ 目录

- [Github](https://github.com/binghe001/mykit-concurrent-jdk) | [Gitee](https://gitee.com/binghe001/mykit-concurrent-jdk) | [GitCode](https://gitcode.net/binghe001/mykit-concurrent-jdk)
- [1. 内容简述](#) - 添加冰河微信【hacker_binghe】备注【高并发读书加群】
- [2. 书籍购买](#2-书籍购买)
- [3. 勘误记录](#3-勘误记录) - 非常感谢，`各位小伙伴提交阅读过程中发现的错字和问题`。
- [4. 感谢](#4-感谢) - 非常感谢图书编辑、各位写推荐语的大佬、冰河的读者和一直支持帮助和鼓励冰河的小伙伴们。

### 1. 内容简述

<div align="center">
    <a href="#" target="_blank">
        <img src="https://binghe.gitcode.host/assets/images/knowledge/book/2023-02-20-001-jdk.jpg?raw=true" width="250px">
    </a>
</div>

<br/>

随着计算机与互联网技术的不断发展，CPU硬件的核心数也在不断提升，并发编程越来越普及，但是并发编程并不像其他业务那样简单明了。在编写并发程序时，往往会出现各种各样的Bug，这些Bug常常以某种“诡异”的形式出现，然后迅速消失，并且在大部分场景下难以复现。所以，高并发编程着实是一项让程序员头疼的技术。在“冰河技术丛书”之“深入理解高并发编程”系列的第1部作品——《深入理解高并发编程：核心原理与案例实战》一书中，全面细致地介绍了高并发编程的基础知识、核心原理、实战案例和系统架构等内容，帮助读者从根本上理解并发编程出现各种Bug的根源，并从原理与实战层面找到解决问题的方案。  
  
本书是“冰河技术丛书”之“深入理解高并发编程”系列的第2部作品，从实际需求出发，全面细致地介绍了JDK高并发编程的基础知识、核心工具和线程池核心技术。每个章节根据实际需要配有相关的原理图、流程图和实战案例。在线程池核心技术篇，还提供了完整的手动编写线程池的案例源码。  
  
通过阅读和学习本书，读者可以更加全面、深入、透彻地理解JDK高并发编程知识，提高对高并发编程问题的处理能力和项目实战能力，并提高站在更高层面解决高并发编程系统架构问题的能力。


### 2. 书籍购买

**链接下单**：[https://u.jd.com/izMwOkE](https://u.jd.com/izMwOkE)

本书共 16 章：

- 第 1 ~ 2 章:简单地介绍了进程与线程的基本概念、线程调度与上下文切换、进程与线程的综合对比、如何查看进程与线程的运行时信息，以及线程和线程组的基本操作。。
- 第 3 ~ 13 章:通过大量源码和案例详细介绍了JDK的各种并发工具，涵盖同步集合、并发List集合类、并发Set集合类、并发Map集合类、并发阻塞队列、并发非阻塞队列、并发工具类、锁工具类、无锁原子类、线程工具类和异步编程工具类。几乎每个章节都配有JDK核心工具类的源码及实战案例，有助于读者理解。。
- 第 14 ~ 16 章:深入剖析了JDK中线程池的核心源码。包括线程池顶层接口和抽象类、线程池正确运行的核心流程、线程池执行任务的核心流程、Worker线程的核心流程、线程池优雅退出的核心流程、ScheduledThreadPoolExecutor类与Timer类的区别、定时任务线程池的初始化、调度流程和优雅关闭流程等。通过对本篇的学习，读者能够从源码级别深刻理解线程池的核心原理和执行流程。。

### 3. 勘误记录

---

### 4. 感谢

感谢图书编辑：张晶、杨中兴

感谢大佬推荐（排名部分先后）：蒋涛（CSDN创始人、总裁）、邹欣（CSDN副总裁）、李海翔（腾讯数据库资深研究员、首席架构师）、林子熠（阿里巴巴JVM技术专家、CCF系统软件专委会执行委员）、于君泽（资深技术专家、公众号“技术琐话”作者）、沈剑（互联网架构专家，公众号“架构师之路”作者）、秦金卫（长亮科技平台技术部副总经理、Apache Dubbo/ShardingSphere PMC）、张开涛（《亿级流量网站架构核心技术》作者）、季敏（Seata开源社区创始人）、李鹏云（杭州任你说智能科技CTO）、程军（前饿了么技术总监，公众号“军哥手记”作者）、骆俊武（京东零售架构师）、纯洁的微笑（公众号“纯洁的微笑”作者）、黄哲铿/Mr.K（“顿悟山丘”咨询创始人、公众号“技术领导力”作者）、李伟（Apache RocketMQ北京社区联合发起人 && Commiter）、翟永超（公众号“程序猿DD”维护者、《Spring Cloud微服务实战》作者）

感谢冰河的读者和一直支持帮助和鼓励冰河的小伙伴们。

<div align="center">
    <a href="https://github.com/binghe001/BingheGuide">关注冰河技术，解锁更多技能！</a>
</div>


## 出版其他书籍

<div align="center">
    <img src="https://img-blog.csdnimg.cn/fe76310aea734752b3b79c4df1438943.jpeg?raw=true" width="80%">
      <div style="font-size: 9px;"><a href="https://item.jd.com/13190783.html">《深入理解高并发编程：核心原理与案例实战》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/5ff576f8189d46cf83c59fe4e5efc6dd.png?raw=true" width="80%">
      <div style="font-size: 18px;"><a href="https://item.jd.com/10067507938306.html">《深入高平行開發：深度原理&專案實戰》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/5ee367b68023466a87f66763a64a4133.jpg?raw=true" width="100%">
      <div style="font-size: 9px;"><a href="https://item.jd.com/12972343.html">《深入理解分布式事务：原理与实战》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/20210426115257555.png?raw=true" width="80%">
      <div style="font-size: 9px;"><a href="https://item.jd.com/13036154.html">《MySQL技术大全：开发、优化与运维实战》</a></div>
    <br/>
</div>

<div align="center">
    <img src="https://img-blog.csdnimg.cn/20200828011209412.png?raw=true" width="80%">
      <div style="font-size: 9px;"><a href="https://item.jd.com/12710993.html">《海量数据处理与大数据技术实战》</a></div>
    <br/>
</div>


## 加群交流

本群的宗旨是给大家提供一个良好的技术学习交流平台，所以杜绝一切广告！由于微信群人满 100 之后无法加入，请扫描下方二维码先添加作者 “冰河” 微信(hacker_binghe)，备注：`学习加群`。

<div align="center">
    <img src="https://binghe.gitcode.host/images/personal/hacker_binghe.jpg?raw=true" width="180px">
    <div style="font-size: 9px;">冰河微信</div>
    <br/>
</div>




## 公众号

分享各种编程语言、开发技术、分布式与微服务架构、分布式数据库、分布式事务、云原生、大数据与云计算技术和渗透技术。另外，还会分享各种面试题和面试技巧。内容在 **冰河技术** 微信公众号首发，强烈建议大家关注。

<div align="center">
    <img src="https://img-blog.csdnimg.cn/20210426115714643.jpg?raw=true" width="180px">
    <div style="font-size: 9px;">公众号：冰河技术</div>
    <br/>
</div>



## 星球

加入星球 **[冰河技术](http://m6z.cn/6aeFbs)**，可以获得本站点所有学习内容的指导与帮助。如果你遇到不能独立解决的问题，也可以添加冰河的微信：**hacker_binghe**， 我们一起沟通交流。另外，在星球中不只能学到实用的硬核技术，还能学习**实战项目**！

关注 [冰河技术](https://img-blog.csdnimg.cn/20210426115714643.jpg?raw=true)公众号，回复 `星球` 可以获取入场优惠券。

<div align="center">
    <img src="https://binghe.gitcode.host/images/personal/xingqiu.png?raw=true" width="180px">
    <div style="font-size: 9px;">知识星球：冰河技术</div>
    <br/>
</div>

## ConcurrentLinkedQueue的remove()方法内存泄露

[https://bugs.java.com/bugdatabase/view_bug.do?bug_id=8137185](https://bugs.java.com/bugdatabase/view_bug.do?bug_id=8137185)