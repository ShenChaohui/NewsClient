package com.geniuses.newsclient.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sch on 2018/2/7.
 */

public class WeatherInfo {
    /**
     * aqi : {"city":{"aqi":"32","qlty":"优","pm25":"8","pm10":"31","no2":"9","so2":"5","co":"0.267","o3":"83"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.90498734","lon":"116.4052887","update":{"loc":"2018-02-07 13:49","utc":"2018-02-07 05:49"}}
     * daily_forecast : [{"astro":{"mr":"00:01","ms":"11:16","sr":"07:16","ss":"17:41"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2018-02-07","hum":"21","pcpn":"0.0","pop":"0","pres":"1028","tmp":{"max":"2","min":"-9"},"uv":"2","vis":"20","wind":{"deg":"4","dir":"北风","sc":"4-5","spd":"17"}},{"astro":{"mr":"01:01","ms":"11:49","sr":"07:15","ss":"17:42"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2018-02-08","hum":"20","pcpn":"0.0","pop":"0","pres":"1022","tmp":{"max":"3","min":"-7"},"uv":"2","vis":"20","wind":{"deg":"182","dir":"南风","sc":"3-4","spd":"17"}},{"astro":{"mr":"01:59","ms":"12:24","sr":"07:14","ss":"17:44"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2018-02-09","hum":"16","pcpn":"0.0","pop":"0","pres":"1023","tmp":{"max":"5","min":"-9"},"uv":"2","vis":"20","wind":{"deg":"337","dir":"西北风","sc":"4-5","spd":"22"}},{"astro":{"mr":"02:54","ms":"13:03","sr":"07:12","ss":"17:45"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-02-10","hum":"15","pcpn":"0.0","pop":"0","pres":"1027","tmp":{"max":"0","min":"-10"},"uv":"2","vis":"20","wind":{"deg":"344","dir":"西北风","sc":"3-4","spd":"13"}},{"astro":{"mr":"03:48","ms":"13:46","sr":"07:11","ss":"17:46"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-02-11","hum":"15","pcpn":"0.0","pop":"0","pres":"1026","tmp":{"max":"0","min":"-8"},"uv":"2","vis":"20","wind":{"deg":"285","dir":"西北风","sc":"3-4","spd":"13"}},{"astro":{"mr":"04:37","ms":"14:33","sr":"07:10","ss":"17:47"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-02-12","hum":"20","pcpn":"0.0","pop":"0","pres":"1024","tmp":{"max":"4","min":"-6"},"uv":"2","vis":"20","wind":{"deg":"226","dir":"西南风","sc":"3-4","spd":"12"}},{"astro":{"mr":"05:24","ms":"15:25","sr":"07:09","ss":"17:48"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2018-02-13","hum":"23","pcpn":"0.0","pop":"0","pres":"1020","tmp":{"max":"7","min":"-5"},"uv":"2","vis":"20","wind":{"deg":"243","dir":"西南风","sc":"微风","spd":"4"}}]
     * hourly_forecast : [{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-07 16:00","hum":"14","pop":"0","pres":"1029","tmp":"1","wind":{"deg":"115","dir":"东南风","sc":"微风","spd":"14"}},{"cond":{"code":"100","txt":"晴"},"date":"2018-02-07 19:00","hum":"17","pop":"0","pres":"1030","tmp":"0","wind":{"deg":"126","dir":"东南风","sc":"微风","spd":"7"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-07 22:00","hum":"19","pop":"0","pres":"1031","tmp":"-4","wind":{"deg":"163","dir":"东南风","sc":"微风","spd":"6"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-08 01:00","hum":"20","pop":"0","pres":"1030","tmp":"-6","wind":{"deg":"220","dir":"西南风","sc":"微风","spd":"6"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-08 04:00","hum":"22","pop":"0","pres":"1027","tmp":"-8","wind":{"deg":"217","dir":"西南风","sc":"微风","spd":"5"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-08 07:00","hum":"23","pop":"0","pres":"1027","tmp":"-8","wind":{"deg":"209","dir":"西南风","sc":"微风","spd":"4"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-08 10:00","hum":"19","pop":"0","pres":"1025","tmp":"-7","wind":{"deg":"193","dir":"西南风","sc":"微风","spd":"6"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2018-02-08 13:00","hum":"15","pop":"0","pres":"1021","tmp":"0","wind":{"deg":"185","dir":"南风","sc":"微风","spd":"8"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"-5","hum":"16","pcpn":"0.0","pres":"1028","tmp":"0","vis":"10","wind":{"deg":"329","dir":"西北风","sc":"3-4","spd":"13"}}
     * status : ok
     * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"comf":{"brf":"较不舒适","txt":"白天天气较凉，且风力较强，您会感觉偏冷，不很舒适，请注意添加衣物，以防感冒。"},"cw":{"brf":"较不宜","txt":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"},"drsg":{"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"},"flu":{"brf":"易发","txt":"天冷风大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。"},"sport":{"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。"},"trav":{"brf":"一般","txt":"天空状况还是比较好的，但温度比较低，且风稍大，会让人感觉有点冷。外出请备上防风保暖衣物。"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
     */

