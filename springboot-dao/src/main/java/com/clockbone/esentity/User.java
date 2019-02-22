package com.clockbone.esentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "account",type = "user", shards = 1, replicas = 0)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    @Field(type = FieldType.text, analyzer = "ik_max_word")
    private String name; //姓名
    @Field(type = FieldType.keyword)
    private String job;// 职称
    @Field(type = FieldType.keyword)
    private String hob; // 爱好
    @Field(type = FieldType.Double)
    private Double age; // 年龄
    @Field(index = false, type = FieldType.keyword)
    private String images;
}
