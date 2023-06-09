package cse.java2.project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import cse.java2.project.entities.Answer;
import cse.java2.project.entities.Comment;
import cse.java2.project.entities.Owner;
import cse.java2.project.entities.Question;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonParser {

  public static final String QuestionJsonPath = "src/main/resources/jsons/questions.json";
  public static JSONArray jsonQuestionArray;
  public static List<Question> questions; // 总数是998，即questions.size
  public static Map<Long, List<Answer>> ansOfQues; // 只会记录有回复的, 如果没有回复，查询得到null
  public static Map<Long, Question> idOfQue; // 总数是998，即questions.size

  private JsonParser() {
  }

  ;

  static {
    try {
      // 读取JSON文件内容为字符串
      String jsonStr = new String(
          Files.readAllBytes(Paths.get("src/main/resources/jsons/questions.json")));

      // 将JSON字符串转换为JSONArray对象
      jsonQuestionArray = JSON.parseArray(jsonStr);
      questions = jsonQuestionArray.toJavaList(Question.class);
      idOfQue = new HashMap<>();
      questions.forEach(q -> {
        if (!idOfQue.containsKey(q.getQuestion_id())) {
          idOfQue.put(q.getQuestion_id(), q);
        }
      });
      questions = new ArrayList<>(idOfQue.values());
      // 读取answer
      jsonStr = new String(
          Files.readAllBytes(Paths.get("src/main/resources/jsons/answers.json")));
      jsonQuestionArray = JSON.parseArray(jsonStr);
      List<Answer> ans = jsonQuestionArray.toJavaList(Answer.class);
      ansOfQues = new HashMap<>();
      ans.forEach(a -> {
        if (ansOfQues.containsKey(a.getQuestion_id())) {
          ansOfQues.get(a.getQuestion_id()).add(a);
        } else {
          List<Answer> answers = new ArrayList<>();
          answers.add(a);
          ansOfQues.put(a.getQuestion_id(), answers);
        }
      });

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static List<Integer> noAnsCount() {
    int totalCount = questions.size();
    int noAnsCount = questions.size() - ansOfQues.size();
    return Arrays.asList(noAnsCount, totalCount);
  }

  public static List<Integer> avgAnsCount() {
    int totalCount = questions.size();
    AtomicInteger totalAns = new AtomicInteger(0);
    questions.forEach(e -> {
      totalAns.addAndGet(e.getAnswer_count());
    });
    return Arrays.asList(totalAns.get(), totalCount);
  }

  public static int maxAnsCount() {
    AtomicInteger max = new AtomicInteger(0);
    questions.forEach(e -> {
      if (e.getAnswer_count() == 40) {
        System.err.println(e.getQuestion_id());
      }
      max.set(Math.max(max.get(), e.getAnswer_count()));
    });
    return max.get();
  }

  public static Map<String, Integer> ansDistribution() {
    Map<String, Integer> result = new HashMap<>();
    for (List<Answer> answers : ansOfQues.values()) {
      int value = result.getOrDefault(String.valueOf(answers.size()), 0);
      result.put(String.valueOf(answers.size()), value + 1);
    }
    result.put("0", questions.size() - ansOfQues.size());
    return result;
  }

  public static List<Integer> acceptAnsCnt() {
    int totalCount = questions.size();
    AtomicInteger acc = new AtomicInteger(0);
    questions.forEach(e -> {
      if (e.isIs_answered()) {
        acc.getAndIncrement();
      }
    });
    return Arrays.asList(acc.get(), totalCount);
  }

  public static List<Pair> resolutionTimeDis() {
    int lessThanOneDay = 0,
        lessThanTwoDay = 0,
        lessThanAWeek = 0,
        longerThanAWeek = 0;
    Map<String, Integer> result = new HashMap<>();
    for (Long l : idOfQue.keySet()) {
      Question q = idOfQue.get(l);
      if (q.isIs_answered()) {
        List<Answer> answers = ansOfQues.get(l);
        if (answers == null) {
          continue;
        }
        for (Answer answer : answers) {
          if (answer.isIs_accepted()) {
            final float days =
                (float) (answer.getCreation_date() - q.getCreation_date()) / 86400;
            if (days < 1) {
              lessThanOneDay += 1;
            } else if (days < 2) {
              lessThanTwoDay += 1;

            } else if (days < 7) {
              lessThanAWeek += 1;

            } else {
              longerThanAWeek += 1;
            }
          }
        }
      }
    }
    return Arrays.asList(new Pair("<1 day", lessThanOneDay),
        new Pair("<2 day", lessThanTwoDay),
        new Pair("<1 week", lessThanAWeek),
        new Pair(">=1 week", longerThanAWeek));
  }

  public static List<Integer> ansOverAccept() {
    int over = 0, total = 0;
    for (Long l : idOfQue.keySet()) {
      Question q = idOfQue.get(l);
      if (q.isIs_answered()) {
        total++;
        int accVote = 0, maxVote = 0;
        List<Answer> answers = ansOfQues.get(l);
        if (answers == null) {
          continue;
        }
        for (Answer answer : answers) {
          if (answer.isIs_accepted()) {
            accVote = answer.getUp_vote_count();
          } else {
            maxVote = Math.max(maxVote, answer.getUp_vote_count());
          }
        }
        if (maxVote > accVote) {
          over++;
        }
      }
    }
    return Arrays.asList(over, total);
  }

  public static Map<String, Integer> appearMostWithJava() {
    Map<String, Integer> result = new HashMap<>();
    for (Question q : questions) {
      if (q.getTags() == null || !q.getTags().contains("java")) {
        continue;
      }
      for (String tag : q.getTags()) {
        if (!tag.equals("java")) {
          int value = result.getOrDefault(tag, 0);
          result.put(tag, value + 1);
        }
      }
    }
    return result;
  }

  public static List<Pair> MostUpCom() {
    Map<Set<String>, Integer> immediate = new HashMap<>();
    for (Question q : questions) {
      if (q.getUp_vote_count() == 0) {
        continue;
      }
      List<Set<String>> tagSubsets = generateSubsets(q.getTags());
      tagSubsets.forEach(s -> {
        int value = immediate.getOrDefault(s, 0);
        immediate.put(s, value + q.getUp_vote_count());
      });
    }
    Map<String, Integer> imm2 = new HashMap<>();
    immediate.forEach((s, i) -> {
      imm2.put(s.toString(), i);
    });
    return PairParser.MapToList(imm2).stream().sorted((p1, p2) -> p2.getValue() - p1.getValue())
        .toList();
  }

  public static List<Pair> MostViewCom() {
    Map<Set<String>, Integer> immediate = new HashMap<>();
    for (Question q : questions) {
      if (q.getView_count() == 0) {
        continue;
      }
      List<Set<String>> tagSubsets = generateSubsets(q.getTags());
      tagSubsets.forEach(s -> {
        int value = immediate.getOrDefault(s, 0);
        immediate.put(s, value + q.getView_count());
      });
    }
    Map<String, Integer> imm2 = new HashMap<>();
    immediate.forEach((s, i) -> {
      imm2.put(s.toString(), i);
    });
    return PairParser.MapToList(imm2).stream().sorted((p1, p2) -> p2.getValue() - p1.getValue())
        .toList();
  }

  private static List<Set<String>> generateSubsets(List<String> set) {
    int n = set.size();
    List<Set<String>> subsets = new ArrayList<>();

    // 生成二进制掩码
    int total = (int) Math.pow(2, n);
    for (int i = 0; i < total; i++) {
      Set<String> subset = new HashSet<>();

      for (int j = 0; j < n; j++) {
        // 检查二进制掩码对应位置的位是否为1
        if ((i & (1 << j)) > 0) {
          subset.add(set.get(j));
        }
      }
      if (subset.size() > 1) {
        subsets.add(subset);
      }
    }

    return subsets;
  }

  public static Map<String, Integer> totalParticipantsDis() {
    Map<String, Integer> result = new HashMap<>();
    for (Question q : questions) {
      Set<Long> userIds = new HashSet<>();
      userIds.add(q.getOwner().getAccount_id());
      if (ansOfQues.containsKey(q.getQuestion_id())) {
        userIds.addAll(ansOfQues.get(q.getQuestion_id()).stream().map(Answer::getOwner)
            .map(Owner::getAccount_id).toList());
      }
      if (q.getComments() != null) {
        // 添加评论的Owner的id
        userIds.addAll(
            q.getComments().stream().map(Comment::getOwner).map(Owner::getAccount_id)
                .toList());
      }
      int value = result.getOrDefault(String.valueOf(userIds.size()), 0);
      result.put(String.valueOf(userIds.size()), value + 1);
    }
    return result;
  }

  public static Map<String, Integer> answerParticipantsDis() {
    Map<String, Integer> result = new HashMap<>();
    for (Question q : questions) {
      Set<Long> userIds = new HashSet<>();
      if (ansOfQues.containsKey(q.getQuestion_id())) {
        userIds.addAll(ansOfQues.get(q.getQuestion_id()).stream().map(Answer::getOwner)
            .map(Owner::getAccount_id).toList());
      }
      int value = result.getOrDefault(String.valueOf(userIds.size()), 0);
      result.put(String.valueOf(userIds.size()), value + 1);
    }
    return result;
  }

  public static Map<String, Integer> commentParticipantsDis() {
    Map<String, Integer> result = new HashMap<>();
    for (Question q : questions) {
      Set<Long> userIds = new HashSet<>();
      if (q.getComments() != null) {
        // 添加评论的Owner的id
        userIds.addAll(
            q.getComments().stream().map(Comment::getOwner).map(Owner::getAccount_id)
                .toList());
      }
      int value = result.getOrDefault(String.valueOf(userIds.size()), 0);
      result.put(String.valueOf(userIds.size()), value + 1);
    }
    return result;
  }

  public static Map<String, Integer> threadsSta() {
    Map<String, Integer> result = new HashMap<>();
    Set<Long> userIds = new HashSet<>();
    Set<Long> userIdInAns = new HashSet<>();
    Set<Long> userIdInCom = new HashSet<>();
    for (Question q : questions) {
      userIds.add(q.getOwner().getAccount_id());
      if (ansOfQues.containsKey(q.getQuestion_id())) {
        List<Long> ids = ansOfQues.get(q.getQuestion_id()).stream().map(Answer::getOwner)
            .map(Owner::getAccount_id).toList();
        userIdInAns.addAll(ids);
      }
      if (q.getComments() != null) {
        List<Long> ids = q.getComments().stream().map(Comment::getOwner)
            .map(Owner::getAccount_id).toList();
        // 添加评论的Owner的id
        userIdInCom.addAll(ids);
      }
    }
    result.put("questions", userIds.size());
    result.put("answers", userIdInAns.size());
    result.put("comments", userIdInCom.size());
    return result;
  }

  public static Map<String, Integer> activityEvaluation() {
    // 发布问题加5，answer+3，comment+2
    Map<Long, String> usernameOfId = new HashMap<>();
    Map<Long, Integer> activityOfId = new HashMap<>();
    for (Question q : questions) {
      if (!usernameOfId.containsKey(q.getOwner().getAccount_id())) {
        usernameOfId.put(q.getOwner().getAccount_id(), q.getOwner().getDisplay_name());
      }
      int value = activityOfId.getOrDefault(q.getOwner().getAccount_id(), 0);
      activityOfId.put(q.getOwner().getAccount_id(), value + 5);
      if (ansOfQues.get(q.getQuestion_id()) != null) {
        ansOfQues.get(q.getQuestion_id()).forEach(e -> {
          if (!usernameOfId.containsKey(e.getOwner().getAccount_id())) {
            usernameOfId.put(e.getOwner().getAccount_id(),
                e.getOwner().getDisplay_name());
          }
          int val = activityOfId.getOrDefault(e.getOwner().getAccount_id(), 0);
          activityOfId.put(e.getOwner().getAccount_id(), val + 3);
        });
      }
      if (q.getComments() != null) {
        q.getComments().forEach(e -> {
          if (!usernameOfId.containsKey(e.getOwner().getAccount_id())) {
            usernameOfId.put(e.getOwner().getAccount_id(),
                e.getOwner().getDisplay_name());
          }
          int val = activityOfId.getOrDefault(e.getOwner().getAccount_id(), 0);
          activityOfId.put(e.getOwner().getAccount_id(), val + 2);
        });
      }
    }
    Map<String, Integer> result = new HashMap<>();
    usernameOfId.forEach((u, v) -> {
      int val = activityOfId.getOrDefault(u, 0);
      result.put(v, val);
    });
    return result;
  }

  public static Map<String, Integer> frequentlyDiscussed() {
    Map<String, Integer> result = new HashMap<>();
    List<String> bodies = new ArrayList<>();
    List<String> codes = new ArrayList<>();
    for (Question q : questions) {
      if (ansOfQues.containsKey(q.getQuestion_id())) {
        bodies.addAll(
            ansOfQues.get(q.getQuestion_id()).stream().map(Answer::getBody).toList());
      }
      if (q.getComments() != null) {
        // 添加评论的Owner的id
        bodies.addAll(q.getComments().stream().map(Comment::getBody).toList());
      }
    }
    bodies.forEach(b -> {
      codes.addAll(HTMLParser.findCodeInBlocks(b));
    });
    return HTMLParser.JavaAPICollect(codes);
  }
}
