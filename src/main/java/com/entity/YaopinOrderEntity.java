package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 药品订单
 *
 * @author 
 * @email
 */
@TableName("yaopin_order")
public class YaopinOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YaopinOrderEntity() {

	}

	public YaopinOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单编号
     */
    @ColumnInfo(comment="订单编号",type="varchar(200)")
    @TableField(value = "yaopin_order_uuid_number")

    private String yaopinOrderUuidNumber;


    /**
     * 收货地址
     */
    @ColumnInfo(comment="收货地址",type="int(11)")
    @TableField(value = "address_id")

    private Integer addressId;


    /**
     * 药品
     */
    @ColumnInfo(comment="药品",type="int(11)")
    @TableField(value = "yaopin_id")

    private Integer yaopinId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 购买数量
     */
    @ColumnInfo(comment="购买数量",type="int(11)")
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 实付价格
     */
    @ColumnInfo(comment="实付价格",type="decimal(10,2)")
    @TableField(value = "yaopin_order_true_price")

    private Double yaopinOrderTruePrice;


    /**
     * 快递公司
     */
    @ColumnInfo(comment="快递公司",type="varchar(200)")
    @TableField(value = "yaopin_order_courier_name")

    private String yaopinOrderCourierName;


    /**
     * 快递单号
     */
    @ColumnInfo(comment="快递单号",type="varchar(200)")
    @TableField(value = "yaopin_order_courier_number")

    private String yaopinOrderCourierNumber;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "yaopin_order_types")

    private Integer yaopinOrderTypes;


    /**
     * 支付类型
     */
    @ColumnInfo(comment="支付类型",type="int(11)")
    @TableField(value = "yaopin_order_payment_types")

    private Integer yaopinOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="订单创建时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "YaopinOrder{" +
            ", id=" + id +
            ", yaopinOrderUuidNumber=" + yaopinOrderUuidNumber +
            ", addressId=" + addressId +
            ", yaopinId=" + yaopinId +
            ", yonghuId=" + yonghuId +
            ", buyNumber=" + buyNumber +
            ", yaopinOrderTruePrice=" + yaopinOrderTruePrice +
            ", yaopinOrderCourierName=" + yaopinOrderCourierName +
            ", yaopinOrderCourierNumber=" + yaopinOrderCourierNumber +
            ", yaopinOrderTypes=" + yaopinOrderTypes +
            ", yaopinOrderPaymentTypes=" + yaopinOrderPaymentTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
