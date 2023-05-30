<template>
  <div id="UserDis" style="width: 80vw; height: 80vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "UserDis",
  data() {
    return{
      chart: null,
      graphData: {
        title: {
          text: 'Stacked Line'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['总数', '仅评论', '仅回答']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '总数',
            type: 'line',
            stack: 'Total',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '仅评论',
            type: 'line',
            stack: 'Total',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: '仅回答',
            type: 'line',
            stack: 'Total',
            data: [150, 232, 201, 154, 190, 330, 410]
          },
        ]
      }
    }
  },
  methods: {
    getData() {
      axios.get('/Users/Q1/total').then(res => {
        this.graphData.xAxis.data = res.data.map(item => item.name);
        this.graphData.series[0].data = res.data.map(item => item.value);
        console.log(res.data);
        this.drawChart();
      });
      axios.get('/Users/Q1/comments').then(res => {
        this.graphData.series[1].data = res.data.map(item => item.value);
        console.log(res.data);
        this.drawChart();
      });
      axios.get('/Users/Q1/answers').then(res => {
        this.graphData.series[2].data = res.data.map(item => item.value);
        console.log(res.data);
        this.drawChart();
      });
    },
    drawChart() {
      this.chart = this.$echarts.init(document.getElementById("UserDis"));
      this.chart.setOption(this.graphData);
    },
  },
  mounted() {
    this.getData();
  },
  beforeDestroy() {
    if (this.chart) {
      this.chart.dispose();
    }
  }
}
</script>

<style scoped>

</style>