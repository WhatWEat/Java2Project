<template>
  <!--  使用没有答案的百分比-->
  <div id="main" style="width: 50vw; height: 60vh"/>
</template>

<script>

export default {
  name: "NoAnswer",
  data() {
    return {
      graphData: {
        title: {
          text: "ECharts 入门示例",
        },
        tooltip: {},
        legend: {
          data: ["销量"],
        },
        xAxis: {
          data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "bar",
            data: [5, 20, 36, 10, 10, 20],
          },
        ],
      }
    };
  },
  methods: {
    drawChart() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = this.$echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      let option = this.graphData;
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
  },
  mounted() {
    this.drawChart();
  },
}
</script>

<style scoped>
.lineChart {
  width: 800px;
  height: 800px;
}
</style>