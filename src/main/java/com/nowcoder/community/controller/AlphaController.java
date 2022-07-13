package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getDate(){
        return alphaService.find();
    }

    //response对象可以向浏览器响应数据，不需要@ResponseBody对象或者视图渲染
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据  请求行 请求消息头 请求体
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();//请求的key
            String value = request.getHeader(name);
            System.out.println(name+":"+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据  HttpServletResponse
        response.setContentType("text/html;charset=utf-8");  //设置返回的类型   返回text/html网页类型的文本
        try (
            PrintWriter writer = response.getWriter(); //response输出流   加到这里不用写 finally和close方法
        ){
            writer.write("<h1>牛客网</h1>");  //输出流，写到html页面中
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 一、封装：接受请求数据
    // 1. get请求
    // 1)/students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name="current",required = false,defaultValue = "1") int current, Integer limit){
        //默认：url路径中输入的参数名和方法中形参定义的参数名一致，就可以传参
        //更多的对参数的需求  @RequestParam注解可以对参数的注入进行更详细的声名 name=current的参数注入
        System.out.println(current);
        System.out.println(limit);
        return "get请求 学生";
    }
    // 2)/students/2 restful风格
    @RequestMapping(path = "/student/{id}",method=RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "get请求 一个学生";
    }
    //2.post请求 浏览器向服务器提交数据
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        //设置参数的名字和表单的名字一致，就可以实现自动的传参
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //二、响应
    //1.响应html数据
    @RequestMapping(path="teacher",method=RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","加油");
        mav.addObject("age",30);
        //设置模板名称
        mav.setViewName("/demo/teacherView.html");
        return mav;
    }

    @RequestMapping(path="/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        //Model 是dispatcherServlet自动实例化的 bean对象
        model.addAttribute("name","呆呆不呆");
        model.addAttribute("age",33);
        return "/demo/teacherView.html";//如果没有@ResponseBody注解，则返回字符串默认为视图名称，由thymeleaf解析
    }

    //2.响应JSON数据（应用场景：异步请求）
    @RequestMapping(path="/emp",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",20);
        return emp;
    }

    @RequestMapping("/emps")
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp1 = new HashMap<>();
        emp1.put("name","张三");
        emp1.put("age",80);
        emp1.put("salary",150000);
        Map<String,Object> emp2 = new HashMap<>();
        emp2.put("name","王五");
        emp2.put("age",80);
        emp2.put("salary",150000);
        Map<String,Object> emp3 = new HashMap<>();
        emp3.put("name","李四");
        emp3.put("age",80);
        emp3.put("salary",150000);
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        return list;
    }
}
