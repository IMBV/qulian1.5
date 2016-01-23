package com.quliantrip.qulian.view.downPopupwindow;

public class FilterDataSource {

	public static final int FILTER_DATA_TYPE_LOCATION = 1;
	public static final int FILTER_DATA_TYPE_DISTANCE = 2;
	public static final int FILTER_DATA_TYPE_PRICE = 3;
	public static final int FILTER_DATA_TYPE_SORT = 4;

	public static String[] createPriceFilterItems() {
		String[] names = { "不限", "50元以下", "50-100元", "100-150元", "150-200元", "200-250元", "250-300元", "300元以上" };
		return names;
	}

	public static String[] createSortFilterItems() {
		String names[] = new String[] { "默认排序", "价格降序", "价格升序", "预订数降序", "评论数降序" };
		return names;
	}

	public static String getDataTypeName(int dataType) {
		if (FILTER_DATA_TYPE_LOCATION == dataType) {
			return "位置";
		} else if (FILTER_DATA_TYPE_DISTANCE == dataType) {
			return "距离";
		} else if (FILTER_DATA_TYPE_PRICE == dataType) {
			return "价格";
		} else if (FILTER_DATA_TYPE_SORT == dataType) {
			return "排序";
		}
		return "";
	}
}
