package com.lanou.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/20.
 */
public interface BaseDao<T> {


    /**
     * 查询所有
     * @return 返回结果集
     */
    List<T> findAll(String hql);


    /**
     * 根据条件查询, 返回查询到的对象集合
     * @param hql 查询语句
     * @param params 查询语句的参数集合
     * @return 查询到的结果集合
     */
    List<T> find(String hql, Map<String,Object> params);


    /**
     * 根据条件查询, 返回查询到的第一个对象
     * @param hql 查询语句
     * @param params 查询语句的参数集合
     * @return 第一个查询到的结果
     */
    T findSingle(String hql, Map<String,Object> params);


    /**
     * 根据主键id查询某个对象
     * @param id 要查询的主键id
     * @param tClass 返回对象的类声明
     */
    T findById(Serializable id, Class<T> tClass);

}
