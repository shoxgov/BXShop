package com.sq.bxstore.base;

import java.util.ArrayList;
import java.util.List;

import com.sq.bxstore.bean.AdvertsBean;
import com.sq.bxstore.net.response.CatalogsResponse.CatalogInfo;

/**
 * 将收到的数据按要求转换成不同的样式定义类
 */
public class ActivitiesListItemStyleManager {

	public List<ActivitiesListItemStyleViewBase> getListItemView(
			List<CatalogInfo> list, List<AdvertsBean> adlist) {
		int i = 0;
		List<ActivitiesListItemStyleViewBase> data = new ArrayList<ActivitiesListItemStyleViewBase>();
		for (CatalogInfo ci : list) {
			ActivitiesListItemStyleViewBase asb;
			List<AdvertsBean> ads = new ArrayList<AdvertsBean>();
			for (AdvertsBean ab : adlist) {
				if ((ab.getKind() + "").equals(ci.getCode())) {
					ads.add(ab);
//					adlist.remove(ab);
				}
			}
			if (i % 2 == 0 || ads.size() < 10) {// 小于10个就用9个显示排列的
				asb = new ActivitiesListItemColumStyleView(ci.getName(), ads);//3*3
			} else {
				asb = new ActivitiesListItemDefaultStyleView(ci.getName(), ads);//3*2+4
			}
			i++;
			data.add(asb);
		}
		return data;
	}
}
