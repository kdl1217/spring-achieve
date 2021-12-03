package com.kon.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * ES索引对象
 *
 * @see Document: 应用于类级别，表示该类是映射到数据库的候选。最重要的属性是：
 *      indexName：存储该实体的索引名称。这可以包含一个 SpEL 模板表达式，如 "log-#{T(java.time.LocalDate).now().toString()}"
 *      createIndex: 标记是否在存储库引导时创建索引。默认值为true。请参阅使用相应映射自动创建索引
 *      versionType: 版本管理的配置。默认值为EXTERNAL。
 *
 * @see Id：应用于字段级别以标记用于标识目的的字段。
 *
 * @see org.springframework.data.annotation.Transient：默认情况下，所有字段在存储或检索时都映射到文档，此注释不包括该字段。
 *
 * @see org.springframework.data.annotation.PersistenceConstructor: 标记给定的构造函数 - 即使是受包保护的构造函数 - 在从数据库实例化对象时使用。构造函数参数按名称映射到检索到的文档中的键值。
 *
 * @see Field：应用于字段级别，定义字段的属性，大部分属性映射到各自的Elasticsearch Mapping定义（以下列表不完整，完整参考请查看注解Javadoc）：
 *      name: 字段名称，因为它将在 Elasticsearch 文档中表示，如果未设置，则使用 Java 字段名称。
 *      type：字段类型，可以是Text、Keyword、Long、Integer、Short、Byte、Double、Float、Half_Float、Scaled_Float、Date、Date_Nanos、Boolean、
 *           Binary、Integer_Range、Float_Range、Long_Range、Double_Range、Date_Range、Ip_Range、Object之一, 嵌套, Ip, TokenCount, Percolator,
 *           Flattened, Search_As_You_Type。请参阅Elasticsearch 映射类型。如果未指定字段类型，则默认为FieldType.Auto。这意味着，没有为该属性写入映射条目，
 *           并且 Elasticsearch 将在存储该属性的第一个数据时动态添加映射条目（查看 Elasticsearch 文档了解动态映射规则）。
 *      format：一种或多种内置日期格式，请参阅下一节日期格式映射。
 *      pattern：一种或多种自定义日期格式，请参阅下一节日期格式映射。
 *      store: 标志是否应将原始字段值存储在 Elasticsearch 中，默认值为false。
 *      analyzer, searchAnalyzer,normalizer用于指定自定义分析器和规范器。
 *
 * @see org.springframework.data.elasticsearch.annotations.GeoPointField: 将字段标记为geo_point数据类型。如果字段是GeoPoint类的实例，则可以省略。
 *
 * @author com.kon, created on 2021/12/2T11:47.
 * @version 1.0.0-SNAPSHOT
 */
@Data
@Document(indexName = "operator_log", createIndex = true)
public class OperatorLog {

    @Id
    private String id;

    @Field(value = "describe", type = FieldType.Text)
    private String describe;

    @Field(value = "create_time", type = FieldType.Date, format= DateFormat.date_hour_minute_second_millis)
    private Date time;
}
