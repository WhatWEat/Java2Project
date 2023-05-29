<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <div>
          <el-statistic group-separator="," :value="answer" title="回答总数">
            <template slot="prefix">
              <i class="el-icon-s-flag" style="color: blue"></i>
            </template>
          </el-statistic>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-statistic title="采纳">
            <template slot="prefix">
              <i class="el-icon-s-flag" style="color: red"></i>
              <span>{{this.answerAverage.toFixed(2)}}</span>
            </template>

          </el-statistic>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-statistic title="单个问题最多的回答" :value="this.maxAnswer">
            <template slot="suffix">
                <i class="el-icon-star-off"></i>
            </template>
          </el-statistic>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-statistic title="每个问题下回答的平均数">
            <template slot="suffix">
                <i class="el-icon-star-on" style="color:red"></i>
                <span>{{this.average.toFixed(2)}}</span>
            </template>
          </el-statistic>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AnserStatic",
  data() {
    return {
      like: true,
      problem: 0,
      answer: 0,
      average: 1.0,
      answerAverage: 1.0,
      maxAnswer: 1,
    };
  },
  methods: {
    getProblem(){
      axios.get('NumberOfAnswers/Q1').then(res => {
        this.problem = res.data[1];
        console.log(this.problem);
        this.calculateAverage();
      })
    },
    getAnswer(){
      axios.get('NumberOfAnswers/Q2/avg').then(res => {
        this.answer = res.data[0];
        console.log(this.answer);
        this.calculateAverage();
      });
      axios.get('/NumberOfAnswers/Q2/max').then(res => {
        this.maxAnswer = res.data;
        console.log(this.maxAnswer);
      });
      axios.get('/AcceptedAnswers/Q1').then(res => {
        this.answerAverage = res.data[0]/res.data[1];
        console.log(this.answerAverage);
      });
    },
    calculateAverage() {
      if (this.answer !== undefined && this.problem !== undefined) {
        this.average = this.answer / this.problem;
        console.log(this.average);
      }
    }
  },
  mounted() {
    this.getProblem();
    this.getAnswer();
  },
};
</script>
<style lang="css">
.like {
  cursor: pointer;
  font-size: 25px;
  display: inline-block;
}
</style>