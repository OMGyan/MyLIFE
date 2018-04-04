package yanxun.qf.com.life.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class DiscoveryChannelBean {
    /**
     * code : 200
     * data : {"channel":[{"cell_type":"life_sir_fav_list","life_sir_fav_list":{"cover_image_url":"http://7xr1wo.com1.z0.glb.clouddn.com/life_sir_fav_list.png","target_url":"http://api.mglife.me/v1/users/lifesir/favorite_lists"}},{"cell_type":"talent_channel","talent_channel":[{"author_icon":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/0oyfzovmb.jpg-w180","author_id":166190,"author_name":"PChouse家居杂志","channel_icon":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/ty9d6tq26.png","cover_image_url":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/d7ezipu9q.png-w640","id":21,"introduction":"面对梦想中的家，有时你只是缺少一些灵感。一张图片或一段文字，都能带给你不同的家居设计启示。PChouse，一本提供家居生活灵感的移动杂志。","items_count":73,"name":"PChouse家居杂志","slogan":"实用的家居灵感手册","type":1}]},{"cell_type":"to_be_talent","to_be_talent":{"cover_image_url":"http://7xr1wo.com1.z0.glb.clouddn.com/to_be_talent.png","target_url":"mglife:///page?type=url&url=http://www.mglife.me/vm?share=true"}},{"cell_type":"life_channel","life_channel":[{"author_id":"","author_name":"","channel_icon":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/bc3ejhsfp.png","cover_image_url":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160726/91fe5t16t.png-w640","id":17,"introduction":"一家专注于\u201c家\u201d的研究所，每期一风，和LIFE一起研究一个家，探索它的细微末节，触碰它的每一寸美好。","items_count":71,"name":"有家研究所","slogan":"一家专注于\u201c家\u201d的研究所","type":0}]}],"paging":{"next_url":"https://api.mglife.me/v2/special_channel?limit=20&offset=20"}}
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
        /**
         * channel : [{"cell_type":"life_sir_fav_list","life_sir_fav_list":{"cover_image_url":"http://7xr1wo.com1.z0.glb.clouddn.com/life_sir_fav_list.png","target_url":"http://api.mglife.me/v1/users/lifesir/favorite_lists"}},{"cell_type":"talent_channel","talent_channel":[{"author_icon":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/0oyfzovmb.jpg-w180","author_id":166190,"author_name":"PChouse家居杂志","channel_icon":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/ty9d6tq26.png","cover_image_url":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/d7ezipu9q.png-w640","id":21,"introduction":"面对梦想中的家，有时你只是缺少一些灵感。一张图片或一段文字，都能带给你不同的家居设计启示。PChouse，一本提供家居生活灵感的移动杂志。","items_count":73,"name":"PChouse家居杂志","slogan":"实用的家居灵感手册","type":1}]},{"cell_type":"to_be_talent","to_be_talent":{"cover_image_url":"http://7xr1wo.com1.z0.glb.clouddn.com/to_be_talent.png","target_url":"mglife:///page?type=url&url=http://www.mglife.me/vm?share=true"}},{"cell_type":"life_channel","life_channel":[{"author_id":"","author_name":"","channel_icon":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/bc3ejhsfp.png","cover_image_url":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160726/91fe5t16t.png-w640","id":17,"introduction":"一家专注于\u201c家\u201d的研究所，每期一风，和LIFE一起研究一个家，探索它的细微末节，触碰它的每一寸美好。","items_count":71,"name":"有家研究所","slogan":"一家专注于\u201c家\u201d的研究所","type":0}]}]
         * paging : {"next_url":"https://api.mglife.me/v2/special_channel?limit=20&offset=20"}
         */

        private PagingBean paging;
        private List<ChannelBean> channel;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<ChannelBean> getChannel() {
            return channel;
        }

        public void setChannel(List<ChannelBean> channel) {
            this.channel = channel;
        }

        public static class PagingBean {
            /**
             * next_url : https://api.mglife.me/v2/special_channel?limit=20&offset=20
             */

            private String next_url;

            public String getNext_url() {
                return next_url;
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }
        }

        public static class ChannelBean {
            /**
             * cell_type : life_sir_fav_list
             * life_sir_fav_list : {"cover_image_url":"http://7xr1wo.com1.z0.glb.clouddn.com/life_sir_fav_list.png","target_url":"http://api.mglife.me/v1/users/lifesir/favorite_lists"}
             * talent_channel : [{"author_icon":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/0oyfzovmb.jpg-w180","author_id":166190,"author_name":"PChouse家居杂志","channel_icon":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/ty9d6tq26.png","cover_image_url":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/d7ezipu9q.png-w640","id":21,"introduction":"面对梦想中的家，有时你只是缺少一些灵感。一张图片或一段文字，都能带给你不同的家居设计启示。PChouse，一本提供家居生活灵感的移动杂志。","items_count":73,"name":"PChouse家居杂志","slogan":"实用的家居灵感手册","type":1}]
             * to_be_talent : {"cover_image_url":"http://7xr1wo.com1.z0.glb.clouddn.com/to_be_talent.png","target_url":"mglife:///page?type=url&url=http://www.mglife.me/vm?share=true"}
             * life_channel : [{"author_id":"","author_name":"","channel_icon":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/bc3ejhsfp.png","cover_image_url":"http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160726/91fe5t16t.png-w640","id":17,"introduction":"一家专注于\u201c家\u201d的研究所，每期一风，和LIFE一起研究一个家，探索它的细微末节，触碰它的每一寸美好。","items_count":71,"name":"有家研究所","slogan":"一家专注于\u201c家\u201d的研究所","type":0}]
             */

            private String cell_type;
            private LifeSirFavListBean life_sir_fav_list;
            private ToBeTalentBean to_be_talent;
            private List<TalentChannelBean> talent_channel;
            private List<LifeChannelBean> life_channel;

            public String getCell_type() {
                return cell_type;
            }

            public void setCell_type(String cell_type) {
                this.cell_type = cell_type;
            }

            public LifeSirFavListBean getLife_sir_fav_list() {
                return life_sir_fav_list;
            }

            public void setLife_sir_fav_list(LifeSirFavListBean life_sir_fav_list) {
                this.life_sir_fav_list = life_sir_fav_list;
            }

            public ToBeTalentBean getTo_be_talent() {
                return to_be_talent;
            }

            public void setTo_be_talent(ToBeTalentBean to_be_talent) {
                this.to_be_talent = to_be_talent;
            }

            public List<TalentChannelBean> getTalent_channel() {
                return talent_channel;
            }

            public void setTalent_channel(List<TalentChannelBean> talent_channel) {
                this.talent_channel = talent_channel;
            }

            public List<LifeChannelBean> getLife_channel() {
                return life_channel;
            }

            public void setLife_channel(List<LifeChannelBean> life_channel) {
                this.life_channel = life_channel;
            }

            public static class LifeSirFavListBean {
                /**
                 * cover_image_url : http://7xr1wo.com1.z0.glb.clouddn.com/life_sir_fav_list.png
                 * target_url : http://api.mglife.me/v1/users/lifesir/favorite_lists
                 */

                private String cover_image_url;
                private String target_url;

                public String getCover_image_url() {
                    return cover_image_url;
                }

                public void setCover_image_url(String cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public String getTarget_url() {
                    return target_url;
                }

                public void setTarget_url(String target_url) {
                    this.target_url = target_url;
                }
            }

            public static class ToBeTalentBean {
                /**
                 * cover_image_url : http://7xr1wo.com1.z0.glb.clouddn.com/to_be_talent.png
                 * target_url : mglife:///page?type=url&url=http://www.mglife.me/vm?share=true
                 */

                private String cover_image_url;
                private String target_url;

                public String getCover_image_url() {
                    return cover_image_url;
                }

                public void setCover_image_url(String cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public String getTarget_url() {
                    return target_url;
                }

                public void setTarget_url(String target_url) {
                    this.target_url = target_url;
                }
            }

            public static class TalentChannelBean {
                /**
                 * author_icon : http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/0oyfzovmb.jpg-w180
                 * author_id : 166190
                 * author_name : PChouse家居杂志
                 * channel_icon : http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/ty9d6tq26.png
                 * cover_image_url : http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/d7ezipu9q.png-w640
                 * id : 21
                 * introduction : 面对梦想中的家，有时你只是缺少一些灵感。一张图片或一段文字，都能带给你不同的家居设计启示。PChouse，一本提供家居生活灵感的移动杂志。
                 * items_count : 73
                 * name : PChouse家居杂志
                 * slogan : 实用的家居灵感手册
                 * type : 1
                 */

                private String author_icon;
                private int author_id;
                private String author_name;
                private String channel_icon;
                private String cover_image_url;
                private int id;
                private String introduction;
                private int items_count;
                private String name;
                private String slogan;
                private int type;

                public String getAuthor_icon() {
                    return author_icon;
                }

                public void setAuthor_icon(String author_icon) {
                    this.author_icon = author_icon;
                }

                public int getAuthor_id() {
                    return author_id;
                }

                public void setAuthor_id(int author_id) {
                    this.author_id = author_id;
                }

                public String getAuthor_name() {
                    return author_name;
                }

                public void setAuthor_name(String author_name) {
                    this.author_name = author_name;
                }

                public String getChannel_icon() {
                    return channel_icon;
                }

                public void setChannel_icon(String channel_icon) {
                    this.channel_icon = channel_icon;
                }

                public String getCover_image_url() {
                    return cover_image_url;
                }

                public void setCover_image_url(String cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIntroduction() {
                    return introduction;
                }

                public void setIntroduction(String introduction) {
                    this.introduction = introduction;
                }

                public int getItems_count() {
                    return items_count;
                }

                public void setItems_count(int items_count) {
                    this.items_count = items_count;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSlogan() {
                    return slogan;
                }

                public void setSlogan(String slogan) {
                    this.slogan = slogan;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }

            public static class LifeChannelBean {
                /**
                 * author_id :
                 * author_name :
                 * channel_icon : http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160725/bc3ejhsfp.png
                 * cover_image_url : http://7xr1wo.com2.z0.glb.qiniucdn.com/image/160726/91fe5t16t.png-w640
                 * id : 17
                 * introduction : 一家专注于“家”的研究所，每期一风，和LIFE一起研究一个家，探索它的细微末节，触碰它的每一寸美好。
                 * items_count : 71
                 * name : 有家研究所
                 * slogan : 一家专注于“家”的研究所
                 * type : 0
                 */

                private String author_id;
                private String author_name;
                private String channel_icon;
                private String cover_image_url;
                private int id;
                private String introduction;
                private int items_count;
                private String name;
                private String slogan;
                private int type;

                public String getAuthor_id() {
                    return author_id;
                }

                public void setAuthor_id(String author_id) {
                    this.author_id = author_id;
                }

                public String getAuthor_name() {
                    return author_name;
                }

                public void setAuthor_name(String author_name) {
                    this.author_name = author_name;
                }

                public String getChannel_icon() {
                    return channel_icon;
                }

                public void setChannel_icon(String channel_icon) {
                    this.channel_icon = channel_icon;
                }

                public String getCover_image_url() {
                    return cover_image_url;
                }

                public void setCover_image_url(String cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIntroduction() {
                    return introduction;
                }

                public void setIntroduction(String introduction) {
                    this.introduction = introduction;
                }

                public int getItems_count() {
                    return items_count;
                }

                public void setItems_count(int items_count) {
                    this.items_count = items_count;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSlogan() {
                    return slogan;
                }

                public void setSlogan(String slogan) {
                    this.slogan = slogan;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }
        }
    }
}

