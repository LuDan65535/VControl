//检测页面加载
window.addEventListener('load', function () {

    var json =
        {
            type:"startDeviceFind",
            content:{
                message:"startDeviceFind"
            }
        };
    var strJson = JSON.stringify(json);
    webSocket.send("strJson");
})
function receivePic(data) {
    // var app = new Vue({
    //     el: '#app',
    //     data: {
    //         message: 'Hello Vue!'
    //     }
    // })
}
