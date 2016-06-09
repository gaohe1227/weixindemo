package com.weixin.base.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.Paper;
import com.weixin.base.dao.BaseDao;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private Class<T> clazz;
	private BaseDao<T> dao;

	abstract protected BaseDao<T> getDao();

	@Autowired
	private  Mapper<T> mapper;

	public BaseServiceImpl() {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();// 获取当前类的直接超类(或接口)的参数类型
		Type type = parameterizedType.getActualTypeArguments()[0];// 获取此类型实际类型参数对象的数组的第一个类型
		this.clazz = (Class<T>) type;// 准转类型
		System.out.println("当前类型clazz = " + clazz.getName());
		dao = this.getDao();
	}

	/**
	 * 根据id查询数据
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(String id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询所有的数据
	 * 
	 * @return
	 */
	public List<T> queryAll() {
		return this.mapper.select(null);
	}

	/**
	 * 查询数据总条数
	 * 
	 * @return
	 */
	public Integer queryCount() {
		return this.mapper.selectCount(null);
	}

	/**
	 * 根据条件查询一条
	 * 
	 * @param t
	 * @return
	 */
	public T queryOne(T t) {
		return this.mapper.selectOne(t);
	}

	/**
	 * 查询记录数
	 * 
	 * @param query 查询对象，如果为null，则查询对象总数
	 * @return long 记录总数
	 * @date 2015年3月3日下午5:45:40
	 */
	public Long selectCount(Map<String, Object> query) {
		return this.dao.selectCount(query);
	}

	/**
	 * 查询对象列表
	 * 
	 * @param query 查询参数，如果未null则查询所有
	 * @return 结果对象列表
	 * @date 2015年3月3日下午5:43:33
	 */
	public List<T> selectList(Map<String, Object> query) {
		List<T> list=this.dao.selectList(query);
		if(list==null){
			return new ArrayList<T>();
		}
		return list;
	}

	/**
	 * 
	 * @Title: selectListPage 分页使用，分页信息以及分页数据存放在Paper对象中 @Description:
	 * 针对json串类型的搜索条件，该条件中带有搜索字段以及搜索方式（相似、相等、大于、小于、等于） @param @param searchjson
	 * 页面搜索的json串 @param @param page 分页信息 @param @param query
	 * 特定搜索条件 @param @return 设定文件 @return Paper<T> 返回类型 @throws
	 */
	public Paper<T> selectListPage(Paper<T> page, Map<String, Object> queryother) {
		// 查询总数量
		Long count = this.dao.selectCount(queryother);
		if (count > 0) {
			page.setTotal(count);
			queryother.put("page", page);
			List<T> list = this.dao.selectList(queryother);
			page.setDatas(list);
		}
		return page;
	}

	/**
	 * 通过Id查询一个对象，如果id为null这会抛出IllegalArgumentException异常
	 * 
	 * @param id
	 *            主键，不能为null
	 * @return 结果对象，如果未找到返回null
	 * @date 2015年3月3日下午5:58:07
	 */
	public T selectById(String id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据Id删除对象 
	 * @param id:要删除的ID，不能为null
	 * @return int 受影响结果数
	 * @date 2015年3月3日下午5:59:59
	 */
	public int deleteById(String id) {
		return this.dao.deleteById(id);
	}

	/**
	 * 添加对象,如果要添加的对象没有设置Id或者Id为空字符串或者是空格，则添加数据之前会调用 generateId()方法设置Id
	 * 
	 * @param entity: 要实例化的实体，不能为null
	 * @return 受影响的结果数
	 * @date 2015年3月3日下午5:47:15
	 */
	public int insert(T entity) {
		return this.mapper.insert(entity);
	}

	/**
	 * 更新对象，对象必须设置ID
	 * 
	 * @param entity:实体的Id不能为null
	 * @return int 受影响结果数
	 * @date 2015年3月3日下午5:48:05
	 */
	public int updateById(T entity) {
		return this.mapper.updateByPrimaryKey(entity);
	}

	/**
	 * 更新对象中已设置的字段，未设置的字段不更新
	 * 
	 * @param entity
	 *            要更新的实体对象，不能为null，切ID必须不为null
	 * @return int 受影响结果数
	 * @date 2015年3月3日下午5:48:05
	 */
	public int updateByIdSelective(T entity) {
		return this.mapper.updateByPrimaryKeySelective(entity);
	}

}
