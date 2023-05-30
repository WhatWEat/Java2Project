<template>
  <div id="TagsCombine" style="width: 90vw; height: 100vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "TagsCombine",
  data(){
    return{
      data: null,
      chart: null,
      graphData: {
        title: {
          text: 'Tags组合',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          type: 'scroll',
          orient: 'vertical',
          right: 10,
          top: 20,
          bottom: 20,
          data: [],
        },
        series: [
          {
            name: 'Tags',
            type: 'pie',
            radius: '60%',
            center: ['40%', '50%'],
            data:[],
            emphasis: {
              focus: 'self',
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      },
    }
  },
  methods: {
    getData(){
      axios.get('/Tags/Q2').then(res => {
        this.data = res.data;
        this.graphData.legend.data = this.data.map(item => item.name);
        this.graphData.series[0].data = this.data.slice(0, 100);
        console.log(this.graphData.series[0].data);
        this.drawChart();
      });
    },
    drawChart(){
      this.chart = this.$echarts.init(document.getElementById("TagsCombine"));
      this.chart.setOption(this.graphData);
    }
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