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
 * 医生
 *
 * @author 
 * @email
 */
@TableName("yisheng")
public class YishengEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YishengEntity() {

	}

	public YishengEntity(T t) {
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
     * 医生工号
     */
    @ColumnInfo(comment="医生工号",type="varchar(200)")
    @TableField(value = "yisheng_uuid_number")

    private String yishengUuidNumber;


    /**
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 医生名称
     */
    @ColumnInfo(comment="医生名称",type="varchar(200)")
    @TableField(value = "yisheng_name")

    private String yishengName;


    /**
     * 科室
     */
    @ColumnInfo(comment="科室",type="int(11)")
    @TableField(value = "yisheng_types")

    private Integer yishengTypes;


    /**
     * 职位
     */
    @ColumnInfo(comment="职位",type="int(11)")
    @TableField(value = "zhiwei_types")

    private Integer zhiweiTypes;


    /**
     * 职称
     */
    @ColumnInfo(comment="职称",type="varchar(200)")
    @TableField(value = "yisheng_zhichneg")

    private String yishengZhichneg;


    /**
     * 医生头像
     */
    @ColumnInfo(comment="医生头像",type="varchar(200)")
    @TableField(value = "yisheng_photo")

    private String yishengPhoto;


    /**
     * 联系方式
     */
    @ColumnInfo(comment="联系方式",type="varchar(200)")
    @TableField(value = "yisheng_phone")

    private String yishengPhone;


    /**
     * 挂号须知
     */
    @ColumnInfo(comment="挂号须知",type="varchar(200)")
    @TableField(value = "yisheng_yisheng_yuyue")

    private String yishengYishengYuyue;


    /**
     * 邮箱
     */
    @ColumnInfo(comment="邮箱",type="varchar(200)")
    @TableField(value = "yisheng_email")

    private String yishengEmail;


    /**
     * 挂号价格
     */
    @ColumnInfo(comment="挂号价格",type="decimal(10,2)")
    @TableField(value = "yisheng_new_money")

    private Double yishengNewMoney;


    /**
     * 履历介绍
     */
    @ColumnInfo(comment="履历介绍",type="longtext")
    @TableField(value = "yisheng_content")

    private String yishengContent;


    /**
     * 创建时间
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
	 * 获取：医生工号
	 */
    public String getYishengUuidNumber() {
        return yishengUuidNumber;
    }
    /**
	 * 设置：医生工号
	 */

    public void setYishengUuidNumber(String yishengUuidNumber) {
        this.yishengUuidNumber = yishengUuidNumber;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 设置：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 设置：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：医生名称
	 */
    public String getYishengName() {
        return yishengName;
    }
    /**
	 * 设置：医生名称
	 */

    public void setYishengName(String yishengName) {
        this.yishengName = yishengName;
    }
    /**
	 * 获取：科室
	 */
    public Integer getYishengTypes() {
        return yishengTypes;
    }
    /**
	 * 设置：科室
	 */

    public void setYishengTypes(Integer yishengTypes) {
        this.yishengTypes = yishengTypes;
    }
    /**
	 * 获取：职位
	 */
    public Integer getZhiweiTypes() {
        return zhiweiTypes;
    }
    /**
	 * 设置：职位
	 */

    public void setZhiweiTypes(Integer zhiweiTypes) {
        this.zhiweiTypes = zhiweiTypes;
    }
    /**
	 * 获取：职称
	 */
    public String getYishengZhichneg() {
        return yishengZhichneg;
    }
    /**
	 * 设置：职称
	 */

    public void setYishengZhichneg(String yishengZhichneg) {
        this.yishengZhichneg = yishengZhichneg;
    }
    /**
	 * 获取：医生头像
	 */
    public String getYishengPhoto() {
        return yishengPhoto;
    }
    /**
	 * 设置：医生头像
	 */

    public void setYishengPhoto(String yishengPhoto) {
        this.yishengPhoto = yishengPhoto;
    }
    /**
	 * 获取：联系方式
	 */
    public String getYishengPhone() {
        return yishengPhone;
    }
    /**
	 * 设置：联系方式
	 */

    public void setYishengPhone(String yishengPhone) {
        this.yishengPhone = yishengPhone;
    }
    /**
	 * 获取：挂号须知
	 */
    public String getYishengYishengYuyue() {
        return yishengYishengYuyue;
    }
    /**
	 * 设置：挂号须知
	 */

    public void setYishengYishengYuyue(String yishengYishengYuyue) {
        this.yishengYishengYuyue = yishengYishengYuyue;
    }
    /**
	 * 获取：邮箱
	 */
    public String getYishengEmail() {
        return yishengEmail;
    }
    /**
	 * 设置：邮箱
	 */

    public void setYishengEmail(String yishengEmail) {
        this.yishengEmail = yishengEmail;
    }
    /**
	 * 获取：挂号价格
	 */
    public Double getYishengNewMoney() {
        return yishengNewMoney;
    }
    /**
	 * 设置：挂号价格
	 */

    public void setYishengNewMoney(Double yishengNewMoney) {
        this.yishengNewMoney = yishengNewMoney;
    }
    /**
	 * 获取：履历介绍
	 */
    public String getYishengContent() {
        return yishengContent;
    }
    /**
	 * 设置：履历介绍
	 */

    public void setYishengContent(String yishengContent) {
        this.yishengContent = yishengContent;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Yisheng{" +
            ", id=" + id +
            ", yishengUuidNumber=" + yishengUuidNumber +
            ", username=" + username +
            ", password=" + password +
            ", yishengName=" + yishengName +
            ", yishengTypes=" + yishengTypes +
            ", zhiweiTypes=" + zhiweiTypes +
            ", yishengZhichneg=" + yishengZhichneg +
            ", yishengPhoto=" + yishengPhoto +
            ", yishengPhone=" + yishengPhone +
            ", yishengYishengYuyue=" + yishengYishengYuyue +
            ", yishengEmail=" + yishengEmail +
            ", yishengNewMoney=" + yishengNewMoney +
            ", yishengContent=" + yishengContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
