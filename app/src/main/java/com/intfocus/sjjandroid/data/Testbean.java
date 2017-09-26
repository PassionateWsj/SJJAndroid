package com.intfocus.sjjandroid.data;

import java.util.List;

/**
 * Created by liuruilin on 2017/8/31.
 */

public class Testbean {

    /**
     * msg : 请求数据成功!
     * code : 0
     * data : [{"password":"123123","updated_at":"2017-09-17 23:39:09","name":"hzl","mobile":"15278618270","description":"asdasd","created_at":"2017-09-17 23:39:09","uuid":"7a0d5b14-dbc6-4f3f-b277-e72b9d4d5d56","profession_ids":null}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * password : 123123
         * updated_at : 2017-09-17 23:39:09
         * name : hzl
         * mobile : 15278618270
         * description : asdasd
         * created_at : 2017-09-17 23:39:09
         * uuid : 7a0d5b14-dbc6-4f3f-b277-e72b9d4d5d56
         * profession_ids : null
         */

        private String password;
        private String updated_at;
        private String name;
        private String mobile;
        private String description;
        private String created_at;
        private String uuid;
        private Object profession_ids;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public Object getProfession_ids() {
            return profession_ids;
        }

        public void setProfession_ids(Object profession_ids) {
            this.profession_ids = profession_ids;
        }
    }
}
