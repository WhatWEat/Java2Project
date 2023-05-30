<template>
  <div id="userActive" style="width: 95vw; height: 80vh"/>
</template>

<script>
import "echarts-wordcloud";
import axios from "axios";
export default {
  name: "UserActive",
  data(){
    return{
      chart: null,
      graphData: {
        tooltip: {
          show: true,
          formatter: function(params) {
            return "<div><p style='font-weight: bold; font-size: 14px;'>活跃度</p><p>" + params.data.name + ": " + params.data.value + "</p></div>";
          },
          textStyle: {
            fontSize: 16
          }
        },
        series: [
          {
            type: "wordCloud",
            sizeRange: [10, 60],
            rotationRange: [-90, 90],
            rotationStep: 45,
            gridSize: 8,
            shape: "circle",
            width: "100%",
            height: "100%",
            drawOutOfBound: true,
            textStyle: {
              color: function () {
                return (
                    "rgb(" +
                    [
                      Math.round(Math.random() * 160),
                      Math.round(Math.random() * 160),
                      Math.round(Math.random() * 160),
                    ].join(",") +
                    ")"
                );
              },
            },
            data: [],
          },
        ],
      },

    }
  },
  methods: {
    getData(){
      axios.get('/Users/Q2').then(res => {
        this.graphData.series[0].data = res.data.splice(0,600);
        console.log(res.data);
        this.drawChart();
      });
    },
    drawChart() {
      this.chart = this.$echarts.init(document.getElementById("userActive"));
      this.chart.setOption(this.graphData);
    },
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