package com.zlx.drools.bean;


import lombok.Data;

import java.util.List;

@Data
public class UserBean {

    private long id;

    private String username;

    private List<BookBean> bookBeanList;

}
