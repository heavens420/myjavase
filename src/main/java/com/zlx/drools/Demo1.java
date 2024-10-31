package com.zlx.drools;


import com.alibaba.fastjson.JSONObject;
import com.zlx.drools.bean.BookBean;
import com.zlx.drools.bean.UserBean;
import com.zlx.java8features.User;
import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.definition.rule.Rule;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.util.*;

/**
 * drools demo
 */
public class Demo1 {

    private static KieServices kieServices = KieServices.Factory.get();

    /**
     * 11:26:27.473 [main] DEBUG org.drools.core.common.DefaultAgenda - State was FIRING_ALL_RULES is now HALTING
     * 11:26:27.473 [main] DEBUG org.drools.core.common.DefaultAgenda - State was HALTING is now INACTIVE
     * 11:26:27.474 [main] DEBUG org.drools.core.common.DefaultAgenda - State was INACTIVE is now DISPOSED
     *
     *
     * 11:27:15.557 [main] DEBUG org.drools.core.common.DefaultAgenda - State was INACTIVE is now FIRING_ALL_RULES
     * 11:27:15.569 [main] DEBUG org.drools.core.common.DefaultAgenda - State was FIRING_ALL_RULES is now HALTING
     * 11:27:15.569 [main] DEBUG org.drools.core.common.DefaultAgenda - State was HALTING is now INACTIVE
     * 11:27:15.569 [main] DEBUG org.drools.core.common.DefaultAgenda - State was INACTIVE is now DISPOSED
     *
     * @param args
     */


    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test();
//        test5();
//        test6();
        test7();
    }


    /**
     * 通过配置文件方式
     */
    public static void test(){
//        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // ruleKs 与META-INF配置下的ksession的name属性必须一致
        KieSession kieSession = kieContainer.newKieSession("ruleKs");
        // 指定要执行的规则分组
//        kieSession.getAgenda().getAgendaGroup("aaa").setFocus();
        User user = new User();
        user.setId(5);
        user.setName("zs");
        kieSession.insert(user);
        kieSession.fireAllRules();
        kieSession.dispose();
    }


    /**
     * 无需配置文件方式 直接指定规则文件路径
     */
    public static void test1() {
        Resource resource = ResourceFactory.newClassPathResource("rules/testRule.drl");
        KieHelper kieHelper = new KieHelper();
        kieHelper.addResource(resource);
        KieBase kieBase = kieHelper.build();
        KieSession kieSession = kieBase.newKieSession();
        // 指定要执行的规则分组
//        kieSession.getAgenda().getAgendaGroup("aaa").setFocus();
        User user = new User();
        user.setId(5);
        user.setName("zs");
        kieSession.insert(user);
        int allRules = kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("user:" + JSONObject.toJSONString(user));
        System.out.println("allRules:" + allRules);
    }

    /**
     * 无需配置文件方式 直接指定规则文件路径
     */
    public static void test2(){
        Resource resource = ResourceFactory.newClassPathResource("rules/testRule.drl");
        KieHelper kieHelper = new KieHelper();
        kieHelper.addResource(resource);
        KieBase kieBase = kieHelper.build();
        StatelessKieSession statelessKieSession = kieBase.newStatelessKieSession();
        User user = new User();
        user.setId(5);
        user.setName("zs");
        statelessKieSession.execute(user);
        System.out.println("user:" + JSONObject.toJSONString(user));
    }


    /**
     * 动态规则加载
     */
    public static void test3(){
        // 从数据库加载的规则
//        Rules rules = rulesMapper.selectByPrimaryKey(ruleId);
        // 模拟数据库加载数据
        Map<String, String> rules = new HashMap<String, String>();
        rules.put("name", "sss");
        rules.put("content", initRules());

        if (rules != null) {

            KieFileSystem kfs = kieServices.newKieFileSystem();

            System.out.println(">>>>>" + kfs);
//            kfs.delete("src/main/resources/rules/" + rules.get("name") + ".drl");
            kfs.write("src/main/resources/rules/" + rules.get("name") + ".drl", rules.get("content"));
            KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
            Results results = kieBuilder.getResults();
            if (results.hasMessages(Message.Level.ERROR)) {
                System.out.println(results.getMessages());
                throw new IllegalStateException("### errors ###");
            }

            StatelessKieSession kieSession = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId()).newStatelessKieSession();
            System.out.println("新规则重载成功:\n" + rules.get("content"));

            User user = new User();
            user.setId(5);
            user.setName("zs");

            List<User> list = new ArrayList<User>();
            // 设置global对象
            kieSession.setGlobal("list", list);
            kieSession.execute(user);

            Rule rules1 = kieSession.getKieBase().getRule("rules", "test-01");
            System.out.println("=======rules======="+rules1.toString());
            Globals globals = kieSession.getGlobals();

            // 获取global对象
            Object object = globals.get("list");
            System.out.println("===========global1========"+ object);
            // 直接使用global对象
            System.out.println("===========global2========"+ list);
        }

    }

    /**
     * 动态规则加载
     */
    public static void test4() {
//        List<Rules> rules = rulesMapper.selectAll();
        List<Map<String, String>> rules = new ArrayList<Map<String, String>>();

        KieFileSystem kfs = kieServices.newKieFileSystem();

        for (Map<String,String> rule: rules) {
            System.out.println(">>>>>" + kfs);
            kfs.delete("src/main/resources/rules/" + rule.get("name") + ".drl");
            kfs.write("src/main/resources/rules/" + rule.get("name") + ".drl", rule.get("content"));
        }

        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }

