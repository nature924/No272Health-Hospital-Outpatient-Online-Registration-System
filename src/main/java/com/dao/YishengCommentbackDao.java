package com.dao;

import com.entity.YishengCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YishengCommentbackView;

/**
 * 医生评价 Dao 接口
 *
 * @author 
 */
public interface YishengCommentbackDao extends BaseMapper<YishengCommentbackEntity> {

   List<YishengCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
