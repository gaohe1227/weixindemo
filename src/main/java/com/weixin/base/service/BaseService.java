package com.weixin.base.service;

import java.util.List;
import java.util.Map;

import com.common.Paper;

public interface BaseService<T> { 
   

    /**
     * 查询记录数
     * 
     * @param query
     *            查询对象，如果为null，则查询对象总数
     * @return long 记录总数
     * @date 2015年3月3日下午5:45:40
     */
    public Long selectCount(Map<String, Object> query);

    /**
     * 查询对象列表
     * 
     * @param query
     *            查询参数，如果未null则查询所有
     * @return 结果对象列表
     * @date 2015年3月3日下午5:43:33
     */
    public   List<T> selectList(Map<String, Object> query); 
    /**
     * 
    * @Title: selectListPage  分页使用，分页信息以及分页数据存放在Paper对象中
    * @param @param page 分页信息
    * @param @param query 特定搜索条件
    * @param @return    设定文件 
    * @return Paper<T>    返回类型 
    * @throws
     */
    public Paper<T> selectListPage(Paper<T> page,Map<String, Object> querycount); 
    /**
     * 通过Id查询一个对象，如果id为null这会抛出IllegalArgumentException异常
     * 
     * @param id  主键，不能为null
     * @return 结果对象，如果未找到返回null
     * @date 2015年3月3日下午5:58:07
     */
    public T selectById(String id);

    /**
     * 根据Id删除对象
     * 
     * @param id
     *            要删除的ID，不能为null
     * @return int 受影响结果数
     * @date 2015年3月3日下午5:59:59
     */
    public int deleteById(String id); 
 

    /**
     * 添加对象,如果要添加的对象没有设置Id或者Id为空字符串或者是空格，则添加数据之前会调用 generateId()方法设置Id
     * 
     * @param entity
     *            要实例化的实体，不能为null
     * @return 受影响的结果数
     * @date 2015年3月3日下午5:47:15
     */
    public int insert(T entity);

    /**
     * 更新对象，对象必须设置ID
     * 
     * @param entity
     *            实体的Id不能为null
     * @return int 受影响结果数
     * @date 2015年3月3日下午5:48:05
     */
    public int updateById(T entity);
    
    /**
     * 更新对象中已设置的字段，未设置的字段不更新
     * @param entity 要更新的实体对象，不能为null，切ID必须不为null
     * @return int 受影响结果数
     * @date 2015年3月3日下午5:48:05
     */
    public int updateByIdSelective(T entity);
 

	/**
	 * 查询所有的数据
	 * 
	 * @return
	 */
	public List<T> queryAll();
 

	/**
	 * 查询数据总条数
	 * 
	 * @return
	 */
	public Integer queryCount();
	 

	/**
	 * 根据条件查询一条
	 * 
	 * @param t
	 * @return
	 */
	public T queryOne(T t) ; 
 

  
}
