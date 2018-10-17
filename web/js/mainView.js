//检测页面加载
window.addEventListener('load', function () {

    // var json =
    //     {
    //         type:"startDeviceFind",
    //         content:{
    //             message:"startDeviceFind"
    //         }
    //     };
    // var strJson = JSON.stringify(json);
    // webSocket.send("strJson");


    $.ajax({
        type:'get',
        url:'http://' + window.location.host + '/Servlet?type=getDevices',
        data:{},
        cache:false,
        //dataType:'json',
        success:function(data){
            console.log(data);
            initSocket('myWebsocket');
            window.setInterval(getDevice, 5000);
        },
        error:function(err){
            console.log(err);
        }
    })
})
function receivePic(data) {
    // var app = new Vue({
    //     el: '#app',
    //     data: {
    //         message: 'Hello Vue!'
    //     }
    // })
}
function getDevice() {
    var json = {
        type:"ConnectDevices",
        content:{
            message: "ConnectDevices",
            reserved: " "
        }
    }
    var strJson = JSON.stringify(json);
    var myDate = new Date();
    console.log(myDate.toLocaleString() + "send Message:" + strJson);
    webSocket.send(strJson);
}
