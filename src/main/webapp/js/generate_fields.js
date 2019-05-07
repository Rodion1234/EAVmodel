var input = document.getElementById("Num");
var newEl = document.createElement('div');
var oldNum = 1;
newEl.className = "inpmain";
newEl.innerHTML = "<input type=\"text\" placeholder=\"Column Name\">  <input type=\"text\" placeholder=\"Column Value\">";
input.oninput = function () {
    var newNum = Number(input.value);
    var parentEl = document.getElementById("inp_id");
    if (newNum > 0 && newNum <= 10) {
        if (newNum < oldNum) {
            var num = oldNum - newNum;
            var lengthParEl = parentEl.children.length;
            for (var i = 1; i <= num; i++) {
                parentEl.removeChild(parentEl.children[lengthParEl - i]);
            }
        }
        if (newNum > oldNum) {
            var num = newNum - oldNum;
            for (var i = 1; i <= num; i++) {
                parentEl.appendChild(newEl.cloneNode(true));
            }
        }
        if (newNum == 0) {
            // var num = newNum - oldNum;
            parentEl.removeChild(parentEl.children[1]);
        }
        oldNum = newNum;
    }
};
