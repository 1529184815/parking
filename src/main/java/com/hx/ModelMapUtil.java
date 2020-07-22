package com.hx;

import org.springframework.ui.ModelMap;

public class ModelMapUtil {
    public static ModelMap getModelMap(String name, Object obj){
        ModelMap modelMap = new ModelMap();
        if(obj!=null){
            modelMap.put("ok",true);
            modelMap.put("msg",name+"成功");
        }else{
            modelMap.put("ok",false);
            modelMap.put("msg",name+"失败");
        }
        return modelMap;
    }
}
