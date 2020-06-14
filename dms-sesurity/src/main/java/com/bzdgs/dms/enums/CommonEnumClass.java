package com.bzdgs.dms.enums;


import com.bzdgs.dms.IResultCode;

/**
 * ClassName: CommonEnumClass <br/>
 * Description: 公用全局枚举类<br/>
 * Date: 2017/12/28 <br/>
 *
 * @author zq
 * @version 1.0.0
 * @since 1.8
 */
public class CommonEnumClass {
	/**
	 * 接口返回码
	 */
	public enum CommonInterfaceEnum implements IResultCode {

		/* 公共模块--开始 */
		SUCCESS("00000000","请求处理成功"),

		UNKNOWN_FAIL("00000001", "未知错误"),

		DATABASE_ERROR("00000002", "数据库操作失败"),

		DATA_ACCESS_RESOURCE_FAILURE("00000011", "访问数据资源故障"),

		PARAM_ERROR("00000100", "请求参数无效"),
		
		ACCESS_DENIED("10000009", "用户或授权服务器拒绝授予访问权限"),

		UNAUTHORIZED_ACCESS("70000000", "无权访问"),

		ACCOUNT_IS_LANDED_ELSEWHERE("70000001", "当前账号已在其他地方登录"),

		TOKEN_EXPIRES("70000002", "登陆信息已失效,请重新登陆"),

		REFRESH_TOKEN_EXPIRES("70000003", "登陆信息已失效,请重新登陆"),

		INVALID_ACCOUNT_PASSWORD("70000004", "账号或密码无效"),

		USER_IS_DISABLED("70000005", "账号已被禁用，请联系管理员"),

		VALIDAT_CODE_FAILURE("70000006","验证码校验失败"),


		/* 公共模块--结束 */
		
		/* 人像库模块--开始 */
		FACEDB_USER_NOTEXIST("70214001", "当前用户不存在"),
		
		FACEDB_USER_NOASSOORGAN("70214002", "当前用户没有关联机构"),
		
		FACEDB_ENGINE_FAIL("70214003", "引擎操作异常"),
		
		FACEDB_NOTEXIST("70214004", "人像库不存在"),
		
		FACEDB_SELECT_ORGAN("70214005", "请选择可见单位"),
		
		FACEDB_FACEDBID_NOTEXIST("70214006", "人像库ID不存在"),
		
		FACEDB_FEATURETASK_ALREADY("70214007", "当前底库已存在重提任务,请等待重提完成之后再进行"),
		
		FACEDB_NOFACE("70214008", "当前底库没有可以重提特征的人像数据"),
		
		FACEDB_COPY_UPLOWER("70214009", "只能复制上级和下级数据"),
		
		FACEDB_IMG_ERROR("70214010", "图片错误"),
		
		FACEDB_MONITOR_NODELT("70214011", "底库已布控,不能删除"),

		FACEDB_DATA_PERMISSIONS("70214012", "没有人像库数据权限"),

		FACEDB_ORGID_NOTEXIST("70214013", "单位不存在"),

		FACEDB_ORGID_DATA_PERMISSIONS("70214014", "没有单位数据权限"),
		
		FACEDB_TYPE_NOTINDIC("70214015", "type值在数据字典中不存在"),
		
		FACEDB_CARDTYPE_NOTINDIC("70214016", "cardType值在数据字典中不存在"),

		FACEDB_FACEDBNAME_EXIST("70214017", "人像库name已存在"),

		FACEDB_NOTEXIST_DATA("70214018", "没有人像库数据"),

		/* 人像库模块--结束 */
		
		/* 人像集模块--开始 */
		FACE_USER_NOTEXIST("70215001", "当前用户不存在"),
		
		FACE_USER_NOASSOORGAN("70215002", "当前用户没有关联单位"),
		
		FACE_ENGINE_FAIL("70215003", "引擎操作异常"),
		
		FACE_AGEB_BIG_AGEE("70215004", "开始年龄不能大于结束年龄"),
		
		FACE_IMG_ERROR("70215005", "图片错误"),
		
		FACE_FACEDB_NOTEXIST("70215006", "人像库不存在"),
		
		FACE_NAS_ERROR("70215007", "Nas存储图片失败"),
		
		FACE_BASE_CHANGEERROR("70215008", "base64获取类型转换错误"),
		
		FACE_FACEID_NOTEXIST("70215009", "底库人脸ID不存在"),

		FACE_DATA_PERMISSIONS("70215010", "没有人像集数据权限"),

		FACE_FACEIDIDS_NOTEXIST_ALL("70215011", "当前传入的人像集id都不存在"),

		FACE_SAVE_BATIMPORT_FORMAT_ERROR("70215012", "批量导入失败!,只支持ZIP格式的压缩文件"),

		FACE_IMAGE_TYPE_ERROR("70215013", "图片类型错误"),

		FACE_IMAGE_MAX_SIZE_ERROR("70215014", "图片数据太大"),

		FACE_IMAGE_NAME_ERROR("70215015", "图片命名错误"),

		FACE_ID_CARD_ERROR("70215016", "身份证错误"),

		FACE_FEATURE_DATA_ERROR("70215017", "人像集重提特征失败"),

		FACE_NOTAVAILABLE_FACEDB("70215018", "没有可用人像库"),

		FACE_CHECK_NOTEXIST("70215019", "没有检测到人脸"),

		ORGID_NOTEXIST("70003100", "单位不存在"),

		USER_IMG_DATA_ERROR("70005100", "图片数据错误"),


		;
		/* 人像集模块--结束 */

		private final String key;

		private final String value;

		CommonInterfaceEnum(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}
	
	/**
	 * 布控状态（1:已布控 0:未布控）
	 */
	public enum MonitorState {

		ALREADY(1, "已布控"),
		
		DIDNOT(0, "未布控");

		private final Integer key;

		private final String value;

		MonitorState(Integer key, String value) {
			this.key = key;
			this.value = value;
		}

		public Integer getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

	}
	
	/**
	 * 底库用途
	 */
	public enum FaceDbModel {

		COMMON(1, "通用"), 
		
		BOTH(2, "通用+人口管理分析");

		private final Integer key;

		private final String value;

		FaceDbModel(Integer key, String value) {
			this.key = key;
			this.value = value;
		}

		public Integer getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

	}
	
	/**
	 * 是否重点人口
	 */
	public enum KeyState {
		/**
		 * 重点人口类型
		 */
		YES(1, "是"), 
		
		NO(0, "否");

		private final int key;

		private final String value;

		KeyState(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

	}
	
	/**
	 * 性别 1男 2女
	 */
	public enum Gender {

		MAN(1, "男"), 
		
		WOMAN(2, "女");

		private final int key;

		private final String value;

		Gender(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

	}
	
	/**
	 * 特征值状态（1有效 0无效 -1删除）
	 */
	public enum FeatureState {

		VALID(1, "有效"), 
		
		INVALID(0, "无效"),
		
		DELETED(-1, "删除");

		private final int key;

		private final String value;

		FeatureState(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

	}
}
