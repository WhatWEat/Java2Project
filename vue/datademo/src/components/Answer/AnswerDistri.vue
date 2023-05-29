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
        dataset: {
          source: [
            ['score', 'number'],
            [89.3, '1'],
            [57.1, '2'],
            [74.4, '3'],
            [50.1, '4'],
            [89.7, '5'],
            [68.1, '6'],
            [19.6, '7'],
            [10.6, '8'],
            [32.7, '9']
          ]
        },
        grid: { containLabel: true },
        xAxis: { type: 'category' },  // 类型改为 category
        yAxis: { name: 'score' },    // 名称改为 amount
        visualMap: {
          orient: 'horizontal',
          left: 'center',
          min: 10,
          max: 100,
          text: ['High Score', 'Low Score'],
          // Map the score column to color
          dimension: 0,
          inRange: {
            color: ['#65B581', '#FFCE34', '#FD665F']
          }
        },
        series: [
          {
            type: 'bar',
            encode: {
              // Map the "product" column to X axis.
              x: 'number',
              // Map the "amount" column to Y axis
              y: ''
            }
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