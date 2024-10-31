package com.dao;

import com.entity.YaopinCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YaopinCollectionView;

/**
 * 药品收藏 Dao 接口
 *
 * @author 
 */
public interface YaopinCollectionDao extends BaseMapper<YaopinCollectionEntity> {

   List<YaopinCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
