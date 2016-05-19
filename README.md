#Android OCR之tesseract
***  
#1. 开源工具简介
***  
[tesseract](https://github.com/tesseract-ocr/tesseract)是非常有名的开源OCR工具，但是要将它配置到Android开发环境中可能要费点功夫，别担心，github上面有好人帮助我们封装了Android开发环境的tesseract配置，这就是Android平台上OCR开源项目[tess-two](https://github.com/rmtheis/tess-two)。  

#2. 环境配置  
***  
利用tess-two配置OCR环境非常简单。  
- 首先，设置工程的ndk路径。  
- 其次，在模块中添加依赖：`compile 'com.rmtheis:tess-two:6.0.0'`。  

#3. 数据准备  
***  
要进行OCR还要获取训练数据[tessdata](https://github.com/tesseract-ocr/tessdata)，下载该训练数据，到时候将训练数据弄到自己的Android设备上就行了。  

**注意：不要把所有训练数据都弄到Android设备上，因为训练数据比较大，需要用到什么语言的数据就只把这些语言对应的数据弄到Android设备上。**  

比如，我一般识别只用到识别英文与简体中文，因此只需要将eng.traineddata，chi_sim.traineddata弄到Android设备上就行了。  

#4. 开始Android OCR之旅  
***  
相关常量设置：  
```  
    //训练数据路径，必须包含tesseract文件夹
    static final String TESSBASE_PATH = "/storage/emulated/0/Download/tesseract/";
    //识别语言英文
    static final String DEFAULT_LANGUAGE = "eng";
    //识别语言简体中文
    static final String CHINESE_LANGUAGE = "chi_sim";  
```  

英文识别：  
```  
     public void EnglishOCR(){
        //设置图片可以缓存
        english.setDrawingCacheEnabled(true);
        //获取缓存的bitmap
        final Bitmap bmp = english.getDrawingCache();
        final TessBaseAPI baseApi = new TessBaseAPI();
        //初始化OCR的训练数据路径与语言
        baseApi.init(TESSBASE_PATH, DEFAULT_LANGUAGE);
        //设置识别模式
        baseApi.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_LINE);
        //设置要识别的图片
        baseApi.setImage(bmp);
        english.setImageBitmap(bmp);
        englishtext.setText(baseApi.getUTF8Text());
        baseApi.clear();
        baseApi.end();
     }  
```  

简体中文识别与英文识别类似。  

#5. 程序界面  
***  
![](http://i.imgur.com/hOsu7LN.png)  

![](http://i.imgur.com/iW0R6br.png)  

程序源代码下载：[https://github.com/lavor-zl/FunctionsDemo](https://github.com/lavor-zl/FunctionsDemo)  

**欢迎关注我的微信公众号：Android技术漫谈**  
![](http://i.imgur.com/u75x3BP.jpg) 

