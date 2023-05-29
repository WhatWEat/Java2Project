<template>
  <div id="anserDistri" style="width: 40vw; height: 50vh"/>
</template>

<script>
export default {
  name: "AnswerDistri",
  data() {
    return {
      chart: null,
      graphData: {
        grid: { containLabel: true },
        xAxis: { type: 'category' },
        yAxis: { name: 'score' },
        visualMap: {
          orient: 'horizontal',
          left: 'center',
          min: 10,
          max: 100,
          text: ['High Score', 'Low Score'],
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
            data: [
              {value: 89.3, name: '1'},
              {value: 57.1, name: '2'},
              {value: 74.4, name: '3'},
              {value: 50.1, name: '4'},
              {value: 89.7, name: '5'},
              {value: 68.1, name: '6'},
              {value: 19.6, name: '7'},
              {value: 10.6, name: '8'},
              {value: 32.7, name: '9'}
            ]
          }
        ]
      },
    };
  },
  methods: {
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
    this.drawChart();
  },
  beforeDestroy() {
    if(this.chart != null){
      this.chart.dispose();
    }
  }
}
</script>

<style scoped>

</style>