const EventUtilTool = {
    //添加事件监听
    addHandler: function (element, type, handler) {
        if (element.addEventListener) {
            element.addEventListener(type, handler, false);
        } else if (element.attachEvent) {
            element.attachEvent('on' + type, handler);
        } else {
            element['on' + type] = handler;
        }
    },

    //删除事件监听
    removeHandler: function (element, type, handler) {
        if (element.removeEventListener) {
            element.removeEventListener(type, handler, false);
        } else if (element.detachEvent) {
            element.detachEvent('on' + type, handler);
        } else {
            element['on' + type] = null;
        }
    },

    //获取事件对象
    getEvent: function (event) {
        return event ? event : window.event;
    },

    //获得事件的目标
    getTarget: function (event) {
        return event.target || event.srcElement;
    },

    //阻止默认行为
    preventDefault: function (event) {
        if (event.preventDefault) {
            event.preventDefault();
        } else {
            event.returnValue = false;
        }
    },

    //阻止冒泡
    stopPropagation: function (event) {
        if (event.stopPropagation) {
            event.stopPropagation();
        } else {
            event.canaleBubble = true;
        }
    },

    //获取鼠标的detail属性
    getWgeelDelta: function (event) {
        if (event.wheelDelta) {
            return (client.engine.opera && client.engine.opera < 9.5 ? -event.wheelDelta : event.wgeelDelta);
        } else {
            return -event.detail * 40;
        }
    },

    //获取字符编码
    getCHarCode: function (event) {
        if (typeof event.charCode === 'number') {
            return event.charCode;
        } else {
            return event.keyCode;
        }
    }
};