// window.addEventListener('load', function () {
//     var app = new Vue({
//         el: '#app',
//         data: {
//             message: 'Hello Vue!'
//         }
//     })
// })
function initSocket(myWebsocket) {

    var webSocket = null;

    window.onbeforeunload = function () {
        //离开页面时的其他操作
    };

    if (!window.WebSocket) {
        console("您的浏览器不支持websocket！");
        return false;
    }

    var target = 'ws://' + window.location.host + "/activemq-client/websocket/"+myWebsocket;

    if ('WebSocket' in window) {
        webSocket = new WebSocket(target);
    } else if ('MozWebSocket' in window) {
        webSocket = new MozWebSocket(target);
    } else {
        alert('WebSocket is not supported by this browser.');
        return;
    }


    // 收到服务端消息
    webSocket.onmessage = function (msg) {
        alert(msg.data);
        // 关闭连接
        webSocket.onclose();
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