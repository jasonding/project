package com.project.domain;

import java.util.List;

/**
 * 分页实体bean
 * @author djs
 * @param <T>
 */
public class PageView<T> {
	// 需要指定的
	private int pageSize; // 需要一页显示多少条数据
	private int currentPage; //当前显示的页码

	// 数据库中查询的
	private int recordCount; //数据库总记录数
	private List<T> recordList; // 一页显示的数据
	
	// 计算出来的
	private int totalPage; // 一共多少页码
	private int startPageIndex; // 显示 -->开始页码
	private int endPageIndex; // 显示 -->结束页码

	/**
	 * 计算指定页码中的第一条数据的索引
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static int calcFirstResult(int pageNum, int pageSize) {
		return (pageNum - 1) * pageSize;
	}

	public PageView(int pageSize, int currentPage, int recordCount, List<T> recordList) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.recordCount = recordCount;
		this.recordList = recordList;

		// 计算其他的属性
		// 1, totalPage
		this.totalPage = (recordCount + pageSize - 1) / pageSize;

		// 2, startPageIndex 与 endPageIndex
		// 1, <=10
		this.startPageIndex = 1;
		this.endPageIndex = this.totalPage;

		// 2, > 10
		if (this.totalPage > 10) {
			// 2-1, 当前页附近的10页 // 当前为7
			startPageIndex = currentPage - 4; // 3
			endPageIndex = currentPage + 5; // 12

			// 2-2, 前10页
			if (startPageIndex < 1) { // 前面页码不足
				startPageIndex = 1;
				endPageIndex = 10;
			}

			// 2-3, 后10页
			else if (endPageIndex > totalPage) { // 后面页码不足
				endPageIndex = totalPage; // 共13, 当12
				startPageIndex = totalPage - 10 + 1;// 2? --> 3
			}
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPageIndex() {
		return startPageIndex;
	}

	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

}
