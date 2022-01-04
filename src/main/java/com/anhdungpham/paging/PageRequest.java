package com.anhdungpham.paging;

public class PageRequest implements Pageble {
	
	private final Integer page;
	private final Integer maxPageItem;
	private final Sorter sorter;
	
	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffSet() {
		if (this.page != null && this.maxPageItem != null) {
			return (this.page - 1) * this.maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return this.maxPageItem;
	}

	@Override
	public Sorter getSorter() {
		if (this.sorter != null) {
			return this.sorter;
		}
		return null;
	}

}
