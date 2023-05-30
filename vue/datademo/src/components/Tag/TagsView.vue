<template>
  <div id="TagsView" style="width: 90vw; height: 90vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "TagsView",
  data() {
    return {
      data: null,
      chart: null,
      graphData: {
        title: {
          text: 'Tags组合有最多的浏览',
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
      axios.get('/Tags/Q3').then(res => {
        this.data = res.data.splice(0, 50);
        this.graphData.legend.data = this.data.map(item => item.name);
        this.graphData.series[0].data = this.data;
        console.log(this.graphData.series[0].data);
        this.drawChart();
      });
    },
    drawChart() {
      this.chart = this.$echarts.init(document.getElementById("TagsView"));
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
  },
}
</script>

<style scoped>

</style>