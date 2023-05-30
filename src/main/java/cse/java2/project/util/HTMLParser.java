package cse.java2.project.util;

import com.github.javaparser.ParseException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {

    private HTMLParser() {
    }

    public static List<String> findCodeInBlocks(String htmlText) {
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

    public static Map<String, Integer> JavaAPICollect(List<String> codeBlocks) {
        Map<String, Integer> result = new HashMap<>();
        codeBlocks.forEach(codeBlock -> {
            try {
                CompilationUnit cu = StaticJavaParser.parse(codeBlock);
                Set<String> names = new HashSet<>();
                cu.accept(new VoidVisitorAdapter<Void>() {
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                        names.add(n.getNameAsString());
                        super.visit(n, arg);
                    }

                    @Override
                    public void visit(MethodDeclaration n, Void arg) {
                        names.addAll(n.getParameters().stream().map(Parameter::getType).map(
                            Type::asString).toList());
                        super.visit(n, arg);
                    }

                    @Override
                    public void visit(VariableDeclarator n, Void arg) {
                        names.add(n.getType().asString());
                        super.visit(n, arg);
                    }
                }, null);
                names.forEach(n -> {
                    int value = result.getOrDefault(n, 0);
                    result.put(n, value + 1);
                });
            } catch (Exception e) {
                System.out.println("无法解析的代码块: " + codeBlock);
            }


        });

        return result;
    }


}
