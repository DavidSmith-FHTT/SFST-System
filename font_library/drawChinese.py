# -*- coding: UTF-8 -*-
import sys
from PIL import Image, ImageFont, ImageDraw

def CreateImg(text, style, imagePath):
    fontSize = 128
    # 画布颜色
    im = Image.new("RGB", (128, 128), (255, 255, 255))
    dr = ImageDraw.Draw(im)

    # 字体样式
    if(style == 1):
        fontPath = "./font_library/方正榜书楷体/FZBangSKJF.TTF"
    elif style == 2:
        fontPath = "./font_library/方正柳公权楷书 简繁/FZLiuGQKSJF.TTF"
    elif style == 3:
        fontPath = "./font_library/方正魏碑/FZWBK.TTF"
    elif style == 4:
        fontPath = "./font_library/方正文征明小楷/FZWenZMXKJW.TTF"
    elif style == 5:
        fontPath = "./font_library/方正艺楷 简繁/FZYiKJF.TTF"
    elif style == 6:
        fontPath = "./font_library/方正硬笔楷书/FZYBKSK.TTF"
    elif style == 7:
        fontPath = "./font_library/方正字汇-青华行楷 简繁/FZZH-QHXKJF.TTF"
    else:
        fontPath = "./font_library/方正榜书楷体/FZBangSKJF.TTF"
    font = ImageFont.truetype(fontPath, fontSize)

    # 文字颜色
    dr.text((0, 0), text, font=font, fill="#000000")
    #     im.save('./src/main/resources/static/images/{:0>4}.jpg'.format(0))
    #     im.save('D:/{:0>4}.jpg'.format(0))
    im.save(imagePath)


if __name__ == "__main__":
    text = sys.argv[1]
    style = (int(sys.argv[2]))
    imagePath = sys.argv[3]
    CreateImg(text, style, imagePath)
    print("\nfinish!!!")