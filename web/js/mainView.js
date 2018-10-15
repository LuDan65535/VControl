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
