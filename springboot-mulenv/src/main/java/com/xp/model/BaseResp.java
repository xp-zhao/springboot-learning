package com.xp.model;

import java.beans.ConstructorProperties;

/**
 * Created by xp-zhao on 2018/5/23.
 */
public class BaseResp
{
	protected String code;
	protected String info;
	public static final String CODE_SUCCESS = "000000";
	public static final String INFO_SUCCESS = "success";
	public static final BaseResp SUCCESS = new BaseResp("000000", "success");

	public static BaseResp systemError(String info) {
		return new BaseResp("199999", info);
	}

	public String getCode() {
		return this.code;
	}

	public String getInfo() {
		return this.info;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BaseResp)) {
			return false;
		} else {
			BaseResp other = (BaseResp)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$code = this.getCode();
				Object other$code = other.getCode();
				if (this$code == null) {
					if (other$code != null) {
						return false;
					}
				} else if (!this$code.equals(other$code)) {
					return false;
				}

				Object this$info = this.getInfo();
				Object other$info = other.getInfo();
				if (this$info == null) {
					if (other$info != null) {
						return false;
					}
				} else if (!this$info.equals(other$info)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof BaseResp;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $code = this.getCode();
		result = result * 59 + ($code == null ? 43 : $code.hashCode());
		Object $info = this.getInfo();
		result = result * 59 + ($info == null ? 43 : $info.hashCode());
		return result;
	}

	public String toString() {
		return "BaseResp(code=" + this.getCode() + ", info=" + this.getInfo() + ")";
	}

	@ConstructorProperties ({"code", "info"})
	public BaseResp(String code, String info) {
		this.code = code;
		this.info = info;
	}

	public BaseResp() {
	}
}
