package com.pingan.restful;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pingan.entity.User;
import com.pingan.service.UserService;
import com.pingan.util.PropertyPlaceholder;

@Component
@Path("/userRest")
public class UserRestful {
    /**
     * ��ȡSpring Bean  user �������õ�ֵ��a
     */
    @Autowired
    private User user1;

    /**
     * ��ȡSpring Bean SpringUser �������õ�ֵ��
     */
    @Autowired
    private UserService SpringUser;

    @Autowired
    private UserService userService;
    @Context
    private HttpServletRequest request;
    @Autowired
    private HttpServletRequest res;

    /**
     * http://localhost:8080/Jersey/rest/userRest/query
     *
     * @return
     * @throws InterruptedException
     */
    @Path("query")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() throws InterruptedException {
        String user = userService.getUser();
        List<User> list = SpringUser.getUserList();
        String param = request.getParameter("name");
        String result = user + list.get(0).getName() + list.get(0).getAddress() + "请求参数为" + param;
        System.out.println("---------------" + PropertyPlaceholder.getProperty("user"));
        return Response.ok(result).build();
    }

    /**
     * 写真图片
     *
     * @param id
     * @return
     */
    @Path("picture/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response picture(@PathParam("id") String id) {
        JSONArray jsonArray = new JSONArray();
        try {
            Elements select = Jsoup.connect("http://www.4j4j.cn/beauty/tag_23_" + id + ".html").get().select("#pic-list li a img");
            for (Element element : select) {
                String attr = element.attr("data-original");
                jsonArray.add(attr);
            }
            System.out.println("IP---------------" + getRemortIP());
            System.out.println("数据---" + jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.ok(jsonArray.toString()).build();
    }


    /**
     * 获取IP
     *
     * @return
     */
    public String getRemortIP() {
        if (res.getHeader("x-forwarded-for") == null) {
            return res.getRemoteAddr();
        }
        return res.getHeader("x-forwarded-for");
    }


    public static void main(String[] args) throws IOException {
        Elements select = Jsoup.connect("http://www.4j4j.cn/beauty/tag_23_2.html").get().select("#pic-list li a img");
        for (Element element : select) {
            String attr = element.attr("data-original");
            System.out.println(attr);
        }

    }

}
