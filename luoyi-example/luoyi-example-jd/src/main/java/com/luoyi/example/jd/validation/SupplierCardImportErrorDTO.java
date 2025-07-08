package com.luoyi.example.jd.validation;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SupplierCardImportErrorDTO extends SupplierCardImportDTO implements Serializable {

    private static final long serialVersionUID = -4813161117116868051L;

    private String errorMsg;
}
