/**
 * 
 */
package com.project.service.manage;

import java.util.List;
import java.util.Map;

import com.project.domain.PageView;
import com.project.domain.mapping.User;

/**
 * @author dingjs
 *
 */
public interface UserService {
	public void saveOrUpdate(User user);
	public User getById(Long id);
	public List<User> findAll();
	public User findByUserName(String userName);
	
	
	/**
	 * 得到分页实体List
	 * @param params(数据筛选条件)
	 * @param pageSize(一页显示多少跳记录  默认二十条)
	 * @param currentPage(当前是第几页  默认第一页)
	 * @return
	 */
	public PageView<User> getPageViewList(Map<String,String> params,Integer pageSize,Integer currentPage);
	
	public void saveUsers(List<User> list);
}
