package org.itheima15.googleplay.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.itheima15.googleplay.base.BaseProtocol;
import org.itheima15.googleplay.bean.CategoryBean;
import org.itheima15.googleplay.vo.CategoryVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.protocol
 *  @文件名:   CategoryProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 8:32
 *  @描述：    TODO
 */
public class CategoryProtocol
        extends BaseProtocol<List<CategoryBean>>
{
    @Override
    protected String getInterfacePath() {
        return "/category";
    }

    @Override
    protected Map<String, String> getParameters() {
        return null;
    }

    @Override
    protected List<CategoryBean> parseJson(String json) {
        List<CategoryBean> datas = new ArrayList<>();


        Gson             gson = new Gson();
        List<CategoryVo> vos  = gson.fromJson(json, new TypeToken<List<CategoryVo>>() {}.getType());

        //有服务器的bean转换为客户端的bean
        //        List<CategoryVo> ----》 List<CategoryBean>

        for (int i = 0; i < vos.size(); i++) {
            //服务器的bean
            CategoryVo vo = vos.get(i);


            //title类型的bean
            String title = vo.title;

            CategoryBean titleBean = new CategoryBean();
            titleBean.title = title;
            titleBean.type = CategoryBean.TYPE_TITLE;
            datas.add(titleBean);


            List<CategoryVo.CategoryInfo> infos = vo.infos;
            for (int j = 0; j < infos.size(); j++) {
                CategoryVo.CategoryInfo info = infos.get(j);

                CategoryBean itemBean = new CategoryBean();
                itemBean.type = CategoryBean.TYPE_ITEM;

                itemBean.name1 = info.name1;
                itemBean.name2 = info.name2;
                itemBean.name3 = info.name3;

                itemBean.url1 = info.url1;
                itemBean.url2 = info.url2;
                itemBean.url3 = info.url3;

                datas.add(itemBean);
            }
        }
        return datas;
    }
}
