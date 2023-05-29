<template>
  <div id="anserDistri" style="width: 80vw; height: 80vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "AnswerDistri",
  data() {
    return {
      chart: null,
      data: [],
      graphData: {
        grid: {containLabel: true},
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {name: 'score'},
        visualMap: {
          orient: 'horizontal',
          left: 'center',
          min: 10,
          max: 100,
          text: ['More', 'Less'],
          // Map the score column to color
          dimension: 1,
          inRange: {
            color: ['#65B581', '#FFCE34', '#FD665F']
          }
        },
        series: [
          {
            name: 'score',
            type: 'bar',
            data: []
          }
        ]
      },
    };
  },
  methods: {
    getData() {
      axios.get('/AcceptedAnswers/Q2').then(res => {
        this.data = res.data;
        this.graphData.xAxis.data = this.data.map(item => item.name);
        this.graphData.series[0].data = this.data.map(item => ({
          value: item.value,
          name: item.name,
        }));
        console.log(this.graphData.series[0].data);
        this.drawChart();
      });
    },
    drawChart() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      this.chart = this.$echarts.init(document.getElementById("anserDistri"));
      // 指定图表的配置项和数据
      let option = this.graphData;
      // 使用刚指定的配置项和数据显示图表。
      this.chart.setOption(option);
    },
  },
  mounted() {
    this.getData();
  },
  beforeDestroy() {
    if (this.chart != null) {
      this.chart.dispose();
    }
  }
}
</script>

<style scoped>

</style>