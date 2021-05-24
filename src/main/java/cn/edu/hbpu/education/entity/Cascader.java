package cn.edu.hbpu.education.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class Cascader implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String,Object> province_list;


    private Map<String,Object> city_list;

    private Map<String,Object> county_list;


}
