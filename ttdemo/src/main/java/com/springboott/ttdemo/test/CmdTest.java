package com.springboott.ttdemo.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CmdTest {

    @Test
    public void test() {
        StringBuffer command = new StringBuffer();
        command.append("cmd /c c: ");
        //这里的&&在多条语句的情况下使用，表示等上一条语句执行成功后在执行下一条命令，
        //也可以使用&表示执行上一条后台就立刻执行下一条语句
//        command.append(String.format("cd %s", "c:\\"));
//        command.append(String.format("&& cd C:\\"));
//        command.append("& ipconfig");
        command.append("&& dir");
        System.out.println("command.toString() = " + command.toString());
//        cmd /k c:  && cd C:\ && ipconfig
        List<String> result = runCommand(command.toString());
        System.out.println("result = " + result);
        for (String value : result) {
            System.out.println(":::" + value);
        }
    }


    public List<String> runCommand(String command) {
        try {
            System.out.println("command = " + command);
            Runtime run = Runtime.getRuntime();
            System.out.println("run = " + run);
            Process proc;
            proc = run.exec(command);
            System.out.println("proc = " + proc);

            InputStream is = proc.getInputStream();
            System.out.println("is = " + is);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "GBK"));
            System.out.println("reader = " + reader);
            ArrayList response = new ArrayList();

            String line;
            System.out.println("reader.readLine() = " + reader.readLine());
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            System.out.println("line = " + line);
            System.out.println("line = " + response);
            proc.waitFor();
            is.close();
            reader.close();
            proc.destroy();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e = " + e.getMessage());
            return new ArrayList();
        }
    }
}
