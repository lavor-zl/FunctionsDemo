package com.lavor.functionsdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

public class MainActivity extends AppCompatActivity {
    //训练数据路径，必须包含tesseract文件夹
    static final String TESSBASE_PATH = "/storage/emulated/0/Download/tesseract/";
    //识别语言英文
    static final String DEFAULT_LANGUAGE = "eng";
    //识别语言简体中文
    static final String CHINESE_LANGUAGE = "chi_sim";
    private android.widget.ImageView english;
    private android.widget.TextView englishtext;
    private android.widget.ImageView simplechinese;
    private android.widget.TextView simplechinesetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.simplechinesetext = (TextView) findViewById(R.id.simple_chinese_text);
        this.simplechinese = (ImageView) findViewById(R.id.simple_chinese);
        this.englishtext = (TextView) findViewById(R.id.english_text);
        this.english = (ImageView) findViewById(R.id.english);
    }
    public void ocr(View view){
        //英文识别
        EnglishOCR();
        //简体中文识别
        SimpleChineseOCR();
    }
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
    public void SimpleChineseOCR(){
        //设置图片可以缓存
        simplechinese.setDrawingCacheEnabled(true);
        //获取缓存的bitmap
        final Bitmap bmp = simplechinese.getDrawingCache();
        final TessBaseAPI baseApi = new TessBaseAPI();
        //初始化OCR的训练数据路径与语言
        baseApi.init(TESSBASE_PATH, CHINESE_LANGUAGE);
        //设置识别模式
        baseApi.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_LINE);
        //设置要识别的图片
        baseApi.setImage(bmp);
        simplechinese.setImageBitmap(bmp);
        simplechinesetext.setText(baseApi.getUTF8Text());
        baseApi.clear();
        baseApi.end();
    }
}
