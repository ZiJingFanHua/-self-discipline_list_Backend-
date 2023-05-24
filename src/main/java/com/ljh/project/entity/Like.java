package com.ljh.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zijing
 * @since 2023-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("likes")
@ApiModel(value="Like对象", description="")
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("account_id")
    private String accountId;

    @TableField("subject_id")
    private Long subjectId;

    @TableField("comment_id")
    private Long commentId;


}
