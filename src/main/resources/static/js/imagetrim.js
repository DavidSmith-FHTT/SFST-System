// 获取输入框和按钮元素
var inputImage = document.getElementById('inputImage');
var cropperImage = document.getElementById('cropperImage');
var previewCanvas = document.getElementById('previewCanvas');
var cropButton = document.getElementById('cropButton');
var downloadButton = document.getElementById('downloadButton');

// 初始化 Cropper.js
var cropper = null;
inputImage.addEventListener('change', function () {
    var file = this.files[0];
    var reader = new FileReader();
    reader.onload = function (e) {
        cropperImage.src = e.target.result;
        cropperImage.onload = function () {
            cropper = new Cropper(cropperImage, {
                aspectRatio: 1,
                viewMode: 1,
                cropBoxResizable: true,
                cropBoxMovable: true,
                crop: function (event) {
                    previewCanvas.width = event.detail.width*0.5;
                    previewCanvas.height = event.detail.height*0.5;
                    var ctx = previewCanvas.getContext('2d');
                    ctx.clearRect(0, 0, previewCanvas.width, previewCanvas.height);
                    ctx.drawImage(cropperImage, event.detail.x, event.detail.y, event.detail.width, event.detail.height, 0, 0, previewCanvas.width, previewCanvas.height);
                }
            });
        };
    };
    reader.readAsDataURL(file);
});

// 绑定裁剪按钮点击事件
cropButton.addEventListener('click', function () {
    var canvas = cropper.getCroppedCanvas();
    var blob = canvas.toBlob(function (blob) {
        var url = URL.createObjectURL(blob);
        downloadButton.href = url;
        var previewUrl = canvas.toDataURL();
        previewCanvas.getContext('2d').clearRect(0, 0, previewCanvas.width, previewCanvas.height);
        var previewImage = new Image();
        previewImage.onload = function () {
            previewCanvas.getContext('2d').drawImage(previewImage, 0, 0, previewCanvas.width, previewCanvas.height);
        }
        previewImage.src = previewUrl;
    });
});