//        KieUtils.setKieContainer(kieServices.newKieContainer(getKieServices().getRepository().getDefaultReleaseId()));
        kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId()).newKieSession().getKieBase();
        System.out.println("初始化规则成功");

    }


    /**
     * 直接添加规则脚本 不指定规则路径
     */
    public static void test5() {
        String content = initRules();
        KieHelper helper = new KieHelper();
        helper.addContent(content, ResourceType.DRL);
        KieSession kSession = helper.build().newKieSession();
        User user = new User(22, "ls");
        kSession.insert(user);
        kSession.fireAllRules();
        kSession.dispose();

    }

    /**
     * 动态规则加载
     */
    public static void test6() {
        KieHelper kieHelper = new KieHelper();
        // 模拟数据库查询到的数据
        List<Map<String, String>> rules = new ArrayList<Map<String, String>>();
        Map<String, String> ruleMap = new HashMap<String, String>();
        ruleMap.put("name", "ddd");
        ruleMap.put("content", initRules());
        rules.add(ruleMap);
        for (Map<String, String> rule : rules) {
            String content = rule.get("content");
            // 添加规则
            kieHelper.addContent(content, ResourceType.DRL);
        }
        // 校验规则是否异常
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println("=========ERROR=========" + results.getMessages());
            throw new RuntimeException("初始化规则异常");
        }
        // 重新初始化规则容器
        KieContainer kieContainer = kieHelper.getKieContainer();
        StatelessKieSession statelessKieSession = kieContainer.newStatelessKieSession();
        User user = new User(22, "ls");
        statelessKieSession.execute(user);
//        return kieContainer;
    }



    public static void test7() {
        KieHelper kieHelper = new KieHelper();
//        kieHelper.addContent(initRules2(), ResourceType.DRL);
        kieHelper.addResource(new ClassPathResource("rules/testRule.drl"), ResourceType.DRL);
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println("=========ERROR=========" + results.getMessages());
            throw new RuntimeException("初始化规则异常");
        }
        KieContainer kieContainer = kieHelper.getKieContainer();
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.getAgenda().getAgendaGroup("multi").setFocus();

        BookBean bookBean = new BookBean(99,"hh","22");
        BookBean bookBean2 = new BookBean(19,"ss","33");
        UserBean userBean = new UserBean();
        userBean.setId(123);
        userBean.setUsername("kkkkk");
        userBean.setBookBeanList(Arrays.asList(bookBean, bookBean2));

        kieSession.insert(userBean);
//        kieSession.insert(bookBean);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    public static void test8() {
        KieHelper kieHelper = new KieHelper();
