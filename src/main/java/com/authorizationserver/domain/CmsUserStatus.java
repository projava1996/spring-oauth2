package com.authorizationserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cmsuserstatus")
public class CmsUserStatus implements Serializable {

	private static final long serialVersionUID = -7477098848099051L;

	@Id
	@Column(name = "code")
	private String code;

	@Column(name = "name", nullable = true)
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
