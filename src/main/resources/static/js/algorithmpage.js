

var getUserPhoto = document.getElementById("input_img");
getUserPhoto.onchange = function () {
        var file = this.files;
        console.log(file);
        var reader = new FileReader();
        reader.readAsDataURL(file[0]);
        reader.onload = function () {
        var image = document.createElement("img");
        image.width = "400";
        image.src = reader.result;
        var showPicture = document.getElementById("show_img");
        showPicture.append(image);
    };
};