package com.vikash.cockroachdb.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@ToString
public class Product {

    @Id
    private UUID id;   
    private String tid;
    private String aid;
    private String aidType;
    private String faultType;
    private String severity;
    private String neModel;
	private String conditionDesc;
	private String condition;
	private String location;
	private String direction;
	private String serviceEffected;
	private String conditionEffect;
	private String aidDetail;
	private String rawMsg;

}