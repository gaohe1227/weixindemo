package com.weixin.base.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	 /**
     * 查询总记录数
     * 
     * @return long 记录总数
     * @date 2015年3月3日下午5:35:36
     */
    public Long selectCount();
    
    /**
     * 查询所有记录列表
     * 
     * @return List 结果列表
     * @date 2015年3月3日下午5:35:01
     */
    public <V extends T> List<V> selectList();

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
    public <V extends T> List<V> selectList(Map<String, Object> query); 

    /**
     * 通过Id查询一个对象，如果id为null这会抛出IllegalArgumentException异常
     * 
     * @param id  主键，不能为null
     * @return 结果对象，如果未找到返回null
     * @date 2015年3月3日下午5:58:07
     */
    public <V extends T> V selectById(String id);

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
     * 删除对象
     * 
     * @param entity
     *            要删除的实体对象，不能为null
     * @return int 受影响结果数
     * @date 2015年3月3日下午5:47:47
     */
    public int delete(T query);

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
}
