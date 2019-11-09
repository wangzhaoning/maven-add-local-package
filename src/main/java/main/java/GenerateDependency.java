package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateDependency {

    private static final String PATH = "你的jar目录";

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> files = getFiles();
        System.out.println(files);
        List<List<String>> newNamesResult = processBuilderCommand(files);
        useMvnCommand(newNamesResult.get(0), newNamesResult.get(1));
        printDependency(newNamesResult.get(1));
    }

    private static void printDependency(List<String> newNamesPre) {
        for (String newNamePre : newNamesPre) {

            System.out.println(" <dependency>  \n" +
                    "      <groupId>" + newNamePre + "</groupId>  \n" +
                    "      <artifactId>" + newNamePre + "</artifactId>  \n" +
                    "      <version>1.0</version>  \n" +
                    "    </dependency>  ");
        }
    }

    private static void useMvnCommand(List<String> newNames, List<String> newNamesPre) throws IOException, InterruptedException {
        for (int i = 0; i < newNames.size(); i++) {
            List<String> commands = new ArrayList<>();
            commands.add("cmd.exe");
            commands.add("/c");
            System.out.println("mvn install:install-file -Dfile=" + newNames.get(i) + " -DgroupId=" + newNamesPre.get(i) +
                    " -DartifactId=" + newNamesPre.get(i) + " -Dversion=1.0 -Dpackaging=jar");
            commands.add("mvn install:install-file -Dfile=" + newNames.get(i) + " -DgroupId=" + newNamesPre.get(i) +
                    " -DartifactId=" + newNamesPre.get(i) + " -Dversion=1.0 -Dpackaging=jar");
            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.directory(new File(PATH));
            Process process = pb.start();
            int status = process.waitFor();
            InputStream in = process.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }
    }


    private static List<String> getFiles() {
        List<String> files = new ArrayList<>();
        File file = new File(GenerateDependency.PATH);
        File[] tempList = file.listFiles();

        assert tempList != null;
        for (File value : tempList) {
            if (value.isFile()) {
                String fileName = value.getName();
                files.add(fileName);
            }
        }
        return files;
    }


    private static List<List<String>> processBuilderCommand(List<String> files) throws IOException, InterruptedException {
        List<String> newNames = new ArrayList<>();
        List<String> newFilePre = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        for (String file : files) {
            List<String> commands = new ArrayList<>();
            commands.add("cmd.exe");
            commands.add("/c");
            //按自己的应用逻辑需要改变一下
            String[] fileName = file.split("-");
            String newFileName;
            if (fileName.length > 2) {
                newFileName = fileName[0].trim() + "-" + fileName[1].trim() + "-1.0.jar";
                newFilePre.add(fileName[0] + "-" + fileName[1]);
            } else {
                newFileName = fileName[0] + "-1.0.jar";
                newFilePre.add(fileName[0]);
            }
            commands.add("rename " + "\"" + file + "\"" + " " + newFileName);
            newNames.add(newFileName);
            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.directory(new File(PATH));
            Process process = pb.start();
            int status = process.waitFor();
            InputStream in = process.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }
        result.add(newNames);
        result.add(newFilePre);
        return result;
    }
}
