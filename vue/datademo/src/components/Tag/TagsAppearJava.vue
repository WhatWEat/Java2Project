<template>
  <div id="chart-container" style="width: 80vw; height: 80vh"/>
</template>

<script>
import "echarts-wordcloud";
import axios from "axios";
export default {
  name: "TagsAppearJava",
  data(){
    return{
      chart: null,
      graphData: {
        tooltip: {
          show: true,
          formatter: function(params) {
            return "<div><p style='font-weight: bold; font-size: 14px;'>经常出现的Tags</p><p>" + params.data.name + ": " + params.data.value + "</p></div>";
          },
          textStyle: {
            fontSize: 16
          }
        },
        series: [
          {
            type: "wordCloud",
            sizeRange: [20, 80],
            rotationRange: [-90, 90],
            rotationStep: 45,
            gridSize: 15,
            shape: "pentagon",
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
            emphasis: {
              focus: 'self',
              textStyle: {
                shadowBlur: 10,
                shadowColor: "#333",
                color: "red",
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
      axios.get('/Tags/Q1').then(res => {
        this.graphData.series[0].data = res.data;
        this.drawChart();
      });
    },
    drawChart() {
      this.chart = this.$echarts.init(document.getElementById("chart-container"));
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