package com.authorizationserver.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseResponse {
  @JsonProperty("errorCode")
  private String errorCode;

  @JsonProperty("message")
  private String message;

  @JsonProperty("statusCode")
  private String statusCode;

  @JsonProperty("timestamp")
  private String timestamp;

  @JsonProperty("returnMessage")
  private String returnMessage;

  @JsonProperty("totalCount")
  private Integer totalCount;
}
