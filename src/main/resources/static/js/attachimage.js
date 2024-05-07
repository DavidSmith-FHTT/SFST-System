function concat() {
    var files = document.getElementById("file-input").files;
    if (files.length < 2) {
        alert("Please select at least two images to concatenate.");
        return;
    }
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var images = [];
    var loaded = 0;
    for (var i = 0; i < files.length; i++) {
        var img = new Image();
        img.onload = function() {
            loaded++;
            if (loaded == files.length) {
                var width = 300 * files.length;
                var height = 300;
                canvas.width = width;
                canvas.height = height;
                var x = 0;
                for (var j = 0; j < images.length; j++) {
                    ctx.drawImage(images[j], x, 0, 300, 300);
                    x += 300;
                }
            }
        };
        img.src = URL.createObjectURL(files[i]);
        images.push(img);
    }
}

function download() {
    var link = document.createElement('a');
    link.download = 'concatenated-image.png';
    link.href = document.getElementById('canvas').toDataURL();
    link.click();
}
