package com.clockbone.model;

import lombok.Data;

import java.util.Date;

@Data
public class TblBusinessApplyRes {
  private String taskId;
  private String currentNode;
  private String applyUser;
  private String currentHandle;
  private String processDefineId;
  private Long userId;


}