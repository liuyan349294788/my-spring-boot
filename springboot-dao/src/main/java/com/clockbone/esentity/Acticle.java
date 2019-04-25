package com.clockbone.esentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "acticle1_test",type = "acticle_record1_test", shards = 1, replicas = 0)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Acticle {

    @Id
    private Long id;
    @Field(type = FieldType.text)
    private String category; //姓名
    @Field(type = FieldType.text)
    private String range; //姓名

    @Field(type = FieldType.text)
    private String mode; //姓名

    @Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private String start_time; //姓名
    @Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private String end_time; //姓名

    @Field(type = FieldType.text,analyzer = "ik_smart")
    private String title; //姓名

    @Field(type = FieldType.text,analyzer = "ik_smart")
    private String content; //姓名


}