    private AqiBean aqi;
    private BasicBean basic;
    private NowBean now;
    private String status;
    private SuggestionBean suggestion;
    private List<DailyForecastBean> daily_forecast;
    private List<HourlyForecastBean> hourly_forecast;

    public AqiBean getAqi() {
        return aqi;
    }

    public void setAqi(AqiBean aqi) {
        this.aqi = aqi;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    public List<DailyForecastBean> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public List<HourlyForecastBean> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public static class AqiBean {
        /**
         * city : {"aqi":"32","qlty":"优","pm25":"8","pm10":"31","no2":"9","so2":"5","co":"0.267","o3":"83"}
         */

        private CityBean city;

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * aqi : 32
             * qlty : 优
             * pm25 : 8
             * pm10 : 31
             * no2 : 9
             * so2 : 5
             * co : 0.267
             * o3 : 83
             */

            private String aqi;
            private String qlty;
            private String pm25;
            private String pm10;
            private String no2;
            private String so2;
            private String co;
            private String o3;

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getQlty() {
                return qlty;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }
        }
    }

    public static class BasicBean {
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.90498734
         * lon : 116.4052887
         * update : {"loc":"2018-02-07 13:49","utc":"2018-02-07 05:49"}
         */

        private String city;
        private String cnty;
        private String id;
        private String lat;
        private String lon;
        private UpdateBean update;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public static class UpdateBean {
            /**
             * loc : 2018-02-07 13:49
             * utc : 2018-02-07 05:49
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }
    }

    public static class NowBean {
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : -5
         * hum : 16
         * pcpn : 0.0
         * pres : 1028
         * tmp : 0
         * vis : 10
         * wind : {"deg":"329","dir":"西北风","sc":"3-4","spd":"13"}
         */

        private CondBean cond;
        private String fl;
        private String hum;
        private String pcpn;
        private String pres;
        private String tmp;
        private String vis;
        private WindBean wind;

        public CondBean getCond() {
            return cond;
        }

        public void setCond(CondBean cond) {
            this.cond = cond;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public static class CondBean {
            /**
             * code : 101
             * txt : 多云
             */

            private String code;
            private String txt;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class WindBean {
            /**
             * deg : 329
             * dir : 西北风
             * sc : 3-4
             * spd : 13
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }



    public static class SuggestionBean implements Serializable{
        public static class Entity implements Serializable {
            /**
             * 简介
             */
            @SerializedName("brf")
            public String brf;
            /**
             * 详情
             */
            @SerializedName("txt")
            public String txt;
        }
        /**
         * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
         * comf : {"brf":"较不舒适","txt":"白天天气较凉，且风力较强，您会感觉偏冷，不很舒适，请注意添加衣物，以防感冒。"}
         * cw : {"brf":"较不宜","txt":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"}
         * drsg : {"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"}
         * flu : {"brf":"易发","txt":"天冷风大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。"}
         * sport : {"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。"}
         * trav : {"brf":"一般","txt":"天空状况还是比较好的，但温度比较低，且风稍大，会让人感觉有点冷。外出请备上防风保暖衣物。"}
         * uv : {"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
         */

        private Entity air;
        private Entity comf;
        private Entity cw;
        private Entity drsg;
        private Entity flu;
        private Entity sport;
        private Entity trav;
        private Entity uv;

        public Entity getAir() {
            return air;
        }

        public void setAir(Entity air) {
            this.air = air;
        }

        public Entity getComf() {
            return comf;
        }

        public void setComf(Entity comf) {
            this.comf = comf;
        }

        public Entity getCw() {
            return cw;
        }

        public void setCw(Entity cw) {
            this.cw = cw;
        }

        public Entity getDrsg() {
            return drsg;
        }

        public void setDrsg(Entity drsg) {
            this.drsg = drsg;
        }

        public Entity getFlu() {
            return flu;
        }

        public void setFlu(Entity flu) {
            this.flu = flu;
        }

        public Entity getSport() {
            return sport;
        }

        public void setSport(Entity sport) {
            this.sport = sport;
        }

        public Entity getTrav() {
            return trav;
        }

        public void setTrav(Entity trav) {
            this.trav = trav;
        }

        public Entity getUv() {
            return uv;
        }

        public void setUv(Entity uv) {
            this.uv = uv;
        }

        public static class AirBean {
            /**
             * brf : 中
             * txt : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class ComfBean {
            /**
             * brf : 较不舒适
             * txt : 白天天气较凉，且风力较强，您会感觉偏冷，不很舒适，请注意添加衣物，以防感冒。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class CwBean {
            /**
             * brf : 较不宜
             * txt : 较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class DrsgBean {
            /**
             * brf : 冷
             * txt : 天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class FluBean {
            /**
             * brf : 易发
             * txt : 天冷风大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class SportBean {
            /**
             * brf : 较不宜
             * txt : 天气较好，但考虑天气寒冷，风力较强，推荐您进行室内运动，若户外运动请注意保暖并做好准备活动。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class TravBean {
            /**
             * brf : 一般
             * txt : 天空状况还是比较好的，但温度比较低，且风稍大，会让人感觉有点冷。外出请备上防风保暖衣物。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class UvBean {
            /**
             * brf : 最弱
             * txt : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }

    public static class DailyForecastBean {
        /**
         * astro : {"mr":"00:01","ms":"11:16","sr":"07:16","ss":"17:41"}
         * cond : {"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"}
         * date : 2018-02-07
         * hum : 21
         * pcpn : 0.0
         * pop : 0
         * pres : 1028
         * tmp : {"max":"2","min":"-9"}
         * uv : 2
         * vis : 20
         * wind : {"deg":"4","dir":"北风","sc":"4-5","spd":"17"}
         */

        private AstroBean astro;
        private CondBeanX cond;
        private String date;
        private String hum;
        private String pcpn;
        private String pop;
        private String pres;
        private TmpBean tmp;
        private String uv;
        private String vis;
        private WindBeanX wind;

        public AstroBean getAstro() {
            return astro;
        }

        public void setAstro(AstroBean astro) {
            this.astro = astro;
        }

        public CondBeanX getCond() {
            return cond;
        }

        public void setCond(CondBeanX cond) {
            this.cond = cond;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public TmpBean getTmp() {
            return tmp;
        }

        public void setTmp(TmpBean tmp) {
            this.tmp = tmp;
        }

        public String getUv() {
            return uv;
        }

        public void setUv(String uv) {
            this.uv = uv;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindBeanX getWind() {
            return wind;
        }

        public void setWind(WindBeanX wind) {
            this.wind = wind;
        }

        public static class AstroBean {
            /**
             * mr : 00:01
             * ms : 11:16
             * sr : 07:16
             * ss : 17:41
             */

            private String mr;
            private String ms;
            private String sr;
            private String ss;

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }
        }

        public static class CondBeanX {
            /**
             * code_d : 101
             * code_n : 100
             * txt_d : 多云
             * txt_n : 晴
             */

            private String code_d;
            private String code_n;
            private String txt_d;
            private String txt_n;

            public String getCode_d() {
                return code_d;
            }

            public void setCode_d(String code_d) {
                this.code_d = code_d;
            }

            public String getCode_n() {
                return code_n;
            }

            public void setCode_n(String code_n) {
                this.code_n = code_n;
            }

            public String getTxt_d() {
                return txt_d;
            }

            public void setTxt_d(String txt_d) {
                this.txt_d = txt_d;
            }

            public String getTxt_n() {
                return txt_n;
            }

            public void setTxt_n(String txt_n) {
                this.txt_n = txt_n;
            }
        }

        public static class TmpBean {
            /**
             * max : 2
             * min : -9
             */

            private String max;
            private String min;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }
        }

        public static class WindBeanX {
            /**
             * deg : 4
             * dir : 北风
             * sc : 4-5
             * spd : 17
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class HourlyForecastBean {
        /**
         * cond : {"code":"103","txt":"晴间多云"}
         * date : 2018-02-07 16:00
         * hum : 14
         * pop : 0
         * pres : 1029
         * tmp : 1
         * wind : {"deg":"115","dir":"东南风","sc":"微风","spd":"14"}
         */

        private CondBeanXX cond;
        private String date;
        private String hum;
        private String pop;
        private String pres;
        private String tmp;
        private WindBeanXX wind;

        public CondBeanXX getCond() {
            return cond;
        }

        public void setCond(CondBeanXX cond) {
            this.cond = cond;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public WindBeanXX getWind() {
            return wind;
        }

        public void setWind(WindBeanXX wind) {
            this.wind = wind;
        }

        public static class CondBeanXX {
            /**
             * code : 103
             * txt : 晴间多云
             */

            private String code;
            private String txt;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class WindBeanXX {
            /**
             * deg : 115
             * dir : 东南风
             * sc : 微风
             * spd : 14
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }
}
