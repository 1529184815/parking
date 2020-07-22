package com.hx.bean;

import com.hx.utils.DateUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Data
public class Temp {
  @Id
  private String tempId;
  private String cardId;
  private String carNum;
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date entryTime;
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date outTime;
  private Integer tempMoney;
  private Integer state;
  @Transient
  private Integer money=3;

  public String getEntryTime() {
    if(entryTime!=null){
      return DateUtil.timeStrap2String(entryTime,"yyyy-MM-dd HH:mm:ss");
    }
    return null;
  }

  public String getOutTime() {
    if(outTime!=null){
      return DateUtil.timeStrap2String(outTime,"yyyy-MM-dd HH:mm:ss");
    }
    return null;
  }
}
