package com.sq.bxstore.utils;

import java.util.Comparator;

import com.sq.bxstore.net.response.CatalogsResponse.CatalogInfo;

public class CatalogComparator implements Comparator<CatalogInfo> {
	public int compare(CatalogInfo ci1, CatalogInfo ci2) {
		int order1 = ci1.getOrder();
		int order2 = ci2.getOrder();
		if (order1 > order2) {
			return 1;
		} else if (order1 < order2) {
			return -1;
		} else {
			return 0;
		}
	}
}
