package com.dao;

import com.entity.YaopinOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YaopinOrderView;

/**
 * 药品订单 Dao 接口
 *
 * @author 
 */
public interface YaopinOrderDao extends BaseMapper<YaopinOrderEntity> {

   List<YaopinOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
