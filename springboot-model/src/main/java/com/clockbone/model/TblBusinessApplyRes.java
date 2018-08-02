package com.clockbone.model;

import lombok.Data;

import java.util.Date;

@Data
public class TblBusinessApplyRes extends TblBusinessApply{
  private String taskId;
  private String currentNode;
  private String applyUser;
  private String currentHandle;
  private Long userId;
  private String defineProcessId;


}