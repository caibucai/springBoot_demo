package com.example.demo.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class QueryParam {

    /**
     * 开始时间
     */
    @NotBlank(message = "开始时间不能为空")
    @Pattern(regexp = "\\d{4,4}-\\d{2,2}", message = "开始时间格式必须为yyyy-MM")
    private String kssj;

    /**
     * 结束时间
     */
    @NotBlank(message = "结束时间不能为空")
    @Pattern(regexp = "\\d{4,4}-\\d{2,2}", message = "结束时间格式必须为yyyy-MM")
    private String jssj;

    /**
     * 地区的code
     */
    @NotEmpty(message = "地区不能为空")
    private String[] code;
}
