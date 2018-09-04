<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>mymeituan</title>
    <link rel="stylesheet" href="css/base.css">
    <script src="js/main.js"></script>
</head>
<body>
<div id="top">
    <div class="top-content">
        <div class="top-bar-position">
            <span>广州</span>
            <a class="change-city" href="javascript:;">切换城市</a>
            <span class="near-citys">[<a href="">佛山</a><a href="">花都</a><a href="">顺德</a>]</span>
            <span class="user-entry">
                <a class="growth-entry" href="javascript:;">${sessionScope.usercode.username}</a>
                <a class="extra-entry" href="/user/logout">退出</a>
            </span>
        </div>

        <ul class="top-nav-first">
            <li><a href="">我的美团</a></li>
            <li class="nav-app"><a href="">手机APP</a></li>
            <li><a href="/shop_login">商家中心</a></li>
            <li><a href="">网站导航</a></li>
        </ul>
    </div>
    <header>
        <div class="header-content">
            <div class="header-title">
                <a href="#"><img src="/imgs/meituan_logo.png" alt=""></a>
            </div>

            <div class="header-search-module">
                <div class="header-search-block">
                    <input class="header-search-input" value="搜索商家或地点" type="text">
                    <button class="header-search-btn">
                        <span class="search icon"></span>
                    </button>
                </div>
                <div class="header-search-hotword">
                    <a href="javascript:;">广州长隆欢乐世界</a>
                    <a href="javascript:;">广州长隆水上乐园</a>
                    <a href="javascript:;">广州长隆野生动物世界</a>
                    <a href="javascript:;">正佳极地海洋世界</a>
                    <a href="javascript:;">广州长隆国际大马戏</a>
                    <a href="javascript:;">蒙肥羊</a>
                    <a href="javascript:;">广州塔</a>
                    <a href="javascript:;">四海一家</a>
                </div>
            </div>
            <a href="javascript:;">
                <ul class="header-commitment">
                    <li class="commitment-item-1">
                        <i></i>
                        <span>随时退</span>
                    </li>
                    <li class="commitment-item-2">
                        <i></i>
                        <span>不满意免单</span>
                    </li>
                    <li class="commitment-item-3">
                        <i></i>
                        <span>过期退</span>
                    </li>
                </ul>
            </a>
        </div>
    </header>

    <div id="index-page">
        <div class="index-page-top clear">
            <div class="left-banner">
                <div class="left-banner-maintitle">全部分类</div>
                <div class="left-menu">
                    <ul>
                        <li>美食</li>
                        <li><a href="/waimai_index">外卖<a></li>
                        <li>酒店</li>
                        <li>榛果民宿</li>
                        <li>猫眼电影</li>
                        <li>机票 / 火车票</li>
                        <li>休闲娱乐/KTV</li>
                        <li>生活服务</li>
                        <li>丽人 / 美发 / 美容</li>
                        <li>结婚 / 婚纱摄影</li>
                        <li>亲子 / 儿童乐园</li>
                        <li>家具 / 建材</li>
                        <li>酒吧 / 密室逃脱</li>
                        <li>学习培训 / 音乐培训</li>
                    </ul>
                </div>
            </div>
            <div class="right-banner">

                <div class="home-header-links">
                    <a href="waimai_index.html">美团外卖</a>
                    <a href="javascript:;">猫眼电影</a>
                    <a href="javascript:;">美团酒店<span class="nav-promotion">推荐</span></a>
                    <a href="javascript:;">民宿／公寓</a>
                    <a href="javascript:;">商家入驻</a>
                </div>

                <div class="banner-row">
                    <ul id="banner-slider-img">
                        <li style="filter:alpha(opacity=100); opacity:1;"><a href="waimai_index.html"><img src="/imgs/content_1.jpg" alt="1"></a></li>
                        <li><a href="waimai_index.html"><img src="/imgs/content_2.jpg" alt="2"></a></li>
                        <li><a href="waimai_index.html"><img src="/imgs/content_3.jpg" alt="3"></a></li>
                        <li><a href="waimai_index.html"><img src="/imgs/content_4.jpg" alt="4"></a></li>
                    </ul>

                    <a href="javascript:;"><img src="/imgs/pic1.jpg" alt="1"></a>

                    <div class="banner-logincard">
                        <div class="login">
                            <div class="head-img-row"><img src="/imgs/unlogin.jpg" alt=""></div>
                        </div>

                        <p class="user-name">Hi！${sessionScope.usercode.username}</p>

                        <a class="btn-login" href="/regist">注册</a>
                        <a class="btn-login" href="/login">立即登录</a>
                    </div>
                </div>

                <div class="banner-row">
                    <a href="javascript:;">
                        <img class="pic-2" src="/imgs/pic2.png" alt="2">
                    </a>
                    <a href="javascript:;">
                        <img class="pic-3" src="/imgs/pic3.jpg" alt="3">
                    </a>
                    <a href="javascript:;">
                        <img class="pic-4" src="/imgs/pic4.jpg" alt="4">
                    </a>
                    <div class="download-app">
                        <div class="qrcode-box"><img src="/imgs/download-qr.png" alt=""></div>
                        <p class="app-name">美团APP手机版</p>
                        <p class="sl">
                            <span class="red">1元起</span>
                            <span class="gary">吃喝玩乐</span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="quality-container">
            <dl class="clear">
                <dt>有格调</dt>
                <dd>全部</dd>
                <dd>约会聚餐</dd>
                <dd>丽人spa</dd>
                <dd>电影演出</dd>
                <dd>品质出游</dd>
            </dl>

            <div class="quality-area">
                <ul class="clear">
                    <li>
                        <a href="javascript:;">
                            <img src="/imgs/index_1.jpg" alt="">
                            <div class="poi-info">
                                <div class="title">粤海喜来登酒店大堂吧</div>
                                <div class="sub-title">“芝”遇下午茶单人1位</div>
                                <div class="price-info">
                                    <span class="current-price-wrapper">
                                        <span class="price-symbol">¥</span>
                                        <span class="current-price">888</span>
                                    </span>
                                    <span class="old-price">门市价¥1182</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <img src="/imgs/index_6.jpg" alt="">
                            <div class="poi-info">
                                <div class="title">Three Hair Studio</div>
                                <div class="sub-title">Three Hair Studio</div>
                                <div class="price-info">
                                    <span class="current-price-wrapper">
                                        <span class="price-symbol">¥</span>
                                        <span class="current-price">508</span>
                                    </span>
                                    <span class="old-price">门市价¥1182</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <img src="/imgs/index_3.jpg" alt="">
                            <div class="poi-info">
                                <div class="title">万达国际影城(番禺店)</div>
                                <div class="sub-title">儿童票,real D厅,IMAX厅,4DX厅,RealD 6FL厅</div>
                                <div class="price-info">
                                    <span class="current-price-wrapper">
                                        <span class="price-symbol">¥</span>
                                        <span class="current-price">42.5</span>
                                    </span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <img src="/imgs/index_4.jpg" alt="">
                            <div class="poi-info">
                                <div class="title">粤海喜来登酒店大堂吧</div>
                                <div class="sub-title">“芝”遇下午茶单人1位</div>
                                <div class="price-info">
                                    <span class="current-price-wrapper">
                                        <span class="price-symbol">¥</span>
                                        <span class="current-price">888</span>
                                    </span>
                                    <span class="old-price">门市价¥1182</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <img src="/imgs/index_5.jpg" alt="">
                            <div class="poi-info">
                                <div class="title">强胜记海鲜大咖</div>
                                <div class="sub-title">海天盛宴，建议8-10人使用</div>
                                <div class="price-info">
                                    <span class="current-price-wrapper">
                                        <span class="price-symbol">¥</span>
                                        <span class="current-price">888</span>
                                    </span>
                                    <span class="old-price">门市价¥1182</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <img src="/imgs/index_2.jpg" alt="">
                            <div class="poi-info">
                                <div class="title">弦外重庆老灶火锅串串香（吃货之家）</div>
                                <div class="sub-title">3人套餐，提供免费WiFi</div>
                                <div class="price-info">
                                    <span class="current-price-wrapper">
                                        <span class="price-symbol">¥</span>
                                        <span class="current-price">195</span>
                                    </span>
                                    <span class="old-price">门市价¥224</span>
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>