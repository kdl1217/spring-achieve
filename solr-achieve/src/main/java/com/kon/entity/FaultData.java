package com.kon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * note
 *
 * @author kon, created on 2021/12/2T15:23.
 * @version 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@SolrDocument(collection = "faultData")
public class FaultData {

    @Field
    private String id;

    @Field("fault_data_vin")
    private String faultDataVin;

    @Field("fault_data_faultCode")
    private String faultDataFaultCode;

    @Field("fault_data_faultRemark")
    private String faultDataFaultRemark;

    @Field("fault_data_faultValue")
    private String faultDataFaultValue;

    @Field("fault_data_faultContent")
    private String faultDataFaultContent;

    @Field("fault_data_receiveTime")
    private String faultDataReceiveTime;

    @Field("fault_data_longitude")
    private String faultDataLongitude;

    @Field("fault_data_orgId")
    private String faultDataOrgId;

    @Field("fault_data_detectionTime")
    private String faultDataDetectionTime;

    @Field("fault_data_latitude")
    private String faultDataLatitude;

    @Field("fault_data_saleStatus")
    private String faultDataSaleStatus;

    @Field("fault_data_visibleLevel")
    private String faultDataVisibleLevel;
}

