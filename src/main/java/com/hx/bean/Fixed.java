package com.hx.bean;

import com.hx.utils.DateUtil;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Data
public class Fixed {
  @Id
  private String fixedId;
  private String cardId;
  private Date startTime;
  private Date endTime;
  private Integer money;
  @Transient
  private Card card;

  public String getStartTime() {
    if(startTime!=null){
      return DateUtil.timeStrap2String(startTime,"yyyy-MM-dd");
    }
    return null;
  }

  public String getEndTime() {
    if(endTime!=null) {
      return DateUtil.timeStrap2String(endTime, "yyyy-MM-dd");
    }
    return null;
  }
}
