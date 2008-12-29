package org.zhulei.test.hessian.first;

import java.util.ArrayList;
import java.util.List;

public class FirstImpl implements IFirst {

    public String getString(String str) {
        return str + "_first";
    }

    public List<String> getData(String str1, String str2) {
        List<String> list = new ArrayList<String>();
        list.add(str1 + "_first");
        list.add(str2 + "_first");
        return list;
    }

}
