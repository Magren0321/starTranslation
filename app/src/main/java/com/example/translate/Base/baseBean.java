package com.example.translate.Base;

import java.util.ArrayList;
import java.util.List;

public class baseBean {


    public List<bean> sentences;
    public String src;
    public double confidence;
    public ld_result ld_result;




    public static class bean{
        String trans;
        String orig;
        int backend;

        public String getTrans() {
            return trans;
        }

    }

    public class ld_result{
        List<String> srclangs;
        List<Integer>srclangs_confidences;
        List<String>extended_srclangs;
    }



    public void show(){
        System.out.println(sentences);
        System.out.println(src);
        System.out.println(confidence);
        System.out.println(ld_result.extended_srclangs);
    }

}
