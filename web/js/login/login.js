var webSocket = null;

//添加页面加载监听事件
window.addEventListener('load', function () {
    // var app = new Vue({
    //     el: '#app',
    //     data: {
    //         message: 'Hello Vue!'
    //     }
    // })
})

function login() {

    var userName = document.getElementById("userName");
    var strUserName = userName.value;
    var passwd = document.getElementById("passwd");
    var strPasswd = passwd.value;
    var loginJson =
    {
        type:"login",
            content:{
            userName:strUserName,
            password:strPasswd
        }
    };
    var loginJsonString=JSON.stringify(loginJson);
    webSocket.send(loginJsonString);
    // window.self.location = "/main";
}
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

    // if ('WebSocket' in window) {
    //     webSocket = new WebSocket(target);
    // } else if ('MozWebSocket' in window) {
    //     webSocket = new MozWebSocket(target);
    // } else {
    //     alert('WebSocket is not supported by this browser.');
    //     return;
    // }


    // 收到服务端消息
    webSocket.onmessage = function (msg) {
        alert(msg.data);
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