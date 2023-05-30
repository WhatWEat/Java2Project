<template>
  <div id="TimeInterval" style="width: 40vw; height: 50vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "TimeInterval",
  data(){
    return{
      chart: null,
      graphData: {
        title: {
          text: '回答时间分布',
          left: 'center'
        },
        grid: { containLabel: true },
        xAxis: { name: 'value' },
        yAxis: { type: 'category', data: []},
        visualMap: {
          orient: 'horizontal',
          left: 'center',
          min: 10,
          max: 100,
          text: ['More', 'Less'],
          dimension: 0,
          inRange: {
            color: ['#65B581', '#FFCE34', '#FD665F']
          }
        },
        series: [
          {
            type: 'bar',
            data: []
          }
        ]
      },
    }
  },
  methods: {
    getData() {
      axios.get('/AcceptedAnswers/Q2').then(res => {
        this.data = res.data;
        this.graphData.yAxis.data = this.data.map(item => item.name);
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
      this.chart = this.$echarts.init(document.getElementById("TimeInterval"));
      // 指定图表的配置项和数据
      let option = this.graphData;
      // 使用刚指定的配置项和数据显示图表。
      this.chart.setOption(option);
    },
  },
  mounted() {
    this.getData();
  }
}
</script>

<style scoped>

</style>