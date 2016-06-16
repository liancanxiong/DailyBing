package com.brilliantbear.dailybing.bean;

import java.util.List;

/**
 * Created by Bear on 2016-6-14.
 */
public class ResultBean {

    /**
     * loading : 正在加载...
     * previous : 上一个图像
     * next : 下一个图像
     * walle : 此图片不能下载用作壁纸。
     * walls : 下载今日美图。仅限用作桌面壁纸。
     */

    private TooltipsBean tooltips;
    /**
     * startdate : 20160613
     * fullstartdate : 201606131600
     * enddate : 20160614
     * url : http://s.cn.bing.net/az/hprichbg/rb/BomboHeadland_ZH-CN9339065341_1920x1080.jpg
     * urlbase : /az/hprichbg/rb/BomboHeadland_ZH-CN9339065341
     * copyright : 凯马Bombo Headland，新南威尔士州，澳大利亚 (© Attakorn Bk/Getty Images)
     * copyrightlink : http://www.bing.com/search?q=%E5%87%AF%E9%A9%AC&form=hpcapt&mkt=zh-cn
     * wp : true
     * hsh : 8b50b348242b793c63102d33c2ca5eb7
     * drk : 1
     * top : 1
     * bot : 1
     * hs : []
     * msg : [{"title":"今日图片故事","link":"http://www.bing.com/search?q=%E5%87%AF%E9%A9%AC&FORM=pgbar1&mkt=zh-cn","text":"凯马"}]
     */

    private List<ImagesBean> images;

    public TooltipsBean getTooltips() {
        return tooltips;
    }

    public void setTooltips(TooltipsBean tooltips) {
        this.tooltips = tooltips;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class TooltipsBean {
        private String loading;
        private String previous;
        private String next;
        private String walle;
        private String walls;

        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getWalle() {
            return walle;
        }

        public void setWalle(String walle) {
            this.walle = walle;
        }

        public String getWalls() {
            return walls;
        }

        public void setWalls(String walls) {
            this.walls = walls;
        }
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "images=" + images +
                '}';
    }
}
