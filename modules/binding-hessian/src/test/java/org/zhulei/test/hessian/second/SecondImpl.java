package org.zhulei.test.hessian.second;

import org.osoa.sca.annotations.Reference;
import org.zhulei.test.hessian.first.IFirst;

public class SecondImpl implements ISecond {

    private IFirst first;

    public String getString(String str) {
        return first.getString(str) + "_second";
    }

    @Reference
    public void setFirst(IFirst first) {
        this.first = first;
    }

}
