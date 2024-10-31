
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
 * 药品订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yaopinOrder")
public class YaopinOrderController {
    private static final Logger logger = LoggerFactory.getLogger(YaopinOrderController.class);

    private static final String TABLE_NAME = "yaopinOrder";

    @Autowired
    private YaopinOrderService yaopinOrderService;


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
    private YaopinService yaopinService;//药品
    @Autowired
    private YaopinCollectionService yaopinCollectionService;//药品收藏
    @Autowired
    private YaopinCommentbackService yaopinCommentbackService;//药品评价
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
        CommonUtil.checkMap(params);
        PageUtils page = yaopinOrderService.queryPage(params);

        //字典表数据转换
        List<YaopinOrderView> list =(List<YaopinOrderView>)page.getList();
        for(YaopinOrderView c:list){
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
        YaopinOrderEntity yaopinOrder = yaopinOrderService.selectById(id);
        if(yaopinOrder !=null){
            //entity转view
            YaopinOrderView view = new YaopinOrderView();
            BeanUtils.copyProperties( yaopinOrder , view );//把实体数据重构到view中
            //级联表 收货地址
            //级联表
            AddressEntity address = addressService.selectById(yaopinOrder.getAddressId());
            if(address != null){
            BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setAddressId(address.getId());
            }
            //级联表 药品
            //级联表
            YaopinEntity yaopin = yaopinService.selectById(yaopinOrder.getYaopinId());
            if(yaopin != null){
            BeanUtils.copyProperties( yaopin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYaopinId(yaopin.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yaopinOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody YaopinOrderEntity yaopinOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yaopinOrder:{}",this.getClass().getName(),yaopinOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yaopinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        yaopinOrder.setCreateTime(new Date());
        yaopinOrder.setInsertTime(new Date());
        yaopinOrderService.insert(yaopinOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YaopinOrderEntity yaopinOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yaopinOrder:{}",this.getClass().getName(),yaopinOrder.toString());
        YaopinOrderEntity oldYaopinOrderEntity = yaopinOrderService.selectById(yaopinOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            yaopinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            yaopinOrderService.updateById(yaopinOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YaopinOrderEntity> oldYaopinOrderList =yaopinOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        yaopinOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<YaopinOrderEntity> yaopinOrderList = new ArrayList<>();//上传的东西
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
                            YaopinOrderEntity yaopinOrderEntity = new YaopinOrderEntity();
//                            yaopinOrderEntity.setYaopinOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            yaopinOrderEntity.setAddressId(Integer.valueOf(data.get(0)));   //收货地址 要改的
//                            yaopinOrderEntity.setYaopinId(Integer.valueOf(data.get(0)));   //药品 要改的
//                            yaopinOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yaopinOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            yaopinOrderEntity.setYaopinOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            yaopinOrderEntity.setYaopinOrderCourierName(data.get(0));                    //快递公司 要改的
//                            yaopinOrderEntity.setYaopinOrderCourierNumber(data.get(0));                    //快递单号 要改的
//                            yaopinOrderEntity.setYaopinOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            yaopinOrderEntity.setYaopinOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            yaopinOrderEntity.setInsertTime(date);//时间
//                            yaopinOrderEntity.setCreateTime(date);//时间
                            yaopinOrderList.add(yaopinOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("yaopinOrderUuidNumber")){
                                    List<String> yaopinOrderUuidNumber = seachFields.get("yaopinOrderUuidNumber");
                                    yaopinOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yaopinOrderUuidNumber = new ArrayList<>();
                                    yaopinOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yaopinOrderUuidNumber",yaopinOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<YaopinOrderEntity> yaopinOrderEntities_yaopinOrderUuidNumber = yaopinOrderService.selectList(new EntityWrapper<YaopinOrderEntity>().in("yaopin_order_uuid_number", seachFields.get("yaopinOrderUuidNumber")));
                        if(yaopinOrderEntities_yaopinOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YaopinOrderEntity s:yaopinOrderEntities_yaopinOrderUuidNumber){
                                repeatFields.add(s.getYaopinOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yaopinOrderService.insertBatch(yaopinOrderList);
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yaopinOrderService.queryPage(params);

        //字典表数据转换
        List<YaopinOrderView> list =(List<YaopinOrderView>)page.getList();
        for(YaopinOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YaopinOrderEntity yaopinOrder = yaopinOrderService.selectById(id);
            if(yaopinOrder !=null){


                //entity转view
                YaopinOrderView view = new YaopinOrderView();
                BeanUtils.copyProperties( yaopinOrder , view );//把实体数据重构到view中

                //级联表
                    AddressEntity address = addressService.selectById(yaopinOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                }
                //级联表
                    YaopinEntity yaopin = yaopinService.selectById(yaopinOrder.getYaopinId());
                if(yaopin != null){
                    BeanUtils.copyProperties( yaopin , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYaopinId(yaopin.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(yaopinOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody YaopinOrderEntity yaopinOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yaopinOrder:{}",this.getClass().getName(),yaopinOrder.toString());
            YaopinEntity yaopinEntity = yaopinService.selectById(yaopinOrder.getYaopinId());
            if(yaopinEntity == null){
                return R.error(511,"查不到该药品");
            }
            // Double yaopinNewMoney = yaopinEntity.getYaopinNewMoney();

            if(false){
            }
            else if(yaopinEntity.getYaopinNewMoney() == null){
                return R.error(511,"现价/积分不能为空");
            }
            else if((yaopinEntity.getYaopinKucunNumber() -yaopinOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - yaopinEntity.getYaopinNewMoney()*yaopinOrder.getBuyNumber();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            yaopinOrder.setYaopinOrderTypes(101); //设置订单状态为已支付
            yaopinOrder.setYaopinOrderTruePrice(yaopinEntity.getYaopinNewMoney()*yaopinOrder.getBuyNumber()); //设置实付价格
            yaopinOrder.setYonghuId(userId); //设置订单支付人id
            yaopinOrder.setYaopinOrderUuidNumber(String.valueOf(new Date().getTime()));
            yaopinOrder.setYaopinOrderPaymentTypes(1);
            yaopinOrder.setInsertTime(new Date());
            yaopinOrder.setCreateTime(new Date());
                yaopinEntity.setYaopinKucunNumber( yaopinEntity.getYaopinKucunNumber() -yaopinOrder.getBuyNumber());
                yaopinService.updateById(yaopinEntity);
                yaopinOrderService.insert(yaopinOrder);//新增订单
            //更新第一注册表
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);


            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String yaopinOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer addressId = Integer.valueOf(String.valueOf(params.get("addressId")));

            Integer yaopinOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("yaopinOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("yaopins"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> yaopins = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<YaopinOrderEntity> yaopinOrderList = new ArrayList<>();
        //商品表
        List<YaopinEntity> yaopinList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : yaopins) {
           //取值
            Integer yaopinId = Integer.valueOf(String.valueOf(map.get("yaopinId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            YaopinEntity yaopinEntity = yaopinService.selectById(yaopinId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(yaopinEntity.getYaopinKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(yaopinEntity.getYaopinName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                yaopinEntity.setYaopinKucunNumber(yaopinEntity.getYaopinKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            YaopinOrderEntity yaopinOrderEntity = new YaopinOrderEntity<>();

            //赋值订单信息
            yaopinOrderEntity.setYaopinOrderUuidNumber(yaopinOrderUuidNumber);//订单编号
            yaopinOrderEntity.setAddressId(addressId);//收货地址
            yaopinOrderEntity.setYaopinId(yaopinId);//药品
                        yaopinOrderEntity.setYonghuId(userId);//用户
            yaopinOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            yaopinOrderEntity.setYaopinOrderTypes(101);//订单类型
            yaopinOrderEntity.setYaopinOrderPaymentTypes(yaopinOrderPaymentTypes);//支付类型
            yaopinOrderEntity.setInsertTime(new Date());//订单创建时间
            yaopinOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(yaopinOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(yaopinEntity.getYaopinNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额


                    yaopinOrderEntity.setYaopinOrderTruePrice(money);

                }
            }
            yaopinOrderList.add(yaopinOrderEntity);
            yaopinList.add(yaopinEntity);

        }
        yaopinOrderService.insertBatch(yaopinOrderList);
        yaopinService.updateBatchById(yaopinList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);

        return R.ok();
    }


    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            YaopinOrderEntity yaopinOrder = yaopinOrderService.selectById(id);//当前表service
            Integer buyNumber = yaopinOrder.getBuyNumber();
            Integer yaopinOrderPaymentTypes = yaopinOrder.getYaopinOrderPaymentTypes();
            Integer yaopinId = yaopinOrder.getYaopinId();
            if(yaopinId == null)
                return R.error(511,"查不到该药品");
            YaopinEntity yaopinEntity = yaopinService.selectById(yaopinId);
            if(yaopinEntity == null)
                return R.error(511,"查不到该药品");
            Double yaopinNewMoney = yaopinEntity.getYaopinNewMoney();
            if(yaopinNewMoney == null)
                return R.error(511,"药品价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

            //判断是什么支付方式 1代表余额 2代表积分
            if(yaopinOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = yaopinEntity.getYaopinNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }

            yaopinEntity.setYaopinKucunNumber(yaopinEntity.getYaopinKucunNumber() + buyNumber);

            yaopinOrder.setYaopinOrderTypes(102);//设置订单状态为已退款
            yaopinOrderService.updateAllColumnById(yaopinOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            yaopinService.updateById(yaopinEntity);//更新订单中药品的信息

            return R.ok();
    }

    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer yaopinCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            YaopinOrderEntity yaopinOrder = yaopinOrderService.selectById(id);
        if(yaopinOrder == null)
            return R.error(511,"查不到该订单");
        Integer yaopinId = yaopinOrder.getYaopinId();
        if(yaopinId == null)
            return R.error(511,"查不到该药品");

        YaopinCommentbackEntity yaopinCommentbackEntity = new YaopinCommentbackEntity();
            yaopinCommentbackEntity.setId(id);
            yaopinCommentbackEntity.setYaopinId(yaopinId);
            yaopinCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            yaopinCommentbackEntity.setYaopinCommentbackText(commentbackText);
            yaopinCommentbackEntity.setInsertTime(new Date());
            yaopinCommentbackEntity.setReplyText(null);
            yaopinCommentbackEntity.setUpdateTime(null);
            yaopinCommentbackEntity.setCreateTime(new Date());
            yaopinCommentbackService.insert(yaopinCommentbackEntity);

            yaopinOrder.setYaopinOrderTypes(105);//设置订单状态为已评价
            yaopinOrderService.updateById(yaopinOrder);//根据id更新
            return R.ok();
    }

    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String yaopinOrderCourierNumber, String yaopinOrderCourierName , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        YaopinOrderEntity  yaopinOrderEntity = yaopinOrderService.selectById(id);
        yaopinOrderEntity.setYaopinOrderTypes(103);//设置订单状态为已发货
        yaopinOrderEntity.setYaopinOrderCourierNumber(yaopinOrderCourierNumber);
        yaopinOrderEntity.setYaopinOrderCourierName(yaopinOrderCourierName);
        yaopinOrderService.updateById( yaopinOrderEntity);

        return R.ok();
    }


    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        YaopinOrderEntity  yaopinOrderEntity = yaopinOrderService.selectById(id);
        yaopinOrderEntity.setYaopinOrderTypes(104);//设置订单状态为收货
        yaopinOrderService.updateById( yaopinOrderEntity);
        return R.ok();
    }

}

