
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 药品
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yaopin")
public class YaopinController {
    private static final Logger logger = LoggerFactory.getLogger(YaopinController.class);

    private static final String TABLE_NAME = "yaopin";

    @Autowired
    private YaopinService yaopinService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AddressService addressService;//收货地址
    @Autowired
    private CartService cartService;//购物车
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//公告信息
    @Autowired
    private YaopinCollectionService yaopinCollectionService;//药品收藏
    @Autowired
    private YaopinCommentbackService yaopinCommentbackService;//药品评价
    @Autowired
    private YaopinOrderService yaopinOrderService;//药品订单
    @Autowired
    private YishengService yishengService;//医生
    @Autowired
    private YishengCollectionService yishengCollectionService;//医生收藏
    @Autowired
    private YishengCommentbackService yishengCommentbackService;//医生评价
    @Autowired
    private YishengYuyueService yishengYuyueService;//挂号
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("医生".equals(role))
            params.put("yishengId",request.getSession().getAttribute("userId"));
        params.put("yaopinDeleteStart",1);params.put("yaopinDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = yaopinService.queryPage(params);

        //字典表数据转换
        List<YaopinView> list =(List<YaopinView>)page.getList();
        for(YaopinView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YaopinEntity yaopin = yaopinService.selectById(id);
        if(yaopin !=null){
            //entity转view
            YaopinView view = new YaopinView();
            BeanUtils.copyProperties( yaopin , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YaopinEntity yaopin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yaopin:{}",this.getClass().getName(),yaopin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YaopinEntity> queryWrapper = new EntityWrapper<YaopinEntity>()
            .eq("yaopin_name", yaopin.getYaopinName())
            .eq("zan_number", yaopin.getZanNumber())
            .eq("cai_number", yaopin.getCaiNumber())
            .eq("yaopin_types", yaopin.getYaopinTypes())
            .eq("yaopin_kucun_number", yaopin.getYaopinKucunNumber())
            .eq("shangxia_types", yaopin.getShangxiaTypes())
            .eq("yaopin_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YaopinEntity yaopinEntity = yaopinService.selectOne(queryWrapper);
        if(yaopinEntity==null){
            yaopin.setYaopinClicknum(1);
            yaopin.setShangxiaTypes(1);
            yaopin.setYaopinDelete(1);
            yaopin.setInsertTime(new Date());
            yaopin.setCreateTime(new Date());
            yaopinService.insert(yaopin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YaopinEntity yaopin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yaopin:{}",this.getClass().getName(),yaopin.toString());
        YaopinEntity oldYaopinEntity = yaopinService.selectById(yaopin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(yaopin.getYaopinPhoto()) || "null".equals(yaopin.getYaopinPhoto())){
                yaopin.setYaopinPhoto(null);
        }

            yaopinService.updateById(yaopin);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YaopinEntity> oldYaopinList =yaopinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<YaopinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YaopinEntity yaopinEntity = new YaopinEntity();
            yaopinEntity.setId(id);
            yaopinEntity.setYaopinDelete(2);
            list.add(yaopinEntity);
        }
        if(list != null && list.size() >0){
            yaopinService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<YaopinEntity> yaopinList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YaopinEntity yaopinEntity = new YaopinEntity();
//                            yaopinEntity.setYaopinName(data.get(0));                    //药品名称 要改的
//                            yaopinEntity.setYaopinUuidNumber(data.get(0));                    //药品编号 要改的
//                            yaopinEntity.setYaopinPhoto("");//详情和图片
//                            yaopinEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            yaopinEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            yaopinEntity.setYaopinTypes(Integer.valueOf(data.get(0)));   //药品类型 要改的
//                            yaopinEntity.setYaopinKucunNumber(Integer.valueOf(data.get(0)));   //药品库存 要改的
//                            yaopinEntity.setYaopinOldMoney(data.get(0));                    //药品原价 要改的
//                            yaopinEntity.setYaopinNewMoney(data.get(0));                    //现价/积分 要改的
//                            yaopinEntity.setYaopinClicknum(Integer.valueOf(data.get(0)));   //药品热度 要改的
//                            yaopinEntity.setYaopinContent("");//详情和图片
//                            yaopinEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            yaopinEntity.setYaopinDelete(1);//逻辑删除字段
//                            yaopinEntity.setInsertTime(date);//时间
//                            yaopinEntity.setCreateTime(date);//时间
                            yaopinList.add(yaopinEntity);


                            //把要查询是否重复的字段放入map中
                                //药品编号
                                if(seachFields.containsKey("yaopinUuidNumber")){
                                    List<String> yaopinUuidNumber = seachFields.get("yaopinUuidNumber");
                                    yaopinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yaopinUuidNumber = new ArrayList<>();
                                    yaopinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yaopinUuidNumber",yaopinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //药品编号
                        List<YaopinEntity> yaopinEntities_yaopinUuidNumber = yaopinService.selectList(new EntityWrapper<YaopinEntity>().in("yaopin_uuid_number", seachFields.get("yaopinUuidNumber")).eq("yaopin_delete", 1));
                        if(yaopinEntities_yaopinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YaopinEntity s:yaopinEntities_yaopinUuidNumber){
                                repeatFields.add(s.getYaopinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [药品编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yaopinService.insertBatch(yaopinList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<YaopinView> returnYaopinViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = yaopinOrderService.queryPage(params1);
        List<YaopinOrderView> orderViewsList =(List<YaopinOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(YaopinOrderView orderView:orderViewsList){
            Integer yaopinTypes = orderView.getYaopinTypes();
            if(typeMap.containsKey(yaopinTypes)){
                typeMap.put(yaopinTypes,typeMap.get(yaopinTypes)+1);
            }else{
                typeMap.put(yaopinTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("yaopinTypes",type);
            PageUtils pageUtils1 = yaopinService.queryPage(params2);
            List<YaopinView> yaopinViewList =(List<YaopinView>)pageUtils1.getList();
            returnYaopinViewList.addAll(yaopinViewList);
            if(returnYaopinViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = yaopinService.queryPage(params);
        if(returnYaopinViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnYaopinViewList.size();//要添加的数量
            List<YaopinView> yaopinViewList =(List<YaopinView>)page.getList();
            for(YaopinView yaopinView:yaopinViewList){
                Boolean addFlag = true;
                for(YaopinView returnYaopinView:returnYaopinViewList){
                    if(returnYaopinView.getId().intValue() ==yaopinView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnYaopinViewList.add(yaopinView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnYaopinViewList = returnYaopinViewList.subList(0, limit);
        }

        for(YaopinView c:returnYaopinViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnYaopinViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yaopinService.queryPage(params);

        //字典表数据转换
        List<YaopinView> list =(List<YaopinView>)page.getList();
        for(YaopinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YaopinEntity yaopin = yaopinService.selectById(id);
            if(yaopin !=null){

                //点击数量加1
                yaopin.setYaopinClicknum(yaopin.getYaopinClicknum()+1);
                yaopinService.updateById(yaopin);

                //entity转view
                YaopinView view = new YaopinView();
                BeanUtils.copyProperties( yaopin , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody YaopinEntity yaopin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yaopin:{}",this.getClass().getName(),yaopin.toString());
        Wrapper<YaopinEntity> queryWrapper = new EntityWrapper<YaopinEntity>()
            .eq("yaopin_name", yaopin.getYaopinName())
            .eq("yaopin_uuid_number", yaopin.getYaopinUuidNumber())
            .eq("zan_number", yaopin.getZanNumber())
            .eq("cai_number", yaopin.getCaiNumber())
            .eq("yaopin_types", yaopin.getYaopinTypes())
            .eq("yaopin_kucun_number", yaopin.getYaopinKucunNumber())
            .eq("yaopin_clicknum", yaopin.getYaopinClicknum())
            .eq("shangxia_types", yaopin.getShangxiaTypes())
            .eq("yaopin_delete", yaopin.getYaopinDelete())
//            .notIn("yaopin_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YaopinEntity yaopinEntity = yaopinService.selectOne(queryWrapper);
        if(yaopinEntity==null){
                yaopin.setZanNumber(1);
                yaopin.setCaiNumber(1);
            yaopin.setYaopinClicknum(1);
            yaopin.setYaopinDelete(1);
            yaopin.setInsertTime(new Date());
            yaopin.setCreateTime(new Date());
        yaopinService.insert(yaopin);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

