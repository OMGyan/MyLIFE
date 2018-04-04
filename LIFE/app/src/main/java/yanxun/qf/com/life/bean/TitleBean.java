package yanxun.qf.com.life.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class TitleBean {


    /**
     * code : 200
     * data : {"time_axis":[{"id":1,"name":"最新"},{"id":6,"name":"热销榜"},{"id":3,"name":"逛店"},{"id":2,"name":"限时购"},{"id":7,"name":"精选"},{"id":5,"name":"歪果志"},{"id":4,"name":"问答"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<TimeAxisBean> time_axis;

        public List<TimeAxisBean> getTime_axis() {
            return time_axis;
        }

        public void setTime_axis(List<TimeAxisBean> time_axis) {
            this.time_axis = time_axis;
        }

        public static class TimeAxisBean {
            /**
             * id : 1
             * name : 最新
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
