
function displayGraph() {
    var r = document.getElementById("radius").value;
    if (r === "_")
        return;
    r = parseFloat(r)
    displayGraphR(r)
}

function drawLastPoint(){
    const table = document.querySelector('#Table');
// получаем все строки таблицы
    const rows = table.querySelectorAll('tr');
    if (rows.length===0)
        return;
// получаем первую строку таблицы
    const firstRow = rows[1];
// получаем содержимое ячейки второй колонки первой строки
    var x = parseFloat(firstRow.cells[1].innerHTML.trim()),
        y = parseFloat(firstRow.cells[2].innerHTML.trim()),
        r = parseFloat(firstRow.cells[3].innerHTML.trim()),
        success = firstRow.cells[4].innerHTML.trim()==="true";
    drawPoint({x:x, y:y, r:r}, success ? "green" : "red")
}
function displayGraphR(r){
    var example = document.getElementById("graph"),
    ctx = example.getContext('2d');
    example.width  = 300;
    example.height = 300;
    ctx.font = "17px Arial";

    // Фигура
    ctx.fillStyle = "#5e939c";
    r_tmp = 150-20
    ctx.beginPath();
    ctx.arc(150, 150, r_tmp, Math.PI/2, Math.PI, false);
    ctx.lineTo(150, 20);
    ctx.lineTo((r_tmp/2*3)+20, 20);
    ctx.lineTo((r_tmp/2*3)+20, 150);
    ctx.lineTo(150, 150);
    ctx.closePath();
    ctx.fill();

    ctx.fillStyle = "black";

    // OX, OY
    ctx.beginPath();
    ctx.moveTo(150, 0);
    ctx.lineTo(150, 300);
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(145,10);
    ctx.lineTo(150, 0);
    ctx.lineTo(155, 10);
    ctx.stroke();
    ctx.fillText("x", 290, 143);
    ctx.beginPath();
    ctx.moveTo(0, 150);
    ctx.lineTo(300, 150);
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(300-10, 145);
    ctx.lineTo(300, 150);
    ctx.lineTo(300-10, 155);
    ctx.stroke();
    ctx.fillText("y", 155, 15);

    count = 12;
    step = (260)/(count-2);
    ctx.font = "11px Arial";


    //X parts
    let textValue;
    textValue = r*-1;
    for (i = 20; textValue.toFixed(1) <= r; i += step) {
        ctx.beginPath();
        ctx.moveTo(i, 145);
        ctx.lineTo(i, 155);
        ctx.stroke();
        ctx.fillText(textValue.toFixed(1), i + 1, 160);
        textValue += r / (count - 2) * 2;
    }

    // Y parts
    textValue = r;
    for (i=20;textValue.toFixed(1)>=-1*r;i+=step){
        ctx.beginPath();
        ctx.moveTo(145, i);
        ctx.lineTo(155, i);
        ctx.stroke();
        if (textValue.toFixed(1)!=0)
            ctx.fillText(textValue.toFixed(1), 155, i+8);
        textValue-=r/(count-2)*2;
    }


}

function drawCircle(x, y, radius, color){
    var example = document.getElementById("graph"),
        ctx     = example.getContext('2d');
    let prevColor = ctx.fillStyle;

    ctx.fillStyle = color;
    ctx.beginPath();
    ctx.arc(x,y,radius,0,Math.PI*2,true);
    ctx.closePath();
    ctx.fill();

    ctx.fillStyle = prevColor;
}

function drawPoint(coordinateDraw, color){
    displayGraph(coordinateDraw.r)
    const example = document.getElementById("graph"),
        ctx = example.getContext('2d');
    let x = 150 + coordinateDraw.x/coordinateDraw.r*(150-20);
    let y = 150 + -1*coordinateDraw.y/coordinateDraw.r*(150-20);
    drawCircle(
        x,
        y,
        4,
        color
    );
}

window.onload = function(){
    displayGraph();
    // drawLastPoint();
    var canvas = document.getElementById("graph");

    // Добавьте обработчик события "click"
    canvas.addEventListener("click", function(e) {
        // Получите координаты нажатия
        var x = (e.offsetX-150)/(150-20);
        var y = -1*(e.offsetY-150)/(150-20);
        // Отправьте координаты в ваш JSF-бин через AJAX-запрос
        setCurrentCoordinate([
            {name:'x', value:x},
            {name:'y', value:y}
        ]);
    });
    }
