package com.zlx.es.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.DateFormat;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * ES请求记录文档
 */

@Data
//@Document(indexName = "#{@indexPrefix}" + "request_record", type = "_doc")
//@Document(indexName = "ttt", type = "_doc")
@AllArgsConstructor
@NoArgsConstructor
public class RequestRecord {

    /**
     * 流水号
     */
    private Long serialId;

    /**
     * 虚指令集编码
     */
    private String servCode;

    /**
     * 虚指令集名称
     */
    private String servName;

    /**
     * 专业
     */
    private String domain;

    /**
     * 网元类型 设备类型
     */
    private String neType;

    /**
     * 上线时间 虚指令集创建时间
     */
    private Date onlineTime;

    /**
     * 版本号
     */
    private String version;

    /**
     * 虚指令集描述
     */
    private String servDesc;

    /**
     * 请求来源
     */
    private String reqFrom;

    /**
     * 区域
     */
    private String area;

    /**
     * 业务号码 userNo
     */
    private String busiNum;

    /**
     * 接收时间 reqDate，框架接收请求时间
     */
    private Date acceptTime;

    /**
     * 响应时间 框架调用完成时，返回时间
     */
    private Date responseTime;

    /**
     * 调用时间，开始调用虚指令集的时间
     */
    private Date invokeTime;

    /**
     * 执行耗时 虚指令集执行完成耗时 毫秒
     */
    private Long execTime;

    /**
     * 调用状态码，调用服务
     * 200
     */
    private String invokeStatus;

    /**
     * 调用描述，调用服务
     * 成功
     * 失败
     */
    private String invokeDesc;

    /**
     * 执行结果码，框架返回给调用方的结果
     */
    private String execStatus;

    /**
     * 执行描述
     */
    private String execDesc;

    /**
     * 调用服务
     */
    private String logNeName;

    /**
     * 控制接口
     */
    private String phyNeName;

    /**
     * 设备ID,控制接口
     */
    private String phyNeId;

    /**
     * 网元厂家
     */
    private String vendor;

    /**
     * 业务工单号
     */
    private String swsId;

    /**
     * 操作类别
     * 查询  0
     * 修改 1
     */
    private Integer operateType;

    /**
     * 控制服务唯一标识ID
     */
    private String controlId;

    /**
     * 控制服务实列ID
     */
    private String controlInstId;

    /**
     * 操作人工号
     */
    private String omanual;

    /**
     * 服务耗时
     */
    private Long servExecTime;

    /**
     * 上游请求流水
     */
    private String msgId;

    /**
     * 异常原因
     */
    private String faultReason;
}
