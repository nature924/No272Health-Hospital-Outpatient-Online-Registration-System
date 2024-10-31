package com.dao;

import com.entity.YaopinCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YaopinCommentbackView;

/**
 * 药品评价 Dao 接口
 *
 * @author 
 */
public interface YaopinCommentbackDao extends BaseMapper<YaopinCommentbackEntity> {

   List<YaopinCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
