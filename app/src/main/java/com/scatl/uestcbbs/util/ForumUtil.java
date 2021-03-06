package com.scatl.uestcbbs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: sca_tl
 * date: 2020/5/3 16:03
 * description: 从论坛链接获取相关信息
 */
public class ForumUtil {

    public static ForumLink getFromLinkInfo(String url) {

        ForumLink forumLink = new ForumLink();

        //河畔帖子链接：(1)http://bbs.uestc.edu.cn/forum.php?mod=viewthread&tid=xxxxx(或者xxxxx&yyyyy)
        //河畔帖子链接：(2)http://bbs.uestc.edu.cn/forum.php?mod=redirect&goto=findpost&ptid=xxxx&pid=xxxx
        //河畔帖子链接：(3)http://bbs.stuhome.net/forum.php?mod=viewthread&tid=xxxxxx
        //河畔帖子链接：(4)http://bbs.stuhome.net/read.php?tid=xxxx
        //河畔板块链接：http://bbs.uestc.edu.cn/forum.php?mod=forumdisplay&fid=xxx
        //个人详情链接：http://bbs.uestc.edu.cn/home.php?mod=space&uid=xxx(或者xxxxx&yyyyy)
        //淘帖链接：http://bbs.uestc.edu.cn/forum.php?mod=collection&action=view&ctid=200&xxxxxxx
        Matcher post_matcher1 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/forum\\.php\\?mod=viewthread&tid=(\\d+)").matcher(url);
        Matcher post_matcher2 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/forum\\.php\\?mod=viewthread&tid=(\\d+)(&)(.*)").matcher(url);
        Matcher post_matcher3 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/forum\\.php\\?mod=redirect&goto=findpost&ptid=(\\d+)").matcher(url);
        Matcher post_matcher4 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/forum\\.php\\?mod=redirect&goto=findpost&ptid=(\\d+)(&)(.*)").matcher(url);
        Matcher post_matcher5 = Pattern.compile("http://bbs\\.stuhome\\.net/forum\\.php\\?mod=viewthread&tid=(\\d+)").matcher(url);
        Matcher post_matcher6 = Pattern.compile("http://bbs\\.stuhome\\.net/forum\\.php\\?mod=viewthread&tid=(\\d+)(&)(.*)").matcher(url);
        Matcher post_matcher7 = Pattern.compile("http://bbs\\.stuhome\\.net/read\\.php\\?tid=(\\d+)").matcher(url);
        Matcher post_matcher8 = Pattern.compile("http://bbs\\.stuhome\\.net/read\\.php\\?tid=(\\d+)(&)(.*)").matcher(url);

        Matcher user_matcher1 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/home\\.php\\?mod=space&uid=(\\d+)").matcher(url);
        Matcher user_matcher2 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/home\\.php\\?mod=space&uid=(\\d+)(&)(.*)").matcher(url);

        Matcher forum_matcher1 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/forum\\.php\\?mod=forumdisplay&fid=(\\d+)").matcher(url);
        Matcher forum_matcher2 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/forum\\.php\\?mod=forumdisplay&fid=(\\d+)(&)(.*)").matcher(url);

        Matcher collection_matcher1 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/forum\\.php\\?mod=collection&action=view&ctid=(\\d+)(&)(.*)").matcher(url);
        Matcher collection_matcher2 = Pattern.compile("http://bbs\\.uestc\\.edu\\.cn/forum\\.php\\?mod=collection&action=view&ctid=(\\d+)").matcher(url);

        if (post_matcher1.find()) {
            forumLink.id = Integer.parseInt(post_matcher1.group(1));
            forumLink.linkType = LinkType.POST;
            return forumLink;
        } else if (post_matcher2.find()) {
            forumLink.id = Integer.parseInt(post_matcher2.group(1));
            forumLink.linkType = LinkType.POST;
            return forumLink;
        } else if (post_matcher3.find()) {
            forumLink.id = Integer.parseInt(post_matcher3.group(1));
            forumLink.linkType = LinkType.POST;
            return forumLink;
        } else if (post_matcher4.find()) {
            forumLink.id = Integer.parseInt(post_matcher4.group(1));
            forumLink.linkType = LinkType.POST;
            return forumLink;
        } else if (post_matcher5.find()) {
            forumLink.id = Integer.parseInt(post_matcher5.group(1));
            forumLink.linkType = LinkType.POST;
            return forumLink;
        } else if (post_matcher6.find()) {
            forumLink.id = Integer.parseInt(post_matcher6.group(1));
            forumLink.linkType = LinkType.POST;
            return forumLink;
        } else if (post_matcher7.find()) {
            forumLink.id = Integer.parseInt(post_matcher7.group(1));
            forumLink.linkType = LinkType.POST;
            return forumLink;
        } else if (post_matcher8.find()) {
            forumLink.id = Integer.parseInt(post_matcher8.group(1));
            forumLink.linkType = LinkType.POST;
            return forumLink;
        } else if (user_matcher1.find()) {
            forumLink.id = Integer.parseInt(user_matcher1.group(1));
            forumLink.linkType = LinkType.USER;
            return forumLink;
        } else if (user_matcher2.find()) {
            forumLink.id = Integer.parseInt(user_matcher2.group(1));
            forumLink.linkType = LinkType.USER;
            return forumLink;
        } else if (forum_matcher1.find()) {
            forumLink.id = Integer.parseInt(forum_matcher1.group(1));
            forumLink.linkType = LinkType.FORUM;
            return forumLink;
        } else if (forum_matcher2.find()) {
            forumLink.id = Integer.parseInt(forum_matcher2.group(1));
            forumLink.linkType = LinkType.FORUM;
            return forumLink;
        } else if (collection_matcher1.find()) {
            forumLink.id = Integer.parseInt(collection_matcher1.group(1));
            forumLink.linkType = LinkType.COLLECTION;
            return forumLink;
        } else if (collection_matcher2.find()) {
            forumLink.id = Integer.parseInt(collection_matcher2.group(1));
            forumLink.linkType = LinkType.COLLECTION;
            return forumLink;
        } else {
            forumLink.id = 0;
            forumLink.linkType = LinkType.OTHER;
            return forumLink;
        }

    }

    public static class ForumLink{
        public int id;
        public LinkType linkType;
    }

    public enum LinkType {
        POST,
        FORUM,
        USER,
        COLLECTION,
        OTHER
    }
}
