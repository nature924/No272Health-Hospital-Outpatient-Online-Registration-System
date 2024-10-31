package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.YishengCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 医生收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("yisheng_collection")
public class YishengCollectionView extends YishengCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String yishengCollectionValue;

	//级联表 医生
		/**
		* 医生工号
		*/

		@ColumnInfo(comment="医生工号",type="varchar(200)")
		private String yishengUuidNumber;
		/**
		* 医生名称
		*/

		@ColumnInfo(comment="医生名称",type="varchar(200)")
		private String yishengName;
		/**
		* 科室
		*/
		@ColumnInfo(comment="科室",type="int(11)")
		private Integer yishengTypes;
			/**
			* 科室的值
			*/
			@ColumnInfo(comment="科室的字典表值",type="varchar(200)")
			private String yishengValue;
		/**
		* 职位
		*/
		@ColumnInfo(comment="职位",type="int(11)")
		private Integer zhiweiTypes;
			/**
			* 职位的值
			*/
			@ColumnInfo(comment="职位的字典表值",type="varchar(200)")
			private String zhiweiValue;
		/**
		* 职称
		*/

		@ColumnInfo(comment="职称",type="varchar(200)")
		private String yishengZhichneg;
		/**
		* 医生头像
		*/

		@ColumnInfo(comment="医生头像",type="varchar(200)")
		private String yishengPhoto;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String yishengPhone;
		/**
		* 挂号须知
		*/

		@ColumnInfo(comment="挂号须知",type="varchar(200)")
		private String yishengYishengYuyue;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String yishengEmail;
		/**
		* 挂号价格
		*/
		@ColumnInfo(comment="挂号价格",type="decimal(10,2)")
		private Double yishengNewMoney;
		/**
		* 履历介绍
		*/

		@ColumnInfo(comment="履历介绍",type="longtext")
		private String yishengContent;
	//级联表 用户
		/**
		* 用户名称
		*/

		@ColumnInfo(comment="用户名称",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer yonghuDelete;



	public YishengCollectionView() {

	}

	public YishengCollectionView(YishengCollectionEntity yishengCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, yishengCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getYishengCollectionValue() {
		return yishengCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setYishengCollectionValue(String yishengCollectionValue) {
		this.yishengCollectionValue = yishengCollectionValue;
	}


	//级联表的get和set 医生

		/**
		* 获取： 医生工号
		*/
		public String getYishengUuidNumber() {
			return yishengUuidNumber;
		}
		/**
		* 设置： 医生工号
		*/
		public void setYishengUuidNumber(String yishengUuidNumber) {
			this.yishengUuidNumber = yishengUuidNumber;
		}

		/**
		* 获取： 医生名称
		*/
		public String getYishengName() {
			return yishengName;
		}
		/**
		* 设置： 医生名称
		*/
		public void setYishengName(String yishengName) {
			this.yishengName = yishengName;
		}
		/**
		* 获取： 科室
		*/
		public Integer getYishengTypes() {
			return yishengTypes;
		}
		/**
		* 设置： 科室
		*/
		public void setYishengTypes(Integer yishengTypes) {
			this.yishengTypes = yishengTypes;
		}


			/**
			* 获取： 科室的值
			*/
			public String getYishengValue() {
				return yishengValue;
			}
			/**
			* 设置： 科室的值
			*/
			public void setYishengValue(String yishengValue) {
				this.yishengValue = yishengValue;
			}
		/**
		* 获取： 职位
		*/
		public Integer getZhiweiTypes() {
			return zhiweiTypes;
		}
		/**
		* 设置： 职位
		*/
		public void setZhiweiTypes(Integer zhiweiTypes) {
			this.zhiweiTypes = zhiweiTypes;
		}


			/**
			* 获取： 职位的值
			*/
			public String getZhiweiValue() {
				return zhiweiValue;
			}
			/**
			* 设置： 职位的值
			*/
			public void setZhiweiValue(String zhiweiValue) {
				this.zhiweiValue = zhiweiValue;
			}

		/**
		* 获取： 职称
		*/
		public String getYishengZhichneg() {
			return yishengZhichneg;
		}
		/**
		* 设置： 职称
		*/
		public void setYishengZhichneg(String yishengZhichneg) {
			this.yishengZhichneg = yishengZhichneg;
		}

		/**
		* 获取： 医生头像
		*/
		public String getYishengPhoto() {
			return yishengPhoto;
		}
		/**
		* 设置： 医生头像
		*/
		public void setYishengPhoto(String yishengPhoto) {
			this.yishengPhoto = yishengPhoto;
		}

		/**
		* 获取： 联系方式
		*/
		public String getYishengPhone() {
			return yishengPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setYishengPhone(String yishengPhone) {
			this.yishengPhone = yishengPhone;
		}

		/**
		* 获取： 挂号须知
		*/
		public String getYishengYishengYuyue() {
			return yishengYishengYuyue;
		}
		/**
		* 设置： 挂号须知
		*/
		public void setYishengYishengYuyue(String yishengYishengYuyue) {
			this.yishengYishengYuyue = yishengYishengYuyue;
		}

		/**
		* 获取： 邮箱
		*/
		public String getYishengEmail() {
			return yishengEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setYishengEmail(String yishengEmail) {
			this.yishengEmail = yishengEmail;
		}

		/**
		* 获取： 挂号价格
		*/
		public Double getYishengNewMoney() {
			return yishengNewMoney;
		}
		/**
		* 设置： 挂号价格
		*/
		public void setYishengNewMoney(Double yishengNewMoney) {
			this.yishengNewMoney = yishengNewMoney;
		}

		/**
		* 获取： 履历介绍
		*/
		public String getYishengContent() {
			return yishengContent;
		}
		/**
		* 设置： 履历介绍
		*/
		public void setYishengContent(String yishengContent) {
			this.yishengContent = yishengContent;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户名称
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户名称
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}


	@Override
	public String toString() {
		return "YishengCollectionView{" +
			", yishengCollectionValue=" + yishengCollectionValue +
			", yishengUuidNumber=" + yishengUuidNumber +
			", yishengName=" + yishengName +
			", yishengZhichneg=" + yishengZhichneg +
			", yishengPhoto=" + yishengPhoto +
			", yishengPhone=" + yishengPhone +
			", yishengYishengYuyue=" + yishengYishengYuyue +
			", yishengEmail=" + yishengEmail +
			", yishengNewMoney=" + yishengNewMoney +
			", yishengContent=" + yishengContent +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}
