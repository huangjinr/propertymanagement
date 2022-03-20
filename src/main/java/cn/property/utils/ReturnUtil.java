package cn.property.utils;

import org.springframework.ui.ModelMap;

public class ReturnUtil {

    public static ModelMap Success(String msg, Object obj) {
        msg = msg != null || !"".equals(msg) ? msg : "操作成功";
        ModelMap mp = new ModelMap();
        mp.put("code", 0);
        mp.put("state", "success");
        mp.put("msg", msg);
        mp.put("data", obj);
        return mp;
    }

    public static ModelMap Success(String msg, Object obj, Long count) {
        msg = msg != null || !"".equals(msg) ? msg : "操作成功";
        ModelMap mp = new ModelMap();
        mp.put("code", 0);
        mp.put("state", "success");
        mp.put("count", count);
        mp.put("msg", msg);
        mp.put("data", obj);
        return mp;
    }

    public static ModelMap Success(String msg) {
        msg = msg != null || !"".equals(msg) ? msg : "操作成功";
        ModelMap mp = new ModelMap();
        mp.put("code", 200);
        mp.put("state", "success");
        mp.put("msg", msg);
        mp.put("data", null);
        return mp;
    }

    public static ModelMap Error(String msg) {
        msg = msg != null || !"".equals(msg) ? msg : "操作失败";
        ModelMap mp = new ModelMap();
        mp.put("code", 500);
        mp.put("state", "error");
        mp.put("msg", msg);
        mp.put("data", null);
        return mp;
    }
}
