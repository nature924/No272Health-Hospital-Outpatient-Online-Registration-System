package com.entity.model;

import com.entity.YishengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 医生
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YishengModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 医生工号
     */
    private String yishengUuidNumber;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 医生名称
     */
    private String yishengName;


    /**
     * 科室
     */
    private Integer yishengTypes;


    /**
     * 职位
     */
    private Integer zhiweiTypes;


    /**
     * 职称
     */
    private String yishengZhichneg;


    /**
     * 医生头像
     */
    private String yishengPhoto;


    /**
     * 联系方式
     */
    private String yishengPhone;


    /**
     * 挂号须知
     */
    private String yishengYishengYuyue;


    /**
     * 邮箱
     */
    private String yishengEmail;


    /**
     * 挂号价格
     */
    private Double yishengNewMoney;


    /**
     * 履历介绍
     */
    private String yishengContent;


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
