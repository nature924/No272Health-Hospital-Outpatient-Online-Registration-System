package com.entity.vo;

import com.entity.YaopinOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 药品订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yaopin_order")
public class YaopinOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "yaopin_order_uuid_number")
    private String yaopinOrderUuidNumber;


    /**
     * 收货地址
     */

    @TableField(value = "address_id")
    private Integer addressId;


    /**
     * 药品
     */

    @TableField(value = "yaopin_id")
    private Integer yaopinId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 购买数量
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "yaopin_order_true_price")
    private Double yaopinOrderTruePrice;


    /**
     * 快递公司
     */

    @TableField(value = "yaopin_order_courier_name")
    private String yaopinOrderCourierName;


    /**
     * 快递单号
     */

    @TableField(value = "yaopin_order_courier_number")
    private String yaopinOrderCourierNumber;


    /**
     * 订单类型
     */

    @TableField(value = "yaopin_order_types")
    private Integer yaopinOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "yaopin_order_payment_types")
    private Integer yaopinOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单编号
	 */
    public String getYaopinOrderUuidNumber() {
        return yaopinOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setYaopinOrderUuidNumber(String yaopinOrderUuidNumber) {
        this.yaopinOrderUuidNumber = yaopinOrderUuidNumber;
    }
    /**
	 * 设置：收货地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 获取：收货地址
	 */

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 设置：药品
	 */
    public Integer getYaopinId() {
        return yaopinId;
    }


    /**
	 * 获取：药品
	 */

    public void setYaopinId(Integer yaopinId) {
        this.yaopinId = yaopinId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getYaopinOrderTruePrice() {
        return yaopinOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setYaopinOrderTruePrice(Double yaopinOrderTruePrice) {
        this.yaopinOrderTruePrice = yaopinOrderTruePrice;
    }
    /**
	 * 设置：快递公司
	 */
    public String getYaopinOrderCourierName() {
        return yaopinOrderCourierName;
    }


    /**
	 * 获取：快递公司
	 */

    public void setYaopinOrderCourierName(String yaopinOrderCourierName) {
        this.yaopinOrderCourierName = yaopinOrderCourierName;
    }
    /**
	 * 设置：快递单号
	 */
    public String getYaopinOrderCourierNumber() {
        return yaopinOrderCourierNumber;
    }


    /**
	 * 获取：快递单号
	 */

    public void setYaopinOrderCourierNumber(String yaopinOrderCourierNumber) {
        this.yaopinOrderCourierNumber = yaopinOrderCourierNumber;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getYaopinOrderTypes() {
        return yaopinOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setYaopinOrderTypes(Integer yaopinOrderTypes) {
        this.yaopinOrderTypes = yaopinOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getYaopinOrderPaymentTypes() {
        return yaopinOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setYaopinOrderPaymentTypes(Integer yaopinOrderPaymentTypes) {
        this.yaopinOrderPaymentTypes = yaopinOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
