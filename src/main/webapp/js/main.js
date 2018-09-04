window.onload = function () {
    mt.app.toBanner();
};

var mt = {};
mt.tools = {};
mt.ui = {};
mt.app = {};

mt.tools.getByClass = function (oParent, sClass) {
    var aEle = oParent.getElementsByTagName('*');
    var arr = [];

    for (var i = 0; i < aEle.length; i++) {
        if (aEle[i].className == sClass) {
            arr.push(aEle[i]);
        }
    }

    return arr;
};

mt.tools.getStyle = function (obj, attr) {
    if (obj.currentStyle) {
        return obj.currentStyle[attr];
    } else {
        return getComputedStyle(obj, false)[attr];
    }
};

mt.ui.fadeIn = function (obj) {
    var iCur = mt.tools.getStyle(obj, 'opacity');
    if (iCur == 1) {
        return false;
    }

    var value = 0;
    clearInterval(obj.timer);
    obj.timer = setInterval(function () {
        var iSpeed = 5;
        if (value == 100) {
            clearInterval(obj.timer);
        } else {
            value += iSpeed;
            obj.style.opacity = value / 100;
            obj.style.filter = 'alpha(opacity=' + value + ')';
        }
    }, 30);
};

mt.ui.fadeOut = function (obj) {
    var iCur = mt.tools.getStyle(obj, 'opacity');
    if (iCur == 0) {
        return false;
    }

    var value = 100;
    clearInterval(obj.timer);
    obj.timer = setInterval(function () {
        var iSpeed = -5;
        if (value == 0) {
            clearInterval(obj.timer);
        } else {
            value += iSpeed;
            obj.style.opacity = value / 100;
            obj.style.filter = 'alpha(opacity=' + value + ')';
        }
    }, 30);
};

mt.app.toBanner = function () {
    var oImgsUl = document.getElementById('banner-slider-img');
    var aLi = oImgsUl.getElementsByTagName('li');

    var iNow = 0;

    var timer = setInterval(auto, 3000);

    function auto() {
        iNow++;
        iNow %= aLi.length;
        // if (iNow == aLi.length - 1) {
        //     iNow = 0;
        // } else {
        //     iNow++;
        // }

        for (var i = 0; i < aLi.length; i++) {
            mt.ui.fadeOut(aLi[i]);
        }

        mt.ui.fadeIn(aLi[iNow]);
    }
};