package org.itheima15.googleplay.protocol;

import org.itheima15.googleplay.base.BaseProtocol;

import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.protocol
 *  @文件名:   PageProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/24 14:08
 *  @描述：    TODO
 */
public abstract class PageProtocol<T>
        extends BaseProtocol<T>
{

    //具备翻页的功能: index=0
    private Map<String, String> mParameters;

    @Override
    protected Map<String, String> getParameters() {
        return mParameters;
    }

    public T loadPage(int index)
            throws Exception
    {
        if (mParameters == null) {
            mParameters = new HashMap<>();
        }

        mParameters.put("index", String.valueOf(index));

        return loadData();
    }
}
