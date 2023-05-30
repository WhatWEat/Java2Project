package cse.java2.project.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {
    private HTMLParser(){}
    public static List<String> findCodeInBlocks(String htmlText){
        List<String> codeList = new ArrayList<>();
        Document document = Jsoup.parse(htmlText);
        Elements codeElements = document.select("code");

        Pattern pattern = Pattern.compile(".*", Pattern.DOTALL);
        for (Element codeElement : codeElements) {
            Matcher matcher = pattern.matcher(codeElement.text());
            if (matcher.matches()) {
                codeList.add(matcher.group(0));
            }
        }
        return codeList;
    }

    public static Map<String, Integer> classOrInterfaceCollector(String codeBlock){
        CompilationUnit cu = StaticJavaParser.parse(codeBlock);
        Map<String, Integer> result = new HashMap<>();
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                int value = result.getOrDefault(n.getName().toString(), 0);
                result.put(n.getName().toString(), value+1);
                super.visit(n, arg);
            }
        }, null);
        return result;
    }

    public static Map<String, Integer> methodDeclarationCollector(String codeBlock){
        CompilationUnit cu = StaticJavaParser.parse(codeBlock);
        Map<String, Integer> result = new HashMap<>();
        cu.accept(new VoidVisitorAdapter<Void>() {

            @Override
            public void visit(MethodDeclaration n, Void arg) {
                int value = result.getOrDefault(n.getName().toString(), 0);
                result.put(n.getName().toString(), value+1);
                super.visit(n, arg);
            }
        }, null);
        return result;
    }

    public static Map<String, Integer> constCollector(String codeBlock){
        CompilationUnit cu = StaticJavaParser.parse(codeBlock);
        Map<String, Integer> result = new HashMap<>();
        cu.accept(new VoidVisitorAdapter<Void>() {

            @Override
            public void visit(NameExpr n, Void arg) {
                int value = result.getOrDefault(n.getName().toString(), 0);
                result.put(n.getName().toString(), value+1);
                super.visit(n, arg);
            }
        }, null);
        return result;
    }


}
