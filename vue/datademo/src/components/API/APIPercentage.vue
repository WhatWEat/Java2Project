<template>
    <div id="APIPercentage" style="width: 90vw; height: 90vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "APIPercentage",
  data() {
    return {
      loading: true,
      data: null,
      chart: null,
      graphData: {
        title: {
          text: 'API百分比',
          left: 'center',
          top: '10%',
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
            data: [],
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
    getData() {
      axios.get('/JavaAPI/Q1').then(res => {
        this.data = res.data.splice(0, 50);
        this.graphData.legend.data = this.data.map(item => item.name);
        this.graphData.series[0].data = this.data;
        console.log(this.graphData.series[0].data);
        this.loading = false;
        this.drawChart();
      });
    },
    drawChart() {
      this.chart = this.$echarts.init(document.getElementById("APIPercentage"));
      this.chart.setOption(this.graphData);

    }
  },
  mounted() {
    this.getData();
  },
  beforeDestroy() {
    if (this.chart != null) {
      this.chart.dispose();
      this.loading = true;
    }
  },
}
</script>

<style scoped>

</style>