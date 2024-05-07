var nas = document.querySelectorAll('.nav-box');
var boxs1 = document.querySelectorAll('.box');

// for()
// boxs1[1].addEventListener("click",function (){
//     window.location.href = '/dataset';
// })
// boxs1[2].addEventListener("click",function (){
//     window.location.href = '/tool';
// })
//bug 点 queryselectorall 返回的是一个数组 所以需要通过下标来访问
boxs1[0].addEventListener('mouseover',function (){
    // 遍历所有.item元素，移出active样式

    nas[0].classList.remove('active1','active2' ,'active3' ,'active4');

    // 为当前选中项添加active样式
    nas[0].classList.add('active1');
});
boxs1[1].addEventListener('mouseover',function (){
    // 遍历所有.item元素，移出active样式

    nas[0].classList.remove('active1','active2' ,'active3' ,'active4');

    // 为当前选中项添加active样式
    nas[0].classList.add('active2');
});
boxs1[2].addEventListener('mouseover',function (){
    // 遍历所有.item元素，移出active样式

    nas[0].classList.remove('active1','active2' ,'active3' ,'active4');

    // 为当前选中项添加active样式
    nas[0].classList.add('active3');
});
boxs1[3].addEventListener('mouseover',function (){
    // 遍历所有.item元素，移出active样式

    nas[0].classList.remove('active1','active2' ,'active3' ,'active4');

    // 为当前选中项添加active样式
    nas[0].classList.add('active4');

});


