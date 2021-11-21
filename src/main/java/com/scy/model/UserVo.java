package com.scy.model;

import lombok.Data;

@Data
public class UserVo {
    private Integer id;
    private String username;

    private String realname;

    private String sex;

    private String email;

    private String jobTitle;

    private Integer todayUp;
    private Integer todayDown;

    private Integer tomUp;
    private Integer tomDown;


}
