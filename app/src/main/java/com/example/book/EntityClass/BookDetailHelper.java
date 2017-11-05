package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/11/1.
 */

public class BookDetailHelper {

    /**
     * rating : {"max":10,"numRaters":123,"average":"8.5","min":0}
     * subtitle : Android
     * author : ["郭霖"]
     * pubdate : 2016-12-1
     * tags : [{"count":83,"name":"Android","title":"Android"},{"count":42,"name":"特别适合Android初学者的书","title":"特别适合Android初学者的书"},{"count":36,"name":"移动开发","title":"移动开发"},{"count":32,"name":"编程","title":"编程"},{"count":32,"name":"安卓开发","title":"安卓开发"},{"count":30,"name":"android","title":"android"},{"count":24,"name":"Java","title":"Java"},{"count":22,"name":"安卓","title":"安卓"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s29572743.jpg
     * binding : 平装
     * translator : []
     * catalog : 第1章　开始启程——你的第一行Android代码　　1
     1.1　了解全貌——Android王国简介　　2
     1.1.1　Android系统架构　　2
     1.1.2　Android已发布的版本　　3
     1.1.3　Android应用开发特色　　4
     1.2　手把手带你搭建开发环境　　5
     1.2.1　准备所需要的工具　　5
     1.2.2　搭建开发环境　　5
     1.3　创建你的第一个Android项目　　9
     1.3.1　创建HelloWorld项目　　9
     1.3.2　启动模拟器　　12
     1.3.3　运行HelloWorld　　15
     1.3.4　分析你的第一个Android程序　　16
     1.3.5　详解项目中的资源　　22
     1.3.6　详解build.gradle文件　　23
     1.4　前行必备——掌握日志工具的使用　　26
     1.4.1　使用Android的日志工具Log　　26
     1.4.2　为什么使用Log而不使用System.out　　27
     1.5　小结与点评　　29
     第2章　先从看得到的入手——探究活动　　30
     2.1　活动是什么　　30
     2.2　活动的基本用法　　30
     2.2.1　手动创建活动　　31
     2.2.2　创建和加载布局　　32
     2.2.3　在AndroidManifest文件中注册　　35
     2.2.4　在活动中使用Toast　　37
     2.2.5　在活动中使用Menu　　38
     2.2.6　销毁一个活动　　40
     2.3　使用Intent在活动之间穿梭　　41
     2.3.1　使用显式Intent　　41
     2.3.2　使用隐式Intent　　44
     2.3.3　更多隐式Intent的用法　　46
     2.3.4　向下一个活动传递数据　　50
     2.3.5　返回数据给上一个活动　　51
     2.4　活动的生命周期　　53
     2.4.1　返回栈　　53
     2.4.2　活动状态　　54
     2.4.3　活动的生存期　　55
     2.4.4　体验活动的生命周期　　56
     2.4.5　活动被回收了怎么办　　62
     2.5　活动的启动模式　　63
     2.5.1　standard　　64
     2.5.2　singleTop　　65
     2.5.3　singleTask　　67
     2.5.4　singleInstance　　68
     2.6　活动的最佳实践　　71
     2.6.1　知晓当前是在哪一个活动　　71
     2.6.2　随时随地退出程序　　72
     2.6.3　启动活动的最佳写法　　74
     2.7　小结与点评　　75
     第3章　软件也要拼脸蛋——UI开发的点点滴滴　　76
     3.1　如何编写程序界面　　76
     3.2　常用控件的使用方法　　77
     3.2.1　TextView　　77
     3.2.2　Button　　80
     3.2.3　EditText　　82
     3.2.4　ImageView　　86
     3.2.5　ProgressBar　　88
     3.2.6　AlertDialog　　91
     3.2.7　ProgressDialog　　93
     3.3　详解4种基本布局　　94
     3.3.1　线性布局　　94
     3.3.2　相对布局　　100
     3.3.3　帧布局　　103
     3.3.4　百分比布局　　105
     3.4　系统控件不够用？创建自定义控件　　108
     3.4.1　引入布局　　109
     3.4.2　创建自定义控件　　111
     3.5　最常用和最难用的控件——ListView　　113
     3.5.1　ListView的简单用法　　114
     3.5.2　定制ListView的界面　　115
     3.5.3　提升ListView的运行效率　　119
     3.5.4　ListView的点击事件　　120
     3.6　更强大的滚动控件——RecyclerView　　122
     3.6.1　RecyclerView的基本用法　　122
     3.6.2　实现横向滚动和瀑布流布局　　125
     3.6.3　RecyclerView的点击事件　　130
     3.7　编写界面的最佳实践　　132
     3.7.1　制作Nine-Patch图片　　132
     3.7.2　编写精美的聊天界面　　135
     3.8　小结与点评　　141
     第4章　手机平板要兼顾——探究碎片　　142
     4.1　碎片是什么　　142
     4.2　碎片的使用方式　　144
     4.2.1　碎片的简单用法　　144
     4.2.2　动态添加碎片　　147
     4.2.3　在碎片中模拟返回栈　　150
     4.2.4　碎片和活动之间进行通信　　151
     4.3　碎片的生命周期　　151
     4.3.1　碎片的状态和回调　　151
     4.3.2　体验碎片的生命周期　　153
     4.4　动态加载布局的技巧　　156
     4.4.1　使用限定符　　156
     4.4.2　使用最小宽度限定符　　159
     4.5　碎片的最佳实践——一个简易版的新闻应用　　160
     4.6　小结与点评　　169
     第5章　全局大喇叭——详解广播机制　　170
     5.1　广播机制简介　　170
     5.2　接收系统广播　　171
     5.2.1　动态注册监听网络变化　　171
     5.2.2　静态注册实现开机启动　　174
     5.3　发送自定义广播　　177
     5.3.1　发送标准广播　　177
     5.3.2　发送有序广播　　179
     5.4　使用本地广播　　183
     5.5　广播的最佳实践——实现强制下线功能　　185
     5.6　Git时间——初识版本控制工具　　192
     5.6.1　安装Git　　192
     5.6.2　创建代码仓库　　193
     5.6.3　提交本地代码　　195
     5.7　小结与点评　　195
     第6章　数据存储全方案——详解持久化技术　　196
     6.1　持久化技术简介　　196
     6.2　文件存储　　197
     6.2.1　将数据存储到文件中　　197
     6.2.2　从文件中读取数据　　201
     6.3　SharedPreferences存储　　203
     6.3.1　将数据存储到SharedPreferences中　　203
     6.3.2　从SharedPreferences中读取数据　　206
     6.3.3　实现记住密码功能　　208
     6.4　SQLite数据库存储　　211
     6.4.1　创建数据库　　211
     6.4.2　升级数据库　　216
     6.4.3　添加数据　　219
     6.4.4　更新数据　　222
     6.4.5　删除数据　　224
     6.4.6　查询数据　　225
     6.4.7　使用SQL操作数据库　　228
     6.5　使用LitePal操作数据库　　229
     6.5.1　LitePal简介　　229
     6.5.2　配置LitePal　　230
     6.5.3　创建和升级数据库　　231
     6.5.4　使用LitePal添加数据　　236
     6.5.5　使用LitePal更新数据　　237
     6.5.6　使用LitePal删除数据　　240
     6.5.7　使用LitePal查询数据　　241
     6.6　小结与点评　　243
     第7章　跨程序共享数据——探究内容提供器　　244
     7.1　内容提供器简介　　244
     7.2　运行时权限　　245
     7.2.1　Android权限机制详解　　245
     7.2.2　在程序运行时申请权限　　249
     7.3　访问其他程序中的数据　　254
     7.3.1　ContentResolver的基本用法　　254
     7.3.2　读取系统联系人　　256
     7.4　创建自己的内容提供器　　260
     7.4.1　创建内容提供器的步骤　　261
     7.4.2　实现跨程序数据共享　　265
     7.5　Git时间——版本控制工具进阶　　275
     7.5.1　忽略文件　　275
     7.5.2　查看修改内容　　276
     7.5.3　撤销未提交的修改　　278
     7.5.4　查看提交记录　　279
     7.6　小结与点评　　280
     第8章　丰富你的程序——运用手机多媒体　　281
     8.1　将程序运行到手机上　　281
     8.2　使用通知　　283
     8.2.1　通知的基本用法　　283
     8.2.2　通知的进阶技巧　　289
     8.2.3　通知的高级功能　　291
     8.3　调用摄像头和相册　　293
     8.3.1　调用摄像头拍照　　294
     8.3.2　从相册中选择照片　　298
     8.4　播放多媒体文件　　303
     8.4.1　播放音频　　303
     8.4.2　播放视频　　307
     8.5　小结与点评　　311
     第9章　看看精彩的世界——使用网络技术　　312
     9.1　WebView的用法　　312
     9.2　使用HTTP协议访问网络　　314
     9.2.1　使用HttpURLConnection　　315
     9.2.2　使用OkHttp　　319
     9.3　解析XML格式数据　　321
     9.3.1　Pull解析方式　　324
     9.3.2　SAX解析方式　　326
     9.4　解析JSON格式数据　　329
     9.4.1　使用JSONObject　　330
     9.4.2　使用GSON　　331
     9.5　网络编程的最佳实践　　334
     9.6　小结与点评　　338
     第10章　后台默默的劳动者——探究服务　　339
     10.1　服务是什么　　339
     10.2　Android多线程编程　　340
     10.2.1　线程的基本用法　　340
     10.2.2　在子线程中更新UI　　341
     10.2.3　解析异步消息处理机制　　345
     10.2.4　使用AsyncTask　　347
     10.3　服务的基本用法　　349
     10.3.1　定义一个服务　　349
     10.3.2　启动和停止服务　　352
     10.3.3　活动和服务进行通信　　355
     10.4　服务的生命周期　　359
     10.5　服务的更多技巧　　359
     10.5.1　使用前台服务　　359
     10.5.2　使用IntentService　　361
     10.6　服务的最佳实践——完整版的下载示例　　365
     10.7　小结与点评　　378
     第11章　Android特色开发——基于位置的服务　　379
     11.1　基于位置的服务简介　　379
     11.2　申请API Key　　380
     11.3　使用百度定位　　384
     11.3.1　准备LBS SDK　　384
     11.3.2　确定自己位置的经纬度　　386
     11.3.3　选择定位模式　　391
     11.3.4　看得懂的位置信息　　393
     11.4　使用百度地图　　395
     11.4.1　让地图显示出来　　395
     11.4.2　移动到我的位置　　397
     11.4.3　让“我”显示在地图上　　400
     11.5　Git时间——版本控制工具的高级用法　　402
     11.5.1　分支的用法　　403
     11.5.2　与远程版本库协作　　404
     11.6　小结与点评　　406
     第12章　最佳的UI体验——MaterialDesign实战　　407
     12.1　什么是Material Design　　407
     12.2　Toolbar　　408
     12.3　滑动菜单　　415
     12.3.1　DrawerLayout　　415
     12.3.2　NavigationView　　418
     12.4　悬浮按钮和可交互提示　　423
     12.4.1　FloatingActionButton　　424
     12.4.2　Snackbar　　427
     12.4.3　CoordinatorLayout　　428
     12.5　卡片式布局　　430
     12.5.1　CardView　　431
     12.5.2　AppBarLayout　　437
     12.6　下拉刷新　　440
     12.7　可折叠式标题栏　　443
     12.7.1　CollapsingToolbarLayout　　443
     12.7.2　充分利用系统状态栏空间　　453
     12.8　小结与点评　　456
     第13章　继续进阶——你还应该掌握的高级技巧　　457
     13.1　全局获取Context的技巧　　457
     13.2　使用Intent传递对象　　461
     13.2.1　Serializable方式　　461
     13.2.2　Parcelable方式　　463
     13.3　定制自己的日志工具　　464
     13.4　调试Android程序　　466
     13.5　创建定时任务　　469
     13.5.1　Alarm机制　　469
     13.5.2　Doze模式　　471
     13.6　多窗口模式编程　　472
     13.6.1　进入多窗口模式　　473
     13.6.2　多窗口模式下的生命周期　　475
     13.6.3　禁用多窗口模式　　479
     13.7　Lambda表达式　　481
     13.8　总结　　485
     第14章　进入实战——开发酷欧天气　　486
     14.1　功能需求及技术可行性分析　　486
     14.2　Git时间——将代码托管到GitHub上　　489
     14.3　创建数据库和表　　494
     14.4　遍历全国省市县数据　　499
     14.5　显示天气信息　　509
     14.5.1　定义GSON实体类　　509
     14.5.2　编写天气界面　　514
     14.5.3　将天气显示到界面上　　520
     14.5.4　获取必应每日一图　　526
     14.6　手动更新天气和切换城市　　532
     14.6.1　手动更新天气　　532
     14.6.2　切换城市　　535
     14.7　后台自动更新天气　　540
     14.8　修改图标和名称　　542
     14.9　你还可以做的事情　　543
     第15章　最后一步——将应用发布到360应用商店　　545
     15.1　生成正式签名的APK文件　　545
     15.1.1　使用Android Studio生成　　546
     15.1.2　使用Gradle生成　　548
     15.1.3　生成多渠道APK文件　　551
     15.2　申请360开发者账号　　554
     15.3　发布应用程序　　556
     15.4　嵌入广告进行盈利　　560
     15.4.1　注册腾讯广告联盟账号　　560
     15.4.2　新建媒体和广告位　　562
     15.4.3　接入广告SDK　　564
     15.4.4　重新发布应用程序　　569
     15.5　结束语　　570
     * ebook_url : https://read.douban.com/ebook/35604638/
     * pages : 570
     * images : {"small":"https://img3.doubanio.com/spic/s29572743.jpg","large":"https://img3.doubanio.com/lpic/s29572743.jpg","medium":"https://img3.doubanio.com/mpic/s29572743.jpg"}
     * alt : https://book.douban.com/subject/26915433/
     * id : 26915433
     * publisher : 人民邮电出版社
     * isbn10 : 7115439788
     * isbn13 : 9787115439789
     * title : 第一行代码：Android（第2版）
     * url : https://api.douban.com/v2/book/26915433
     * alt_title :
     * author_intro : 郭霖
     Android软件开发工程师。从事Android开发工作6年，有着丰富的项目实战经验，负责及参与开发过多款移动应用与游戏，对Android系统架构及应用层开发有着深入的理解。
     2013年3月开始，在CSDN上发表Android技术相关博文，很快获得了大量网友的好评。目前博客访问量已超过500万次，评论超过10000条。荣获CSDN认证专家，并被连续评选为CSDN 2013、2014、2015年度博客之星。
     * summary : 本书被广大Android 开发者誉为“Android 学习第一书”。全书系统全面、循序渐进地介绍了Android软件开发的必备知识、经验和技巧。
     第2版基于Android 7.0 对第1 版进行了全面更新，将所有知识点都在最新的Android 系统上进行重新适配，使用 全新的Android Studio 开发工具代替之前的Eclipse，并添加了对Material Design、运行时权限、Gradle、RecyclerView、百分比布局、OkHttp、Lambda 表达式等全新知识点的详细讲解。
     本书内容通俗易懂，由浅入深，既是Android 初学者的入门必备，也是Android 开发者的进阶首选。
     * ebook_price : 39.99
     * price : CNY 79.00
     */

    private RatingBean rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String ebook_url;
    private String pages;
    private ImagesBean images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String ebook_price;
    private String price;
    private List<String> author;
    private List<TagsBean> tags;
    private List<?> translator;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getEbook_url() {
        return ebook_url;
    }

    public void setEbook_url(String ebook_url) {
        this.ebook_url = ebook_url;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getEbook_price() {
        return ebook_price;
    }

    public void setEbook_price(String ebook_price) {
        this.ebook_price = ebook_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<?> getTranslator() {
        return translator;
    }

    public void setTranslator(List<?> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 123
         * average : 8.5
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/spic/s29572743.jpg
         * large : https://img3.doubanio.com/lpic/s29572743.jpg
         * medium : https://img3.doubanio.com/mpic/s29572743.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class TagsBean {
        /**
         * count : 83
         * name : Android
         * title : Android
         */

        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
