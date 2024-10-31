package com.entity.model;

import com.entity.YaopinOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 药品订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YaopinOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单编号
     */
    private String yaopinOrderUuidNumber;


    /**
     * 收货地址
     */
    private Integer addressId;


    /**
     * 药品
     */
    private Integer yaopinId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买数量
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double yaopinOrderTruePrice;


    /**
     * 快递公司
     */
    private String yaopinOrderCourierName;


    /**
     * 快递单号
     */
    private String yaopinOrderCourierNumber;


    /**
     * 订单类型
     */
    private Integer yaopinOrderTypes;


    /**
     * 支付类型
     */
    private Integer yaopinOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单编号
	 */
    public String getYaopinOrderUuidNumber() {
        return yaopinOrderUuidNumber;
    }


    /**
	 * 设置：订单编号
	 */
    public void setYaopinOrderUuidNumber(String yaopinOrderUuidNumber) {
        this.yaopinOrderUuidNumber = yaopinOrderUuidNumber;
    }
    /**
	 * 获取：收货地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 设置：收货地址
	 */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 获取：药品
	 */
    public Integer getYaopinId() {
        return yaopinId;
    }


    /**
	 * 设置：药品
	 */
    public void setYaopinId(Integer yaopinId) {
        this.yaopinId = yaopinId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getYaopinOrderTruePrice() {
        return yaopinOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setYaopinOrderTruePrice(Double yaopinOrderTruePrice) {
        this.yaopinOrderTruePrice = yaopinOrderTruePrice;
    }
    /**
	 * 获取：快递公司
	 */
    public String getYaopinOrderCourierName() {
        return yaopinOrderCourierName;
    }


    /**
	 * 设置：快递公司
	 */
    public void setYaopinOrderCourierName(String yaopinOrderCourierName) {
        this.yaopinOrderCourierName = yaopinOrderCourierName;
    }
    /**
	 * 获取：快递单号
	 */
    public String getYaopinOrderCourierNumber() {
        return yaopinOrderCourierNumber;
    }


    /**
	 * 设置：快递单号
	 */
    public void setYaopinOrderCourierNumber(String yaopinOrderCourierNumber) {
        this.yaopinOrderCourierNumber = yaopinOrderCourierNumber;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getYaopinOrderTypes() {
        return yaopinOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setYaopinOrderTypes(Integer yaopinOrderTypes) {
        this.yaopinOrderTypes = yaopinOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getYaopinOrderPaymentTypes() {
        return yaopinOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setYaopinOrderPaymentTypes(Integer yaopinOrderPaymentTypes) {
        this.yaopinOrderPaymentTypes = yaopinOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