//        kieHelper.addContent(initRules2(), ResourceType.DRL);
        kieHelper.addResource(new ClassPathResource("rules/testRule.drl"), ResourceType.DRL);
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println("=========ERROR=========" + results.getMessages());
            throw new RuntimeException("初始化规则异常");
        }
        KieContainer kieContainer = kieHelper.getKieContainer();
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.getAgenda().getAgendaGroup("multi").setFocus();

        BookBean bookBean = new BookBean(99,"hh","22");
        BookBean bookBean2 = new BookBean(19,"ss","33");
        UserBean userBean = new UserBean();
        userBean.setId(123);
        userBean.setUsername("kkkkk");
        userBean.setBookBeanList(Arrays.asList(bookBean, bookBean2));

        kieSession.insert(userBean);
//        kieSession.insert(bookBean);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    public static String initRules2() {
        final String rules = "package rules;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "import com.zlx.java8features.User;\n" +
                "import com.zlx.drools.bean.BookBean\n" +
                "import com.zlx.drools.bean.UserBean\n" +
                "\n" +
                "global java.util.List list;\n" +
                "global java.util.List cfsPreposeList;\n" +
                "global java.util.List listParamCode;\n" +
                "global java.util.List listParamValue;\n" +
                "global java.util.List listParamOldValue;\n" +
                "global java.util.List listQuery;\n" +
                "\n" +
                "rule \"test-01\"\n" +
                "when\n" +
                "    $u: User(id > 1)\n" +
                "then\n" +
                "    $u.setName(\"hahaha\");\n" +
                "    System.out.println(\"==================\"+$u);\n" +
                "end\n" +
                "\n" +
                "rule \"test-02\"\n" +
                "when\n" +
                "    $u: User(name==\"zs\")\n" +
                "then\n" +
                "    System.out.println(\"------------------\"+$u);\n" +
                "end\n" +
                "\n" +
                "rule \"test-03\"\n" +
                "when\n" +
                "    $u: User(name==\"ls\")\n" +
                "then\n" +
                "    System.out.println(\"------------------\"+$u);\n" +
                "end\n" +
                "\n" +
                "\n" +
                "rule \"test-04\"\n" +
                "agenda-group \"multi\"\n" +
                "no-loop\n" +
                "when\n" +
                "    // list1：UserBean对象本身  $lst: UserBean的bookBeanList属性值\n" +
                "    list1: UserBean($lst:bookBeanList)\n" +
                "    // $book: 遍历$lst中的变量值满足price>10的BookBean\n" +
                "    $book: BookBean(id> 10) from $lst\n" +
                "then\n" +
                "    System.out.println(\"list1--\"+list1);\n" +
                "    System.out.println(\"$lst--\"+$lst);\n" +
                "    System.out.println(\"$book--\"+$book);\n" +
                "end\n";
        return rules;
    }
    public static String initRules() {
        final String rules = "package rules;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "import com.zlx.java8features.User;\n" +
                "\n" +
                "\n" +
                "global java.util.List list;\n" +
                "global java.util.List cfsPreposeList;\n" +
                "global java.util.List listParamCode;\n" +
                "global java.util.List listParamValue;\n" +
                "global java.util.List listParamOldValue;\n" +
                "global java.util.List listQuery;\n" +
                "\n" +
                "rule \"test-01\"\n" +
                "when\n" +
                "    $u: User(id > 1)\n" +
                "then\n" +
                "    $u.setName(\"hahaha\");\n" +
                "    System.out.println(\"==================\"+$u);\n" +
                "    list.add($u);\n"+
                "end\n" +
                "\n" +
                "rule \"test-02\"\n" +
                "when\n" +
                "    $u: User(name==\"zs\")\n" +
                "then\n" +
                "    System.out.println(\"------------------\"+$u);\n" +
                "end\n" +
                "\n" +
                "rule \"test-03\"\n" +
                "when\n" +
                "    $u: User(name==\"ls\")\n" +
                "then\n" +
                "    System.out.println(\"------------------\"+$u);\n" +
                "end\n";
        return rules;
    }

}
