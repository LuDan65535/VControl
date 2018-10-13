

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
            username:strUserName,
            password:strPasswd
        }
    };
    var loginJsonString=JSON.stringify(loginJson);
    webSocket.send(loginJsonString);

}
