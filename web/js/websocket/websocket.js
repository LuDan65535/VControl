var webSocket = null;
function initSocket(myWebsocket) {
    window.onbeforeunload = function () {
        //离开页面时的其他操作
    };

    if (!window.WebSocket) {
        console("您的浏览器不支持websocket！");
        return false;
    }

    var target = 'ws://' + window.location.host + "/webSocketByTomcat/"+myWebsocket;

    if('WebSocket' in window) {
        webSocket = new WebSocket(target);
    } else if('MozWebSocket' in window) {
        webSocket = new MozWebSocket(target);
    } else {
        webSocket = new SockJS(target);
    }
    // 收到服务端消息
    webSocket.onmessage = function (msg) {
        var jsonMessage = eval('(' + msg.data + ')');
        if(jsonMessage.type == "login"){
            if(jsonMessage.content.code == "0"){

               // window.self.location = "/main";window.location.host
                $.ajax({
                    type:'get',
                    url:'http://' + window.location.host + '/Servlet?type=getDevice',
                    data:{},
                    cache:false,
                    //dataType:'json',
                    success:function(data){
                        console.log(data);
                    },
                    error:function(err){
                        console.log(err);
                    }
                });



            }else if (jsonMessage.content.code == "1"){
                //提示密码错误
            } else if (jsonMessage.content.code == "2"){
                //提示账号不存在
            }
        }
        // 关闭连接
        //webSocket.onclose();
        console.log(msg);
    };

    // 异常
    webSocket.onerror = function (event) {
        console.log(event);
    };

    // 建立连接
    webSocket.onopen = function (event) {
        console.log(event);
    };

    // 断线
    webSocket.onclose = function () {

        console.log("websocket断开连接");
    };
}