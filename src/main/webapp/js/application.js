/**
 * moveScreen
 * [移动屏幕]
 * @return {[type]} [description]
 */
let finished = false;//判断是否完成切换页面
let moveScreen = (obj,obj1) => {
    if(!finished) {


        let eleObj = document.getElementById(obj);
        let eleObj2 = document.getElementById(obj1);

        changePoint(obj, obj1);

        eleObj.style.animation = 'end 0.5s linear 0s 1';
        setTimeout(function() {
            eleObj.style.display = "none";
            eleObj2.style.display = "block";
            eleObj2.style.animation = 'start 0.5s linear 0s 1';
            finished = false;
            
        }, 480);
    }
    
}
let changePoint = (nowobj, chaobj) => {
    if(!finished) {
        finished = true;
        document.getElementById(nowobj+'6').className = 'point';
        document.getElementById(chaobj+'6').className = 'point active-show';
        document.getElementById(nowobj+'2').className = 'hide';
        document.getElementById(chaobj+'2').className = 'showInline';
    }
    
}

$(function() {

    const SCREENHEIGHT = document.documentElement.clientHeight;
    // alert(SCREENHEIGHT);
    let bsExampleNavbarCollapse = document.getElementById("showINPc"),  //获取电脑版导航栏
        bsExampleNavbarCollapseAs = bsExampleNavbarCollapse.getElementsByTagName("a"),      //获取导航栏的a元素
        bodyNow = "main-index";                                                             //显示的页面
                                                                        
        // let index = $(bsExampleNavbarCollapseAs[1]).attr("index");
        // moveScreen(document.getElementById(bodyNow), document.getElementById(index));
    for(let i = 0,len = bsExampleNavbarCollapseAs.length; i < len; i++) {
        //为导航栏赋值操作
        EventUtilTool.addHandler(bsExampleNavbarCollapseAs[i], 'click', function(e) {
            EventUtilTool.stopPropagation(e);
            EventUtilTool.preventDefault(e);
            let index = $(bsExampleNavbarCollapseAs[i]).attr("index");
            if(index != bodyNow) {
                moveScreen(bodyNow, index);
                bodyNow = index;
            }
           
        })
    }


    //获取手机版导航栏
    bsExampleNavbarCollapsePhone = document.getElementById("showINPhone");  //获取电脑版导航栏
    bsExampleNavbarCollapseAsPhone = bsExampleNavbarCollapsePhone.getElementsByTagName("a");      //获取导航栏的a元素
    for(let i = 0,len = bsExampleNavbarCollapseAsPhone.length; i < len; i++) {
        //为导航栏赋值操作
        EventUtilTool.addHandler(bsExampleNavbarCollapseAsPhone[i], 'click', function(e) {
            EventUtilTool.stopPropagation(e);
            EventUtilTool.preventDefault(e);
            let index = $(bsExampleNavbarCollapseAsPhone[i]).attr("index");
            if(index != bodyNow) {
                moveScreen(bodyNow, index);
                bodyNow = index;
            }
            // $("#bs-example-navbar-collapse-1").fadeOut(400);

            // setTimeout(function() {
            //     document.getElementById("bs-example-navbar-collapse-1").style.display='none';
            // }, 400);
            $("#showPhoneList").click();
        })
    }

    $("#showPhoneList").bind('click',  function(event) {
        /* Act on the event */
        if(document.getElementById("bs-example-navbar-collapse-1").style.display=='none') {
            $("#bs-example-navbar-collapse-1").fadeIn(400);
        }

    });
    $("#backToShow").bind('click',  function(event) {
        /* Act on the event */
        $("#showPhoneList").click();

    });

    //右边的点事件监听
    let sideList = document.getElementById("sideList");
        sideListPoint = sideList.getElementsByTagName("span");

    for(let i = 1,len = sideListPoint.length; i < len; i+=2) {
        //为导航栏赋值操作
        EventUtilTool.addHandler(sideListPoint[i], 'click', function(e) {
            EventUtilTool.stopPropagation(e);
            EventUtilTool.preventDefault(e);
            let index = $(sideListPoint[i]).attr("index");
            if(index != bodyNow) {
                moveScreen(bodyNow, index);
                bodyNow = index;
            }
            // $("#bs-example-navbar-collapse-1").fadeOut(400);

            // setTimeout(function() {
            //     document.getElementById("bs-example-navbar-collapse-1").style.display='none';
            // }, 400);
            // $("#showPhoneList").click();
        })
    }

    let nextPage = document.getElementById("nextPage"),
        nextPageAs = nextPage.getElementsByTagName("a");
    for(let i = 0,len = nextPageAs.length; i < len; i++) {
        //为导航栏赋值操作
        EventUtilTool.addHandler(nextPageAs[i], 'click', function(e) {
            EventUtilTool.stopPropagation(e);
            EventUtilTool.preventDefault(e);
            // let index = $(sideListPoint[i]).attr("index");
           
                moveScreen("main-index", "network");
                bodyNow = "network";
            
            // $("#bs-example-navbar-collapse-1").fadeOut(400);

            // setTimeout(function() {
            //     document.getElementById("bs-example-navbar-collapse-1").style.display='none';
            // }, 400);
            // $("#showPhoneList").click();
        })
    }
   

    
    EventUtilTool.addHandler(window, "mousewheel", function(e) {
            e=e || window.event; 
            if(finished) {
                return;
            }
                // moveScreen(bodyNow, index);
                // bodyNow = index;
            if(e.wheelDelta){//IE/Opera/Chrome 
                if(e.wheelDelta > 0) {//向上滚动
                    switch(bodyNow) {
                        case  "main-index":moveScreen("main-index", "designer");bodyNow = "designer";break;
                        case  "network":moveScreen("network", "main-index");bodyNow = "main-index";break;
                        case  "android":moveScreen("android", "network");bodyNow = "network";break;
                        case  "handgame":moveScreen("handgame", "android");bodyNow = "android";break;
                        case  "embedded":moveScreen("embedded", "handgame");bodyNow = "handgame";break;
                        case  "data":moveScreen("data", "embedded");bodyNow = "embedded";break;
                        case  "designer":moveScreen("designer", "data");bodyNow = "data";break; 
                         
                    }
                } else if (e.wheelDelta < 0) {//向下滚动
                    switch(bodyNow) {
                        case  "main-index":moveScreen("main-index", "network");bodyNow = "network";break;
                        case  "network":moveScreen("network", "android");bodyNow = "android";break;
                        case  "android":moveScreen("android", "handgame");bodyNow = "handgame";break;
                        case  "handgame":moveScreen("handgame", "embedded");bodyNow = "embedded";break;
                        case  "embedded":moveScreen("embedded", "data");bodyNow = "data";break;
                        case  "data":moveScreen("data", "designer");bodyNow = "designer";break;
                        case  "designer":moveScreen("designer", "main-index");bodyNow = "main-index";break; 

                    }
                }
            }else if(e.detail){//Firefox 
                if(e.detail > 0) {//向下滚动
                    switch(bodyNow) {
                        case  "main-index":moveScreen("main-index", "network");bodyNow = "network";break;
                        case  "network":moveScreen("network", "android");bodyNow = "android";break;
                        case  "android":moveScreen("android", "handgame");bodyNow = "handgame";break;
                        case  "handgame":moveScreen("handgame", "embedded");bodyNow = "embedded";break;
                        case  "embedded":moveScreen("embedded", "data");bodyNow = "data";break;
                        case  "data":moveScreen("data", "designer");bodyNow = "designer";break;
                        case  "designer":moveScreen("designer", "main-index");bodyNow = "main-index";break; 

                    }
                } else if (e.detail < 0) {//向上滚动
                    switch(bodyNow) {
                        case  "main-index":moveScreen("main-index", "designer");bodyNow = "designer";break;
                        case  "network":moveScreen("network", "main-index");bodyNow = "main-index";break;
                        case  "android":moveScreen("android", "network");bodyNow = "network";break;
                        case  "handgame":moveScreen("handgame", "android");bodyNow = "android";break;
                        case  "embedded":moveScreen("embedded", "handgame");bodyNow = "handgame";break;
                        case  "data":moveScreen("data", "embedded");bodyNow = "embedded";break;
                        case  "designer":moveScreen("designer", "data");bodyNow = "data";break; 
  
                    }
                } 
            } 
            
    });
    
    function touchSatrt(e){//触摸   
        // e.preventDefault();   
        // var touch=e.touches[0];   
        // startY = touch.pageY;   //刚触摸时的坐标 
        // $("#showPhoneList").click(); 
        // let bsExampleNavbarCollapse = document.getElementById('bs-example-navbar-collapse-1');
        // if(bsExampleNavbarCollapse.style.display == 'block') {
        //     $("#showPhoneList").click();
        // }       
            
        try {
            //e.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等
             
            var touch = e.touches[0]; //获取第一个触点
            var x = Number(touch.pageX); //页面触点X坐标
            var y = Number(touch.pageY); //页面触点Y坐标
            //记录触点初始位置
            startX = x;
            startY = y;

            var text = 'TouchStart事件触发：（' + x + ', ' + y + '）';
            console.log(text);
        }
        catch (e) {
            alert('touchSatrtFunc：' + e.message);
        }  
    }
    function touchMove(e){//滑动             
        // e.preventDefault();           
        // var  touch = e.touches[0];                  
        // y = touch.pageY - startY;//滑动的距离   
                //inner.style.webkitTransform = 'translate(' + 0+ 'px, ' + y + 'px)';  //也可以用css3的方式        
        
        try {
            //evt.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等
            var touch = e.touches[0]; //获取第一个触点
            var x = Number(touch.pageX); //页面触点X坐标
            var y = Number(touch.pageY) - startY; //页面触点Y坐标
            var text = 'TouchMove事件触发：（' + x + ', ' + y + '）';
             
            //判断滑动方向
            if (x - startX != 0) {
                text += '<br/>左右滑动';
            }
            if (y - startY != 0) {
                text += '<br/>上下滑动';
            }
             
            console.log(text);
        }
        catch (e) {
            alert('touchMoveFunc：' + e.message);
        }                     
    }
    function touchEnd(e){//手指离开屏幕   

        // e.preventDefault();                      


        try {
            //evt.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等
            nChangX = e.changedTouches[0].pageX;
            nChangY = e.changedTouches[0].pageY;
            if(nChangY - startY > 0){
                // console.log("up");
                    switch(bodyNow) {
                        case  "main-index":moveScreen("main-index", "designer");bodyNow = "designer";break;
                        case  "network":moveScreen("network", "main-index");bodyNow = "main-index";break;
                        case  "android":moveScreen("android", "network");bodyNow = "network";break;
                        case  "handgame":moveScreen("handgame", "android");bodyNow = "android";break;
                        case  "embedded":moveScreen("embedded", "handgame");bodyNow = "handgame";break;
                        case  "data":moveScreen("data", "embedded");bodyNow = "embedded";break;
                        case  "designer":moveScreen("designer", "data");bodyNow = "data";break;
                        // case  "table":moveScreen("table", "designer");bodyNow = "designer";break;
                    }
            }else if(nChangY - startY < 0){
                // console.log("d");
                switch(bodyNow) {
                        case  "main-index":moveScreen("main-index", "network");bodyNow = "network";break;
                        case  "network":moveScreen("network", "android");bodyNow = "android";break;
                        case  "android":moveScreen("android", "handgame");bodyNow = "handgame";break;
                        case  "handgame":moveScreen("handgame", "embedded");bodyNow = "embedded";break;
                        case  "embedded":moveScreen("embedded", "data");bodyNow = "data";break;
                        case  "data":moveScreen("data", "designer");bodyNow = "designer";break;
                        case  "designer":moveScreen("designer", "main-index");bodyNow = "main-index";break;
                        // case  "table":moveScreen("table", "main-index");bodyNow = "main-index";break;
                }
            }
        }
        catch (e) {
            alert('touchEndFunc：' + e.message);
        }
    }
    // window.addEventListener('touchstart', touchSatrt,false);     
    // window.addEventListener('touchmove', touchMove,false);     
    // window.addEventListener('touchend', touchEnd,false);
     let sections = document.getElementsByTagName("section");
     for(i=0, len=sections.length; i < len; i++) {
        sections[i].addEventListener('touchstart', touchSatrt,false);    
        // sections[i].addEventListener('touchmove', touchMove,false);
        sections[i].addEventListener('touchend', touchEnd,false);
     }  

    let logo = document.getElementById("logo");
    EventUtilTool.addHandler(logo, "touchend", function(e) {
          
        switch(bodyNow) {
                        // case  "main-index":moveScreen("main-index", "designer");bodyNow = "designer";break;
                        case  "network":moveScreen("network", "main-index");bodyNow = "main-index";break;
                        case  "android":moveScreen("android", "main-index");bodyNow = "main-index";break;
                        case  "handgame":moveScreen("handgame", "main-index");bodyNow = "main-index";break;
                        case  "embedded":moveScreen("embedded", "main-index");bodyNow = "main-index";break;
                        case  "data":moveScreen("data", "main-index");bodyNow = "main-index";break;
                        case  "designer":moveScreen("designer", "main-index");bodyNow = "main-index";break; 
                        // case  "table":moveScreen("table", "designer");bodyNow = "designer";break; 
        }
            
    });

    let enrollTable2 = document.getElementById("enrollTable");
    if (enrollTable2.style.display == "none") {

    }
})


var startX,//触摸时的坐标
    startY,
    x, //滑动的距离
    y;

    