var stompClient = null;
 var canvas;
 var context;
function connect() {
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        
        stompClient.subscribe('/topic/newpoint', function (data) {
            var theObject=JSON.parse(data.body);
            context.beginPath();
            context.arc(theObject.x,theObject.y,1,0,2*Math.PI);
            context.stroke();
        });
        
        stompClient.subscribe('/topic/newpolygon',function (data){
            var theObject=[];
            theObject=JSON.parse(data.body);
            context.fillStyle='orange';
            context.beginPath();
            context.moveTo(theObject[0].x,theObject[0].y);
            context.lineTo(theObject[1].x,theObject[1].y);
            context.lineTo(theObject[2].x,theObject[2].y);
            context.lineTo(theObject[3].x,theObject[3].y);
            context.closePath();
            context.fill();
        });
        
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendPoint(){
    stompClient.send("/app/newpoint",{},JSON.stringify({x:$("#x").val(),y:$("#y").val()}));
}

$(document).ready(
        function () {
            console.info('connecting to websockets');
            $("#send").click(function(){sendPoint();});
            $("#connect").click(function(){connect();});
            $("#disconnect").click(function(){disconnect();});
            
            canvas=document.getElementById("canvaspage");
            context=canvas.getContext("2d");
            function getMousePos(canvas,evt){
                var recta=canvas.getBoundingClientRect();
                return{
                  x:evt.clientX-recta.left,
                  y:evt.clientY-recta.top
                };
            }
            /*Seleccionar punto por mouse en el canvas.*/
            canvas.addEventListener('mousedown',function(evt){
                var mousePos=getMousePos(canvas,evt);
                document.getElementById("x").value=mousePos.x;
                document.getElementById("y").value=mousePos.y;
                sendPoint();
                
            },false);
            
            /*Seleccionar punto por tactil en el canvas.*/
            canvas.addEventListener("touchstart",function(evt){
                evt.preventDefault();
                
                touchx=event.targetTouches[0].pageX;
                touchy=event.targetTouches[0].pageY;
                
                document.getElementById("x").value=touchx;
                document.getElementById("y").value=touchy;
                
                sendPoint();
            },false);
        }
);
