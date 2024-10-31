package com.dao;

import com.entity.YishengCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YishengCollectionView;

/**
 * 医生收藏 Dao 接口
 *
 * @author 
 */
public interface YishengCollectionDao extends BaseMapper<YishengCollectionEntity> {

   List<YishengCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
