<template>
  <div id="lineChart">

  </div>
</template>

<script>
import echarts from "echarts";

export default {
  name: "NoAnswer",
  methods: {
    drawLineChart(id) {
      echarts.init(document.getElementById(id)).dispose(); //初始化echarts之前先手动销毁之前的echarts图，防止显示错乱
      let lineChart = this.$echarts.init(document.getElementById(id)); //初始化echarts
// 添加配置项
      lineChart.setOption(
          {
            xAxis: {
              type: 'category',
              data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                data: [150, 230, 224, 218, 135, 147, 260],
                type: 'line'
              }
            ]
          }
      );
    }
  },
  mounted() {
    this.drawLineChart('lineChart');
  }
}
</script>

<style scoped>
.lineChart {
  width: 800px;
  height: 800px;
}
</style>