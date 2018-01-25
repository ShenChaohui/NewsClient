package com.geniuses.newsclient.entity;
import java.io.Serializable;

/**
 * Created by Sch on 2017/11/30.
 */

public class NewsModel implements Serializable {
    /**
     * src : 澎湃新闻
     * weburl : http://news.sina.com.cn/c/nd/2017-11-30/doc-ifyphkhk8954204.shtml
     * time : 2017-11-30 11:43
     * pic : http://api.jisuapi.com/news/upload/201711/30150007_93423.jpg
     * title : 侯淅珉任吉林省副省长 此前担任安徽省政府秘书长
     * category : news
     * content : <p class="art_p">原标题：侯淅珉任吉林省副省长</p><p class="art_p">吉林省十二届人大常委会第三十八次会议第三次全体会议11月30日上午在长春召开，会议决定任命侯淅珉为吉林省副省长。会上，侯淅珉进行了宪法宣誓并作表态发言。</p><p class="art_p"><strong>侯淅珉简历</strong></p><p class="art_p">侯淅珉，男，汉族，1963年7月生，河南淅川人，中共党员，1987年7月参加工作，管理学博士。现任吉林省人民政府副省长、党组成员。</p><p class="art_p">1987.07--1991.06 国家经济体制改革研究所发展研究室研究人员</p><p class="art_p">1991.06--1992.09 国务院房改领导小组办公室指导处干部</p><p class="art_p">1992.09--1995.01 国务院房改领导小组办公室指导处副处长</p><p class="art_p">1995.01--1998.07 国务院房改领导小组办公室指导处处长</p><p class="art_p">1998.07--1999.04 建设部住宅与房地产业司房改政策指导处处长</p><p class="art_p">1999.04--2007.10 建设部住宅与房地产业司副司长（2001.03--2002.01 中央党校一年制中青班学习）</p><p class="art_p">2007.10--2008.08 建设部住房保障与公积金监督管理司司长</p><p class="art_p">2008.08--2010.11 住房和城乡建设部住房保障司司长</p><p class="art_p">2010.11--2011.01 安徽省铜陵市委副书记</p><p class="art_p">2011.01--2014.04 安徽省铜陵市委副书记，市长（2009.09--2013.06 华中科技大学管理科学与工程专业在职博士研究生学习）</p><p class="art_p">2014.04--2017.02 安徽省住房和城乡建设厅厅长、党组书记</p><p class="art_p">2017.02--2017.03 安徽省人民政府党组成员，办公厅党组书记</p><p class="art_p">2017.03--2017.11 安徽省人民政府秘书长、党组成员，办公厅党组书记</p><p class="art_p">2017.11-- 吉林省人民政府副省长、党组成员</p>
     * url : http://news.sina.cn/gn/2017-11-30/detail-ifyphkhk8954204.d.html?vt=4&pos=108
     */
    private String src;
    private String weburl;
    private String time;
    private String pic;
    private String title;
    private String category;
    private String content;
    private String url;
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}



