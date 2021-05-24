package cn.edu.hbpu.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程编号的id
     */
    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    /**
     * 课程的名称或者分类
     */
    private String courseName;

    /**
     * 所属分类的下一个id，没有用0表示
     */
    private Integer coursePrev;

    /**
     * 添加该分类的负责人
     */
    private String courseAuth;

    /**
     * 添加该分类的时间
     */
    private String courseCtime;


    private String courseImage;
}
