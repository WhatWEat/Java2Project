<template>
  <el-carousel indicator-position="outside" autoplay interval="10000" height="80vh"
               style="width: 80vw;">
    <el-carousel-item style="height: 80vh">
      <div id="total" style="width: 80vw; height: 80vh"/>
    </el-carousel-item>
    <el-carousel-item style="height: 80vh">
      <div id="comment" style="width: 80vw; height: 80vh"/>
    </el-carousel-item>
    <el-carousel-item style="height: 80vh">
      <div id="answer" style="width: 80vw; height: 80vh"/>
    </el-carousel-item>
  </el-carousel>

</template>

<script>
import axios from "axios";

export default {
  name: "UserDis",
  data() {
    return {
      chartTotal: null,
      chartComment: null,
      chartAnswer: null,
      chat: null,
      graphData: {
        title: {
          text: '用户数据分布',
          left: 'center'
        },
        xAxis: {
          name: 'Thread中行为数',
          type: 'category',
          boundaryGap: false,
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value',
        },
        series: [
          {
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true,
            areaStyle: {},
            color: '#65B581',
            label: {
              show: true,
              position: 'top',
            }
          }
        ]
      }
    }
  },
  methods: {
    getData() {
      axios.get('/Users/Q1/total').then(res => {
        this.graphData.title.text = '活跃总数分布';
        this.graphData.xAxis.data = res.data.map(item => item.name);
        this.graphData.series[0].data = res.data.map(item => item.value);
        this.graphData.series[0].color = '#65B581';
        console.log('total');
        console.log(res.data);
        this.drawTotalChart();
      });
      axios.get('/Users/Q1/comments').then(res => {
        this.graphData.title.text = '用户评论分布';
        this.graphData.xAxis.data = res.data.map(item => item.name);
        this.graphData.series[0].data = res.data.map(item => item.value);
        this.graphData.series[0].color = '#65b5b2';
        console.log('comments');
        console.log(res.data);
        this.drawCommentsChart();
      });
      axios.get('/Users/Q1/answers').then(res => {
        this.graphData.title.text = '用户回答分布';
        this.graphData.xAxis.data = res.data.map(item => item.name);
        this.graphData.series[0].data = res.data.map(item => item.value);
        this.graphData.series[0].color = '#b065b5';
        console.log('answers');
        console.log(res.data);
        this.drawAnswerChart();
      });
    },
    drawTotalChart() {
      this.chart = this.$echarts.init(document.getElementById("total"));
      this.chart.setOption(this.graphData);
    },
    drawCommentsChart() {
      this.chartComment = this.$echarts.init(document.getElementById("comment"));
      this.chartComment.setOption(this.graphData);
    },
    drawAnswerChart() {
      this.chartAnswer = this.$echarts.init(document.getElementById("answer"));
      this.chartAnswer.setOption(this.graphData);
    }
  },
  mounted() {
    this.getData();
  },
  beforeDestroy() {
    if (this.chart) {
      this.chart.dispose();
    }
    if (this.chartComment) {
      this.chartComment.dispose();
    }
    if (this.chartAnswer) {
      this.chartAnswer.dispose();
    }
  }
}
</script>

<style scoped>

</style>