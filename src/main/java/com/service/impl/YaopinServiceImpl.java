package com.service.impl;

import com.utils.StringUtil;
import com.service.DictionaryService;
import com.utils.ClazzDiff;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import com.dao.YaopinDao;
import com.entity.YaopinEntity;
import com.service.YaopinService;
import com.entity.view.YaopinView;

/**
 * 药品 服务实现类
 */
@Service("yaopinService")
@Transactional
public class YaopinServiceImpl extends ServiceImpl<YaopinDao, YaopinEntity> implements YaopinService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<YaopinView> page =new Query<YaopinView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
