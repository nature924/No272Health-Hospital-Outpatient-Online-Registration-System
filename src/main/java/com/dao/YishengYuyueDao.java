package com.dao;

import com.entity.YishengYuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YishengYuyueView;

/**
 * 挂号 Dao 接口
 *
 * @author 
 */
public interface YishengYuyueDao extends BaseMapper<YishengYuyueEntity> {

   List<YishengYuyueView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
