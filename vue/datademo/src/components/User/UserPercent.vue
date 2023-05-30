<template>
  <div id="userPercent" style="width: 40vw; height: 80vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "UserPercent",
  data(){
    return{
      chart: null,
      graphData: {
        title:{
          text: '行为占比',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: function(params) {
            return params.name + ': ' + params.value + ' (' + params.percent + '%)';
          }
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '行为',
            type: 'pie',
            radius: ['30%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
            ]
          }
        ]
      }
    }
  },
  methods: {
    getData(){
      axios.get('/Users/Q1/1000threadsData').then(res => {
        console.log(res.data);
        // let data = [];
        // data.push(res.data[1]);
        // data.push(res.data[2]);
        this.graphData.series[0].data = res.data;
        this.drawChart();
      })
      this.drawChart();
    },
    drawChart(){
      this.chart = this.$echarts.init(document.getElementById("userPercent"));
      this.chart.setOption(this.graphData);
    }
  },
  mounted(){
    this.getData();
  }
}
</script>

<style scoped>

</style>